package com.example.ubs;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private Email emailService;
    private DataSource dataSource;
    private OTPGenerator otpGenerator;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        emailService = context.getBean(Email.class);
        dataSource = context.getBean(DataSource.class);
        otpGenerator = new OTPGenerator();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String ipAddress = getClientIpAddress(request);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String loginTime = now.format(formatter);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE email = ? AND password = ?")) {

            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Successful login, generate and send OTP
                    String otp = otpGenerator.generateOTP();
                    sendLoginEmail(email, otp);

                    // Store the generated OTP and account details in the session
                    HttpSession session = request.getSession();
                    session.setAttribute("generatedOTP", otp);
                    session.setAttribute("accountType", resultSet.getString("account_type"));
                    session.setAttribute("accountName", resultSet.getString("account_name"));
                    session.setAttribute("balance", resultSet.getBigDecimal("amount"));
                    session.setAttribute("currency", resultSet.getString("currency"));

                    // Insert login details
                    try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO user_login (email, login_time, ip_address) VALUES (?, ?, ?)")) {
                        insertStatement.setString(1, email);
                        insertStatement.setString(2, loginTime);
                        insertStatement.setString(3, ipAddress);
                        insertStatement.executeUpdate();
                    }

                    // Forward to confirm.jsp
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/confirm.jsp");
                    requestDispatcher.forward(request, response);

                } else {
                    request.setAttribute("errorMessage", "Invalid email or password");
                    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An unexpected error occurred");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress != null && ipAddress.length() != 0 && !"unknown".equalsIgnoreCase(ipAddress)) {
            return ipAddress.split(",")[0];
        }

        ipAddress = request.getHeader("Proxy-Client-IP");
        if (ipAddress != null && ipAddress.length() != 0 && !"unknown".equalsIgnoreCase(ipAddress)) {
            return ipAddress;
        }

        ipAddress = request.getHeader("WL-Proxy-Client-IP");
        if (ipAddress != null && ipAddress.length() != 0 && !"unknown".equalsIgnoreCase(ipAddress)) {
            return ipAddress;
        }

        ipAddress = request.getHeader("HTTP_CLIENT_IP");
        if (ipAddress != null && ipAddress.length() != 0 && !"unknown".equalsIgnoreCase(ipAddress)) {
            return ipAddress;
        }

        ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (ipAddress != null && ipAddress.length() != 0 && !"unknown".equalsIgnoreCase(ipAddress)) {
            return ipAddress;
        }

        return request.getRemoteAddr();
    }

    private void sendLoginEmail(String email, String otp) {
        String emailContent = buildEmail(otp);
        try {
            emailService.send(email, emailContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String buildEmail(String otp) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <title>Email Verification</title>\n" +
                "    <style>\n" +
                "        body,\n" +
                "        td,\n" +
                "        div,\n" +
                "        p,\n" +
                "        a,\n" +
                "        input {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            color: black;\n" +
                "            font-size: 18px;\n" +
                "        }\n" +
                "        a:link,\n" +
                "        a:active {\n" +
                "            color: black;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "        a:hover {\n" +
                "            text-decoration: underline;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "        a:visited {\n" +
                "            color: red;\n" +
                "        }\n" +
                "        img {\n" +
                "            border: 0px;\n" +
                "            max-width: 100%;\n" +
                "            height: auto;\n" +
                "        }\n" +
                "        .logo {\n" +
                "            left: -7px;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "        .email-container {\n" +
                "            background-color: #eff1ff;\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "        .email-content {\n" +
                "            background-color: #fff;\n" +
                "            color: red;\n" +
                "            border: 1px solid transparent;\n" +
                "            border-radius: 0;\n" +
                "            width: 590px;\n" +
                "            margin: 0 auto;\n" +
                "        }\n" +
                "        .email-header {\n" +
                "            text-align: center;\n" +
                "            padding-top: 30px;\n" +
                "            background-color: #fff;\n" +
                "        }\n" +
                "        .email-body {\n" +
                "            background-color: #fff;\n" +
                "            padding: 30px 10px;\n" +
                "        }\n" +
                "        .email-body p {\n" +
                "            margin: 0;\n" +
                "            line-height: 1.6;\n" +
                "        }\n" +
                "        .code {\n" +
                "            color: red;\n" +
                "            font-size: 40px;\n" +
                "        }\n" +
                "        .email-footer {\n" +
                "            text-align: center;\n" +
                "            padding: 5px 0;\n" +
                "            background-color: #fff;\n" +
                "            font-size: 1px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"email-container\">\n" +
                "        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "            <tr>\n" +
                "                <td align=\"center\">\n" +
                "                    <table class=\"email-content\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "                        <tr>\n" +
                "                            <td align=\"center\">\n" +
                "                                <img src=\"/images/UBS-Logo.png\" alt=\"UBS Logo\" width=\"150\" style=\"max-width: 100%; height: auto;\">\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td class=\"email-header\">\n" +
                "                                <h1>Email Verification</h1>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td class=\"email-body\">\n" +
                "                                <p>Your verification code is\n" +
                "                                    <br>\n" +
                "                                    <span class=\"code\">" + otp + "</span>\n" +
                "                                </p>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <footer>\n" +
                "                            <td class=\"email-footer\">\n" +
                "                                <h6>\n" +
                "                                    <p>© 2024 UBS Technologies Ltd (Company No. 114722322). All rights reserved.</p>\n" +
                "                                    <p>&nbsp;</p>\n" +
                "                                    <p>If you would like to find out more about which UBS Technologies entity you receive services from, please reach out to us via the in-app chat in the UBS app.</p>\n" +
                "                                    <p>&nbsp;</p>\n" +
                "                                </h6>\n" +
                "                            </td>\n" +
                "                        </footer>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
}
