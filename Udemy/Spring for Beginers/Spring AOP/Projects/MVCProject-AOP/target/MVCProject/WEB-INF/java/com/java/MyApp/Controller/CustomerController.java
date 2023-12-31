package com.java.MyApp.Controller;

import com.java.MyApp.Entity.Customer;
import com.java.MyApp.Service.CustomerService;
import com.java.MyApp.dao.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model){
List<Customer> customerList = customerService.getCustomers();
model.addAttribute("customers",customerList);
        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer",customer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer")Customer customer){
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForId(@RequestParam("customerId")int id, Model model){
      Customer customer = customerService.getCustomer(id);
      model.addAttribute("customer",customer);

      return "customer-form";
    }
    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId")int id){
        customerService.deleteCustomer(id);

        return "redirect:/customer/list";
    }
    @GetMapping("/searchCustomer")
    public String searchCustomer(@RequestParam("customerName")String customerName,Model model){
        List<Customer> customers = customerService.searchCustomer(customerName);
        model.addAttribute("customers",customers);

        return "list-customers";
    }
    @GetMapping("/listSort")
    public String listSorted(@RequestParam(name = "sort", required = false)int sort,Model model) {
        List<Customer> customers = customerService.sortCustomers(sort);

        model.addAttribute("customers",customers);
        return "list-customers";
    }


}
