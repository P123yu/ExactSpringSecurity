package com.pracSecurity.config;

import com.pracSecurity.serviceimpl.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringConfig {


    // step 1
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf-> csrf.disable()).authorizeHttpRequests(req->
                req.requestMatchers("register","login","get").permitAll()
        ).build();
    }

    // convert plain text password into Bcryptpassword

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    // step 4
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        //return authenticationConfiguration.getAuthenticationManager();
        return new ProviderManager(authenticationProvider());
    }


    @Autowired
    private SpringServiceConfig springServiceConfig;


    // step 5
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();

        // this line is searching the row based on username or useremail in db

        // here setUserDetailsService this require UserDetailsService return type
        daoAuthenticationProvider.setUserDetailsService(springServiceConfig);

        // this line is taking password which we enter during login and encode that password
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }






}
