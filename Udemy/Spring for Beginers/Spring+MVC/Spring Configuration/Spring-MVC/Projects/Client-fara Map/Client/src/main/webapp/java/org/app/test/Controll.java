package org.app.test;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class Controll {

    @InitBinder
    public void initBinder(WebDataBinder data){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        data.registerCustomEditor(String.class, stringTrimmerEditor);
    }


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

    @RequestMapping("/processForm")
    public String registerPage(@Valid @ModelAttribute("client") Client client, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "form";
        }
        else {
            return "register";
        }
    }
}
