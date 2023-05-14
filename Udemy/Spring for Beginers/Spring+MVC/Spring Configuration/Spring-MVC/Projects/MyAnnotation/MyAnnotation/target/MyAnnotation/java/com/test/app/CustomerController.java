package com.test.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class CustomerController {

    @RequestMapping("/")
    public String mainPage(){
        return "main";
    }

    @RequestMapping("/form")
    public String formPage(Model model){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
        model.addAttribute("customer",context.getBean("MyCustomer",Customer.class));
        return "form";
    }

    @RequestMapping("/register")
    public String registerPage(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "form";
        }
        else {
            return "register";
        }
    }
}
