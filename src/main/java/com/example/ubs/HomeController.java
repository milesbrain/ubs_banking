package com.example.ubs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    @RequestMapping("/open_account")
    public String open() {

        return "open_account";
    }
    @RequestMapping("/address")
    public String address()
    {
        return "Address";
    }
}
