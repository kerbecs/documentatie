package org.java.code;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;


@SuppressWarnings("unchecked")
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args){
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Student student1 = new Student(12586,"Mititiuc","Eduard","edikutsu2002@mail.ru");
        Student student2 = new Student(5789632,"Goncear","Alexandru","GAlex@mail.ru");

        try{

          logger.warn("This is a warn!----------------------------------------------------------------");
            System.out.println("Retrieve data from database");

            session.beginTransaction();
            Query query = session.createQuery("from Student   where firstName='Mititiuc'");

            List<Student> list = query.getResultList();

            for(Student s : list){
                System.out.println(s);
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
