package org.java.app;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "org.java.app")
@EnableWebMvc
public class appContext implements WebMvcConfigurer {
   @Bean
   public ViewResolver ViewResolver(){
       InternalResourceViewResolver resolver = new InternalResourceViewResolver();
       resolver.setPrefix("/WEB-INF/view/");
       resolver.setSuffix(".jsp");

       return resolver;
   }
   @Bean
    public Student student(){
       return new Student();
   }
   @Bean
    public PropertiesFactoryBean countries() {
       PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
       factoryBean.setLocation(new ClassPathResource("countries.properties"));

       return factoryBean;
   }
   @Bean
   public PropertiesFactoryBean jobs() {
       PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
       factoryBean.setLocation(new ClassPathResource("jobs.properties"));

       return factoryBean;
   }
   @Bean
    public PropertiesFactoryBean langs(){
       PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
       factoryBean.setLocation(new ClassPathResource("languages.properties"));

       return factoryBean;
   }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/CSS/**").addResourceLocations("/WEB-INF/CSS/");
    }
}
