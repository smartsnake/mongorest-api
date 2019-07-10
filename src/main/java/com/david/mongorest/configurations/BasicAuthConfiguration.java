package com.david.mongorest.configurations;


import com.david.mongorest.controllers.UserRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class BasicAuthConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    UserRestController userDetailsService ;
//
//
//    @Autowired
//    public void configAuthBuilder(AuthenticationManagerBuilder builder) throws Exception {
//        builder.userDetailsService(userDetailsService);
//    }
//
//    // Create 2 users for demo
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }
//
//    // Secure the endpoins with HTTP Basic authentication
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                //HTTP Basic authentication
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/invoice/**").permitAll()//hasAnyAuthority("USER, ADMIN")
//                .antMatchers(HttpMethod.POST, "/invoice").permitAll()//hasAnyAuthority("USER, ADMIN")
//                .antMatchers(HttpMethod.PUT, "/invoice/**").permitAll()//hasAnyAuthority("USER, ADMIN")
//                .antMatchers(HttpMethod.PATCH, "/invoice/**").permitAll()//hasAnyAuthority("USER, ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/invoice/**").permitAll()//hasAnyAuthority("USER, ADMIN")
//                .antMatchers(HttpMethod.GET, "/invoice/searchByCustomerID**").permitAll()//hasAnyAuthority("USER, ADMIN")
//                .antMatchers(HttpMethod.GET, "/invoice/totalSaleLessThan**").permitAll()//hasAnyAuthority("USER, ADMIN")
//                .antMatchers(HttpMethod.GET, "/invoice/totalSaleGreaterThan**").permitAll()//hasAnyAuthority("USER, ADMIN")
//                .and()
//                .csrf().disable()
//                .formLogin().disable();
//    }
//
//}