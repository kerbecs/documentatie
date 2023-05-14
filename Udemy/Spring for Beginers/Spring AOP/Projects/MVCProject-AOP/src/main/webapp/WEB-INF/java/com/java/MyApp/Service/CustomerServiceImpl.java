package com.java.MyApp.Service;

import com.java.MyApp.Entity.Customer;
import com.java.MyApp.dao.CustomerDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {

        customerDAO.saveCustomer(customer);
    }

    @Override
    @Transactional
    public Customer getCustomer(int id) {
          return customerDAO.getCustomer(id);
    }

    @Override
    @Transactional
    public void deleteCustomer(int id) {
        customerDAO.deleteCustomer(id);
    }

    @Override
    @Transactional
    public List<Customer> searchCustomer(String customerName) {
        return customerDAO.searchCustomer(customerName);
    }

    @Override
    @Transactional
    public List<Customer> sortCustomers(int sort) {

        return customerDAO.sortCustomers(sort);
    }
}
