package com.test.app;

import org.springframework.stereotype.Component;

@Component("MyCustomer")
public class Customer {

    @CourseCode(value="Fun", message="Hopaa")
    private String courseCode;

    public Customer() {}

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
