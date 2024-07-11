package com.example.ubs;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/Admin")
public class Admin extends HttpServlet {

    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        if (context != null) {
            dataSource = context.getBean(DataSource.class);
        } else {
            throw new ServletException("Failed to get Spring application context.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM admin WHERE email=? AND password=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        req.getRequestDispatcher("/WEB-INF/views/admin_option.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("errorMessage", "Invalid email or password.");
                        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "An error occurred while accessing the database: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
