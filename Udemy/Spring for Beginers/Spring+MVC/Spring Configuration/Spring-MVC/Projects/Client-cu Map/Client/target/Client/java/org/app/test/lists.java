package org.app.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class lists {

    @Value("#{country}")
    public LinkedHashMap <String, String> countriesList;
    @Value("#{language}")
    public LinkedHashMap <String, String> languagesList;

    @Bean
    public LinkedHashMap<String, String> countryList(){
        return countriesList;
    }
    @Bean
    public LinkedHashMap<String, String> languageList(){
        return languagesList;
    }
}
