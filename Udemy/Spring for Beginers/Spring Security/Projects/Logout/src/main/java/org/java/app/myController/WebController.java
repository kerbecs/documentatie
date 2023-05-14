package org.java.app.myController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/showMyLoginPage")
    public String showLoginPage(){
        return "login";
    }
}
