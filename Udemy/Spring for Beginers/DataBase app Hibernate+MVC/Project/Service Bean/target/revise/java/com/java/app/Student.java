package com.java.app;

import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.HashMap;

public class Student {
    private String firstName;
    private String lastName;

    private String[] languages;
    private Integer age;

    @Value("#{language}")
    private HashMap<String,String> languagesList;
    @Value("#{age}")
    private HashMap<Integer,Integer> ageList;

    public Student() {
    }

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

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public HashMap<String, String> getLanguagesList() {
        return languagesList;
    }

    public void setLanguagesList(HashMap<String, String> languagesList) {
        this.languagesList = languagesList;
    }

    public HashMap<Integer, Integer> getAgeList() {
        return ageList;
    }

    public void setAgeList(HashMap<Integer, Integer> ageList) {
        this.ageList = ageList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", languagesList=" + Arrays.toString(languages) +
                '}';
    }
}
