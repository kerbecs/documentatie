package org.app.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

@SessionAttributes(value = {"client"}, types = {Client.class})
@Controller
public class Controll {

    @RequestMapping("/")
    public String mainPage(){
        return "main";
    }
    @RequestMapping("/form")
    public String formPage(Model model){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        model.addAttribute("client",context.getBean("MyClient",Client.class));
        return "form";
    }

    @RequestMapping(value="/processForm", method = RequestMethod.POST)
    public String registerPage(@Valid @ModelAttribute("client") Client client, BindingResult bindingResult, Model model){


        if(bindingResult.hasErrors()){
            return "form";
        }
        else {
            return "register";
        }
    }
}
