package org.app.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashMap;

@Component("MyClient")
public class Client {
    @NotBlank(message = "Can't be empty")
    @Size(min=2,message = "At least 2 characters are required")
    private String firstName;

    @NotBlank(message = "Can't be empty")
    @Size(min=2,message = "At least 2 characters are required")
    private String lastName;

    private String country;

    private String[] language;

    @Value("#{country}")
    private LinkedHashMap<String,String> countryList;


    @Value("#{language}")
    private LinkedHashMap<String,String> languageList;

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

    public String getCountry() {
        return country;
    }

    public String[] getLanguage() {
        return language;
    }

    public LinkedHashMap<String, String> getCountryList() {
        return countryList;
    }

    public LinkedHashMap<String, String> getLanguageList() {
        return languageList;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLanguage(String[] language) {
        this.language = language;
    }
}
