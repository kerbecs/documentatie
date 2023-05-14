package com.app.demo.dao;

import com.app.demo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImplement implements EmployeeDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> findAll() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Employee",Employee.class);

        return query.getResultList();
    }

    @Override
    public Employee findEmployeeId(int id) {
        Session session = sessionFactory.unwrap(Session.class);

        return session.find(Employee.class,id);
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.unwrap(Session.class);
        session.saveOrUpdate(employee);

    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.unwrap(Session.class);
        Query query = session.createQuery("delete from Employee WHERE id=?1").setParameter(1,id);
        query.executeUpdate();

    }
}
