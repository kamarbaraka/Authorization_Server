package org.kamar.authorization_server.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;

/**
 * configuration for the web security.
 * @author kamar baraka.*/

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   @Qualifier("appUserDetailsService")
                                                   UserDetailsService userDetailsService)throws Exception{

        /*configure user details*/
        httpSecurity.userDetailsService(userDetailsService);

        /*configure form login*/
        httpSecurity.formLogin(Customizer.withDefaults());

        /*build and return*/
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        HashMap<String, PasswordEncoder> passwordEncoders = new HashMap<>();
        passwordEncoders.put("bcrypt", new BCryptPasswordEncoder());

        return new DelegatingPasswordEncoder("bcrypt", passwordEncoders);
    }
}
