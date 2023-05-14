package com.luv2code.junitdemo;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class CamelCase extends DisplayNameGenerator.Standard {
    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
        return this.generateNameForClass(super.generateDisplayNameForClass(testClass));
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
        return this.generateNameForClass(super.generateDisplayNameForNestedClass(nestedClass));
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        return this.generateNameForMethod(testMethod.getName());
    }

    public String generateNameForMethod(String name){

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0;i<name.length();i++){
            if(Character.isUpperCase(name.charAt(i))){
                stringBuilder.append(" ");
            }
            stringBuilder.append(Character.toLowerCase(name.charAt(i)));
        }

        return stringBuilder.toString();
    }

    private String generateNameForClass(String name){

        System.out.println(name);
        String newString = "class "+name;
        System.out.println(newString);
        return newString;
    }
}
