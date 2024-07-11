package com.example.ubs;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/submit_account")
public class SubmitServlet extends HttpServlet {
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        dataSource = context.getBean(DataSource.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountType = request.getParameter("accountType");
        String accountName = request.getParameter("accountName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String amount = request.getParameter("amount").replace(",", "");
        BigDecimal amountBig = new BigDecimal(amount);
        String currency = request.getParameter("currency");

        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO accounts (account_type, account_name, email, password, amount, currency) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, accountType);
                preparedStatement.setString(2, accountName);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.setBigDecimal(5, amountBig);
                preparedStatement.setString(6, currency);
                preparedStatement.executeUpdate();
            }
            request.setAttribute("successMessage", "Account created successfully!");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Optional: Implement if GET request handling is needed
    }
}
