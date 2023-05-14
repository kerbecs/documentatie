package com.java.app;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {
    public static void main(String[] args){
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try{
        session.beginTransaction();

        int id = 1;
        Instructor instructor = session.get(Instructor.class,id);

        session.delete(instructor);
        session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
