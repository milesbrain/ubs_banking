package com.example.ubs;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Error implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Log the error details if needed
        // You can return different error views based on the error status code
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            switch (statusCode) {
                case 404:
                    return "error-404"; // error-404.jsp
                case 500:
                    return "error-500"; // error-500.jsp
                default:
                    return "error"; // error.jsp
            }
        }
        return "error";
    }


    public String getErrorPath() {
        return "/error";
    }
}
