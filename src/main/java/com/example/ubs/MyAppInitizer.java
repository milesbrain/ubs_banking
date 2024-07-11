package com.example.ubs;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class MyAppInitizer extends HttpServlet {

    public void contextInitialized(ServletContextEvent sce) {
        LoginServlet loginServlet = new LoginServlet();
        sce.getServletContext().setAttribute("loginServlet", loginServlet);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // Do any necessary cleanup here
    }
}

