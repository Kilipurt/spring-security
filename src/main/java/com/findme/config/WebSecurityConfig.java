package com.findme.config;

import com.findme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DriverManagerDataSource dataSource;

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .and()
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT U.PHONE AS USERNAME, U.PASSWORD AS PASSWORD, 1 AS ENABLED FROM USERS U " +
                        "WHERE PHONE = ?")
                .authoritiesByUsernameQuery("SELECT U.PHONE AS USERNAME, R.NAME AS ROLE FROM USERS U " +
                        "JOIN USER_ROLE UR ON UR.USER_ID = U.ID JOIN ROLE R ON UR.ROLE_ID = R.ID WHERE U.PHONE = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user*").hasRole("USER")
                .antMatchers("/user*").hasRole("ADMIN")
                .antMatchers("/admin*").hasRole("ADMIN")
                .antMatchers("/user*").hasRole("SUPER_ADMIN")
                .antMatchers("/admin*").hasRole("SUPER_ADMIN")
                .antMatchers("/super-admin").hasRole("SUPER_ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .formLogin()
                .usernameParameter("phone")
                .passwordParameter("password")
                .loginPage("/")
                .failureUrl("/")
                .loginProcessingUrl("/login")
                .permitAll();
    }
}