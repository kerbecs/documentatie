package com.java.app;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class TeacherDAOImp implements TeacherDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Teacher getTeacher(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Teacher) session.get(Teacher.class,id);
    }

    @Override
    public List<Teacher> getTeachersList() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Teacher");
        return query.list();
    }
}
