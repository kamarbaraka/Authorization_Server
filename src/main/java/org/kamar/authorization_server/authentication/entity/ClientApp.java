package org.kamar.authorization_server.authentication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kamar.authorization_server.scope.entity.Scope;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * representation of the client application.
 * @author kamar baraka.*/


@Entity(name = "client_apps")
@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class ClientApp extends RegisteredClient {

    @Transient
    private static final String CLIENT_APP_ID = "client_app_id";

    @Id
    private String  id = UUID.randomUUID().toString();

    private String clientId = UUID.randomUUID().toString();

    private String clientName;

    @Column(name = "client_secret")
    private String clientSecret = UUID.randomUUID().toString();

    @ElementCollection
    @CollectionTable(name = "client_redirect_uris", joinColumns = {
            @JoinColumn(name = CLIENT_APP_ID)
    })
    @Column(name = "redirect_uri")
    private Set<String> redirectUris = Collections.emptySet();

    @ElementCollection
    @CollectionTable(name = "client_logout_uris", joinColumns = {
        @JoinColumn(name = CLIENT_APP_ID)
    })
    @Column(name = "logout_uri")
    private Set<String > postLogoutRedirectUris = Collections.emptySet();

    @ElementCollection
    @CollectionTable(name = "client_app_scopes", joinColumns = {
            @JoinColumn(name = CLIENT_APP_ID)
    })
    @Column(name = "scopes")
    private Set<String > scopes = Collections.emptySet();

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(joinColumns = {@JoinColumn(name = CLIENT_APP_ID)})
    private Set<ClientAuthenticationMethod> clientAuthenticationMethods = Set.of(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);

    @ElementCollection
    @CollectionTable(name = "client_app_grant_types", joinColumns = {
            @JoinColumn(name = CLIENT_APP_ID)
    })
    @Column(name = "grant_type")
    private Set<AuthorizationGrantType> authorizationGrantTypes = Set.of(AuthorizationGrantType.AUTHORIZATION_CODE,
            AuthorizationGrantType.REFRESH_TOKEN);

    private Instant clientIdIssuedAt = Instant.now();

    @FutureOrPresent
    private Instant clientSecretExpiresAt;

    private Instant updatedOn = Instant.now();
}
