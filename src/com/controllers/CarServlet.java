package com.controllers;

import com.models.Car;
import com.services.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * The {@code CarServlet} class handles HTTP requests related to car operations.
 * <p>
 * It receives requests for retrieving available cars and saving new cars, interacts with the {@code CarService}
 * to perform corresponding actions, and returns appropriate responses.
 * </p>
 */

@WebServlet("/cars")
public class CarServlet extends HttpServlet {
    private final CarService carService = new CarService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Car> availableCars = carService.getAllAvailableCars();

        request.setAttribute("cars", availableCars);
        request.getRequestDispatcher("cars.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Car car = new Car(
                request.getParameter("model"),
                Boolean.parseBoolean(request.getParameter("available"))
        );

        try {
            this.carService.saveCar(car);
            response.sendRedirect("cars");
        } catch (RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("cars.jsp").forward(request, response);
        }
    }
}