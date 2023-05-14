package com.MyApp.Employee.demo.dao;

import com.MyApp.Employee.demo.entity.Employee;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        Session session = entityManager.unwrap(Session.class);

        Query<Employee> query = session.createQuery("from Employee",Employee.class);
        List<Employee> employees = query.getResultList();

        return employees;
    }

    @Override
    public Employee findByID(int id) {
        Session session = entityManager.unwrap(Session.class);
        Employee employee =  session.get(Employee.class,id);
        return employee;
    }

    @Override
    public void save(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        System.out.println(employee);
        session.saveOrUpdate(employee);
    }

    @Override
    public void deleteById(int id) {
        Session session = entityManager.unwrap(Session.class);

        Query<Employee> query = session.createQuery("delete from Employee where id=:employee").setParameter("employee",id);
        query.executeUpdate();
    }
}
