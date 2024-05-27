package com.controllers;

import com.models.Booking;
import com.services.BookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * The {@code BookingServlet} class handles HTTP requests related to booking operations.
 * <p>
 * It receives requests for booking a car and retrieving bookings for a specified car ID, interacts with the
 * {@code BookingService} to perform corresponding actions, and returns appropriate responses.
 * </p>
 */

@WebServlet("/bookings")
public class BookingServlet extends HttpServlet {
    private final BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.bookingService.bookCar(
                new Booking(
                Integer.parseInt(request.getParameter("idUser")),
                Integer.parseInt(request.getParameter("idCar")),
                LocalDate.parse(request.getParameter("from_date")),
                LocalDate.parse(request.getParameter("from_date"))
                )
        );
        response.sendRedirect("bookings.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Booking> bookings = this.bookingService.
                getBookingByCarId(
                        Integer.parseInt(request.getParameter("idCar"))
                );
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("bookings.jsp").forward(request, response);
    }
}