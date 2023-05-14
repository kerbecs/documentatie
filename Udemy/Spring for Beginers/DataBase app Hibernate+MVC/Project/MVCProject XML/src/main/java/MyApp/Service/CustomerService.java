package MyApp.Service;

import MyApp.Entity.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> getCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomer(int id);

    void deleteCustomer(int id);

    List<Customer> searchCustomer(String customerName);

    List<Customer> sortCustomers(int sort);
}
