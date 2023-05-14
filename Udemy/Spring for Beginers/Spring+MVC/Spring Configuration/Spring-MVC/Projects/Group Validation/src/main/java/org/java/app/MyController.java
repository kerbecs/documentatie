package org.java.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@SessionAttributes("student")
public class MyController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("student",context.getBean("student",Student.class));
        return "index";
    }

    @PostMapping("/register")
    public String register(@Validated(Group1.class) @ModelAttribute("student") Student student, BindingResult result, HttpServletRequest request){
        System.out.println(student);
        System.out.println(request.getParameter("opinion"));
        if(result.hasErrors())
            return "index";
        return "choice";
    }
    @GetMapping("/choice")
    public String choice(@ModelAttribute("student")Student student, Model model){
        return "choice";
    }
    @PostMapping("/processChoice")
    public String processChoice(@Validated(Group2.class) @ModelAttribute("student")Student student,BindingResult result){
        System.out.println(student);
        if(result.hasErrors())
            return "choice";
        return "info";
    }
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
