package org.kamar.authorization_server.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.authentication.AuthenticationManagerBeanDefinitionParser;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerMetadataEndpointConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;

/**
 * configuration for the web security.
 * @author kamar baraka.*/

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {


    @Bean(name = "appSecFilterChain")
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   AuthenticationManager manager)throws Exception{

        /*configure authorization server*/

        /*enable form login*/
        httpSecurity.authenticationManager(manager);
        httpSecurity.formLogin(httpSecurityFormLoginConfigurer -> {});

        /*authenticate all endpoints*/

        /*disable csrf*/
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        /*build and return*/
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        HashMap<String, PasswordEncoder> passwordEncoders = new HashMap<>();
        passwordEncoders.put("bcrypt", new BCryptPasswordEncoder());

        return new DelegatingPasswordEncoder("bcrypt", passwordEncoders);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder){

        return builder.getObject();
    }
}
