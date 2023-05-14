package org.java.app;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.util.Arrays;
import java.util.HashMap;

public class Student {

    @NotNull(message = "Can't be null",groups = Group1.class)
    @NotBlank(message = "Can't be empty",groups = Group1.class)
    @Size(min = 3, max = 20, message = "You name is too long or short",groups = Group1.class)
    private String firstName;
    @NotNull(message = "Can't be null",groups = Group1.class)
    @NotBlank(message = "Can't be empty",groups = Group1.class)
    @Size(min = 3, max = 20, message = "You name is too long or short",groups = Group1.class)
    private String lastName;

    @Min(value = 18,message = "Too young",groups = Group1.class)
    @Max(value = 100, message = "Too old",groups = Group1.class)
    private int age;

    @Value("#{countries}")
    private HashMap<String,String> country;
    @Value("#{jobs}")
    private HashMap<String,String> job;
    @Value("#{langs}")
    private HashMap<String,String> lang;

    @NotNull(message = "Can't be null",groups = Group2.class)
    @NotEmpty(message = "can't be empty",groups = Group2.class)
    private String countries;
    @NotNull(message = "Can't be null",groups = Group2.class)
    private String jobs;
    @NotNull(message = "Can't be null")
    private String[] langs;


    public Student(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    public Student() { }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public HashMap<String, String> getCountry() {
        return country;
    }

    public void setCountry(HashMap<String, String> country) {
        this.country = country;
    }

    public HashMap<String, String> getJob() {
        return job;
    }

    public void setJob(HashMap<String, String> job) {
        this.job = job;
    }

    public HashMap<String, String> getLang() {
        return lang;
    }

    public void setLang(HashMap<String, String> lang) {
        this.lang = lang;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String[] getLangs() {
        return langs;
    }

    public void setLangs(String[] langs) {
        this.langs = langs;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", countries='" + countries + '\'' +
                ", jobs='" + jobs + '\'' +
                ", langs=" + Arrays.toString(langs) +
                '}';
    }
}
