package com.myapp.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



import javax.persistence.Query;
import java.util.List;

@SuppressWarnings("unchecked")
public class Data {
    public static void main(String[] args){
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try{
            session.beginTransaction();
        Query query = session.createQuery("FROM Student s WHERE s.lastName LIKE 'A%'");
        List<Student> lista = query.getResultList();
        session.save(new Student("Vitalie","Celac","Vcelac@mail.ru"));

        for(Student student : lista){
            System.out.println(student);
        }
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
