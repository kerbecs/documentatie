package com.java.MyApp.dao;

import com.java.MyApp.Entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.java.MyApp.Utility.SortClass;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class CustomerDAOImpl implements CustomerDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Customer ORDER BY lastName",Customer.class);

        List<Customer> customers = query.getResultList();
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class,id);

        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM Customer WHERE id=:id").setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomer(String customerName) {
        Session session = sessionFactory.getCurrentSession();
        List<Customer> customerList;

        Query query = session.createQuery("FROM Customer WHERE lower(firstName) LIKE ?0 OR lower(lastName) LIKE ?1");
        query.setParameter(0,"%"+customerName.toLowerCase()+"%");
        query.setParameter(1,"%"+customerName.toLowerCase()+"%");
        customerList = query.getResultList();

        if(customerList==null){
            customerList = getCustomers();
        }

        return customerList;
    }

    @Override
    public List<Customer> sortCustomers(int sort) {
        List<Customer> customers = null;
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Customer ORDER BY ";

        switch(sort){
            case SortClass.FIRST_NAME -> {
              query += "firstName";
            }
            case SortClass.LAST_NAME -> {
                query += "lastName";
            }
            case SortClass.EMAIL -> {
                query += "email";
            }
        }
        Query query1 = session.createQuery(query);
        customers = query1.getResultList();

        return customers;
    }
}
