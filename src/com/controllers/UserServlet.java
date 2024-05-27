package com.controllers;

import com.models.User;
import com.services.UserService;
import com.utils.UserRole;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Optional;

/**
 * The {@code UserServlet} class handles HTTP requests related to user operations.
 * <p>
 * It receives requests for user registration and login, interacts with the {@code UserService} to perform
 * corresponding actions, and returns appropriate responses.
 * </p>
 */

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");
        try {
            Writer writer = response.getWriter();
            writer.write("<h1> User servlet class is available </h2>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserRole role = UserRole.valueOf(request.getParameter("role"));
        User user = new User(username, password, role);

        if ("register".equals(action)) {
            try {
                this.userService.registerNewUser(user);
                response.sendRedirect("login.jsp");
            } catch (RuntimeException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else if ("login".equals(action)){
            Optional<User> userOptional = this.userService.findByUsername(user);
            String hashParameterPassword = this.hashPassword(request.getParameter("password"));
                if(userOptional.isPresent() &&
                userOptional.get().getPassword().equals(hashParameterPassword)){
                    response.getWriter().write("Login successful");
                        switch(userOptional.get().getRole()){
                            case UserRole.USER ->
                                    request.getRequestDispatcher("bookings.jsp").forward(request, response);
                            case UserRole.ADMIN ->
                                    request.getRequestDispatcher("addCar.jsp").forward(request, response);
                        }
                } else {
                    response.getWriter().write("Invalid login");
                }
        }
    }

    /**
     * Hashes the given password using SHA-256 algorithm.
     *
     * @param password the password to hash
     * @return the hashed password
     */
    private String hashPassword(String password){
        return DigestUtils.sha256Hex(password);
    }
}