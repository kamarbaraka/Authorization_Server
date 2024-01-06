package org.kamar.authorization_server.authentication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;
import org.kamar.authorization_server.scope.entity.Scope;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
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
    private static final String CLIENT_APP_ID = "client_app_id";

    @Id
    private String  id = UUID.randomUUID().toString();

    private String clientId = UUID.randomUUID().toString();

    private String clientName;

    @Column(name = "client_secret")
    private String clientSecret = UUID.randomUUID().toString();

    @Column(name = "redirect_uri")
    private  String redirectUri;

    @Column(name = "logout_uri")
    private String  postLogoutRedirectUris ;

    @ManyToMany
    @JoinTable(name = "client_scopes", joinColumns = {
            @JoinColumn(name = CLIENT_APP_ID)
    })
    @Column(name = "scopes")
    private final Set<Scope > scopes = new HashSet<>() ;

    @Column(name = "authentication_method")
    private ClientAuthenticationMethod clientAuthenticationMethod = ClientAuthenticationMethod.PRIVATE_KEY_JWT;

    @ElementCollection
    @JoinTable(name = "client_grants", joinColumns = {
            @JoinColumn(name = CLIENT_APP_ID)
    })
    @Column(name = "grant_type")
    private final Set<AuthorizationGrantType> authorizationGrantTypes = new HashSet<>(
            List.of(AuthorizationGrantType.AUTHORIZATION_CODE, AuthorizationGrantType.REFRESH_TOKEN)
    );

    private Instant clientIdIssuedAt = Instant.now();

    @FutureOrPresent
    private Instant clientSecretExpiresAt;

    private Instant updatedOn = Instant.now();
}
