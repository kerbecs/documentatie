package com.java.MyApp;

import org.hibernate.SessionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet(name = "Servlet", value = "/Servlet")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String jdb = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
      String user = "testuser";
      String pass = "Frb2eshox!";

      try{
          Class.forName("com.mysql.jdbc.Driver");
          Connection connection = DriverManager.getConnection(jdb,user,pass);
          PrintWriter printWriter = response.getWriter();
          printWriter.println("Success Connected");
          connection.close();
      }
      catch (Exception e){
          e.printStackTrace();
        }
    }

}

