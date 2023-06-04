package com.java.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebAppController {

    @GetMapping("/")
    public String indexPage(){
        return "index";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @GetMapping("/forAdmins")
    public String adminPage(){
        return "forAdmins";
    }
    @GetMapping("/forManagers")
    public String managerPage(){
        return "forManagers";
    }
    @GetMapping("/noPermission")
    public String noPermission(){
        return "accesDenied";
    }
}
