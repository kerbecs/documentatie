package MyApp.dao;

import MyApp.Entity.Customer;

import java.util.List;

public interface CustomerDAO {
    public List<Customer> getCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomer(int id);

    void deleteCustomer(int id);

    List<Customer> searchCustomer(String customerName);

    List<Customer> sortCustomers(int sort);
}
