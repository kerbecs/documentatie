package org.java.app.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

@Controller
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication().withUser(userBuilder.username("kerbecs").password("test123").roles("EMPLOYEE","MANAGER","ADMIN"))
                .withUser(userBuilder.username("Alin").password("test123").roles("EMPLOYEE","MANAGER"))
                .withUser(userBuilder.username("Alex").password("test123").roles("EMPLOYEE"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/resources/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/").hasRole("EMPLOYEE")
                .antMatchers("/leaders/**").hasRole("MANAGER")
                .antMatchers("/systems/**").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/confirmLogin").permitAll()
                .and().logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/access-denied");
    }
}
