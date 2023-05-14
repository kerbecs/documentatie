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

        int theId = 1;

        InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);
        System.out.println(instructorDetail);
        Instructor instructor = instructorDetail.getInstructor();
        System.out.println(instructor);


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
