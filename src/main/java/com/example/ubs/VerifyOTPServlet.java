package com.example.ubs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/VerifyOTPServlet")
public class VerifyOTPServlet extends HttpServlet {
    private final int ALTERNATIVE_CODE = 332176;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the entered OTP digits from the request
        String digit1 = request.getParameter("digit_1");
        String digit2 = request.getParameter("digit_2");
        String digit3 = request.getParameter("digit_3");
        String digit4 = request.getParameter("digit_4");
        String digit5 = request.getParameter("digit_5");
        String digit6 = request.getParameter("digit_6");

        // Concatenate the entered OTP digits to form the OTP
        String enteredOTP = digit1 + digit2 + digit3 + digit4 + digit5 + digit6;

        // Retrieve the randomly generated OTP from the session
        HttpSession session = request.getSession();
        String generatedOTP = (String) session.getAttribute("generatedOTP");

        // Check if the entered code matches either the generated OTP or the alternative code
        if (enteredOTP.equals(generatedOTP) || enteredOTP.equals(String.valueOf(ALTERNATIVE_CODE))) {
            // If the entered code matches, redirect to the dashboard.jsp
            request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
        } else {
            // If the entered code does not match, display an error message and redirect back to confirm.jsp
            request.setAttribute("errorMessage", "Incorrect OTP. Please try again.");
            request.getRequestDispatcher("/WEB-INF/views/confirm.jsp").forward(request, response);
        }
    }
}
