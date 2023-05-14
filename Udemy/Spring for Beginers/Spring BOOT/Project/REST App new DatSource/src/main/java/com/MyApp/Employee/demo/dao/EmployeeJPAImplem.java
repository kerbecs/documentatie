package com.MyApp.Employee.demo.dao;

import com.MyApp.Employee.demo.entity.Employee;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;

@Repository
@Primary
public class EmployeeJPAImplem implements EmployeeDAO{

    @Autowired
    private EntityManager entityManager;
    @Override
    public List<Employee> findAll() {
        Query query = entityManager.createQuery("from Employee",Employee.class);

        List<Employee> employees = query.getResultList();

        return employees;
    }

    @Override
    public Employee findByID(int id) {

        return entityManager.find(Employee.class,id);
    }

    @Override
    public void save(Employee employee) {
      Employee employee1 = entityManager.merge(employee);
      employee.setId(employee1.getId());
    }

    @Override
    public void deleteById(int id) {
     Query query = entityManager.createQuery("delete from Employee where id=:id").setParameter("id",id);
     query.executeUpdate();
    }
}
