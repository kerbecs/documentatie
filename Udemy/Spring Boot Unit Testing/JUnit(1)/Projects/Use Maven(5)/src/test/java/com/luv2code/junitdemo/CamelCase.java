package com.luv2code.junitdemo;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class CamelCase extends DisplayNameGenerator.Standard {
    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        return generateNameForMethod(testMethod.getName());
    }

    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
        return generateNameForClass(super.generateDisplayNameForClass(testClass));
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
        return generateNameForClass(super.generateDisplayNameForNestedClass(nestedClass));
    }

    public String generateNameForClass(String s){
        return "Class: " + s;
    }
    public String generateNameForMethod(String s){
        StringBuilder string = new StringBuilder();
        for(int i = 0;i<s.length();i++){
            if(Character.isUpperCase(s.charAt(i))) {
                string.append(" ");
                string.append(Character.toLowerCase(s.charAt(i)));
            }
            else
                string.append(s.charAt(i));
        }

        return string.toString();
    }
}
