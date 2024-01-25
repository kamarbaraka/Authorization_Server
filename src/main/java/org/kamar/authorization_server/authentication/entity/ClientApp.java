package org.kamar.authorization_server.authentication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;

/**
 * representation of the client application.
 * @author kamar baraka.*/


@Entity(name = "client_apps")
@Data
@Component
public class ClientApp {

    @Transient
    private static final String CLIENT_APP_ID = "client_app";

    @Id
    private String  id = UUID.randomUUID().toString();

    private String clientId = UUID.randomUUID().toString();

    private String clientName;

    @Column(name = "client_secret")
    private String clientSecret = UUID.randomUUID().toString();

    @Column(name = "redirect_uri")
    private  String redirectUri;

    @Column(name = "logout_uri")
    private String postLogoutRedirectUri;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "client_scopes", joinColumns = {
            @JoinColumn(name = CLIENT_APP_ID)
    })
    @Column(name = "scopes")
    private final Set<String> scopes = new LinkedHashSet<>() ;

    @Column(name = "authentication_method")
    private ClientAuthenticationMethod clientAuthenticationMethod = ClientAuthenticationMethod.CLIENT_SECRET_POST;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "client_grants", joinColumns = {
            @JoinColumn(name = CLIENT_APP_ID)
    })
    @Column(name = "grant_type")
    private final Set<AuthorizationGrantType> authorizationGrantTypes = new LinkedHashSet<>();

    private Instant clientIdIssuedAt = Instant.now();

    @FutureOrPresent
    private Instant clientSecretExpiresAt;

    private Instant updatedOn = Instant.now();
}
