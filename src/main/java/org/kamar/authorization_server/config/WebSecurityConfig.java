package org.kamar.authorization_server.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerMetadataEndpointConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;

/**
 * The WebSecurityConfig class is a configuration class that sets up Spring Security for the web application.
 *
 * @author samson baraka <kamar254baraka@gmail.com>.
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {


    /**
     * This method configures the security filter chain for the HttpSecurity object.
     *
     * @param httpSecurity The HttpSecurity object to configure.
     * @return The configured SecurityFilterChain object.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{

        /*enable form login*/
        httpSecurity.formLogin(Customizer.withDefaults());

        /*authenticate all endpoints*/

        /*disable csrf*/
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        /*build and return*/
        return httpSecurity.build();
    }

    /**
     * Creates and returns a PasswordEncoder.
     *
     * @return A PasswordEncoder object.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){

        HashMap<String, PasswordEncoder> passwordEncoders = new HashMap<>();
        passwordEncoders.put("bcrypt", new BCryptPasswordEncoder());
        passwordEncoders.put(null, new PlainTextPasswordEncoder());

        return new DelegatingPasswordEncoder("bcrypt", passwordEncoders);
    }

}

/**
 * The PlainTextPasswordEncoder class implements the PasswordEncoder interface and provides
 * methods to encode and verify passwords using plain text encoding.
 *
 * @author samson baraka <kamar254baraka@gmail.com>.
 */
class PlainTextPasswordEncoder implements PasswordEncoder{


    /**
     * Converts a CharSequence to a String representation.
     *
     * @param rawPassword The CharSequence to be encoded.
     * @return The encoded password as a String.
     */
    @Override
    public String encode(CharSequence rawPassword) {
        /*convert the sequence to string and return*/
        return rawPassword.toString();
    }

    /**
     * Determines whether a raw password matches an encoded password.
     *
     * @param rawPassword The raw password to be checked.
     * @param encodedPassword The encoded password to be compared with the raw password.
     * @return true if the raw password matches the encoded password, false otherwise.
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        /*verify the password*/
        return rawPassword.toString().equals(encodedPassword);
    }
}
