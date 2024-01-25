package org.kamar.authorization_server.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * configuration for oauth2 authentication.
 * @author kamar baraka.*/

@Configuration
public class AuthServerConfig {


    /**
     * Builds the security filter chain for the OAuth2 authorization server.
     *
     * @param httpSecurity httpSecurity instance to configure
     * @return configured SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerFilterChain(HttpSecurity httpSecurity) throws Exception{

        /*apply this http security instance as the default oauth2 authorization server security filter chain*/
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);

        /*enable OIDC*/
        httpSecurity
                .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(oidc ->
                    oidc.userInfoEndpoint(Customizer.withDefaults())
                            .logoutEndpoint(Customizer.withDefaults())
                )
                .deviceAuthorizationEndpoint(Customizer.withDefaults())
                .deviceVerificationEndpoint(Customizer.withDefaults());


        /*configure the redirection endpoint when the user is not authenticated*/
        httpSecurity
                .exceptionHandling(exceptions -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/login"),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                );

        /*accept access tokens from resource server*/
        httpSecurity.oauth2ResourceServer(
                resourceServer -> resourceServer.jwt(Customizer.withDefaults())
        );

        /*build the filter chain*/
        return httpSecurity.build();
    }

    /**
     * Generates a key pair using RSA algorithm.
     *
     * @return the generated key pair
     * @throws IllegalStateException if the RSA algorithm cannot be instantiated
     */
    private KeyPair generateKeyPair(){

        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * Returns a JWKSource implementation that generates an RSA key pair and creates a JWKSet containing an RSA key.
     *
     * @return a JWKSource instance representing the generated RSA key
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource(){

        /*generate rsa key pair*/
        KeyPair rsaKeyPair = generateKeyPair();
        /*get the private and public key*/
        RSAPublicKey publicKey = (RSAPublicKey) rsaKeyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) rsaKeyPair.getPrivate();

        /*create a json web key*/
        JWK jwk = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .keyUse(KeyUse.SIGNATURE)
                .build();
        JWKSet jwkSet = new JWKSet(jwk);
        return new ImmutableJWKSet<>(jwkSet);
    }

    /**
     * Returns a JwtDecoder implementation that uses the provided JWKSource to decode JWT tokens.
     *
     * @param jwkSource jWKSource to use for decoding JWT tokens
     * @return a JwtDecoder instance that can decode JWT tokens
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource){

        /*get the decoder of the jwk source*/
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    /**
     * Returns an AuthorizationServerSettings instance that represents the settings for the authorization server.
     *
     * @return an AuthorizationServerSettings instance
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings(){
        /*build the authorization server settings*/
        return AuthorizationServerSettings.builder()
                .deviceAuthorizationEndpoint("/connect/v1/device_authorize")
                .deviceVerificationEndpoint("/connect/v1/device_verify")
                .build();
    }

}
