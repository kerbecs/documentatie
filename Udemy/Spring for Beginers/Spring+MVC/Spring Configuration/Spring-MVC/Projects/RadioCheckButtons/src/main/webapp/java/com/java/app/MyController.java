package com.java.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping("/")
    public String index(Model model){
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        Student student = context.getBean("student",Student.class);
        model.addAttribute("student",student);

        return "index";
    }

    @PostMapping("/formProcess")
    public String hello(@ModelAttribute("student")Student student){
      System.out.println(student);
        return "processed";
    }
}
