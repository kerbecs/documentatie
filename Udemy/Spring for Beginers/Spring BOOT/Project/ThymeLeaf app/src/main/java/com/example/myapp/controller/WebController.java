package com.example.myapp.controller;

import com.example.myapp.entity.Employee;
import com.example.myapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class WebController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/list")
    public String findAll(Model model){
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees",employees);

        return "employees";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        model.addAttribute("theEmployee",new Employee());
        return "formAdd";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee e){
        employeeService.save(e);

        return "redirect:/list";
    }
    @GetMapping("/update")
    public String updateEmployee(@RequestParam("id")int id, Model model){
        model.addAttribute("theEmployee",employeeService.findById(id));

        return "formAdd";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id")int id){
        employeeService.deleteById(id);

        return "redirect:/list";
    }
}
