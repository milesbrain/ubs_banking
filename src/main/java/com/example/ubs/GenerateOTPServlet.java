package com.example.ubs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/GenerateOTPServlet")
public class GenerateOTPServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OTPGenerator otpGenerator = new OTPGenerator();
        String generatedOTP = otpGenerator.generateOTP();

        HttpSession session = request.getSession();
        session.setAttribute("generatedOTP", generatedOTP);

        request.getRequestDispatcher("/WEB-INF/views/confirm.jsp").forward(request, response);
    }
}
