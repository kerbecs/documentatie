package com.java.app;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {
    public static void main(String[] args){
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try{
        session.beginTransaction();

        Instructor instructor = session.get(Instructor.class,7);
        session.delete(instructor);


        session.getTransaction().commit();
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }
}

