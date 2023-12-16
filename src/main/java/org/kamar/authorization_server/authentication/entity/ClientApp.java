package org.kamar.authorization_server.authentication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * representation of the client application.
 * @author kamar baraka.*/


@Entity(name = "client_apps")
@Data
public class ClientApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String clientName;

    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    @ElementCollection
    @CollectionTable(name = "client_redirect_uris")
    @Column(name = "redirect_uri")
    private List<String> redirectUris;

    @ElementCollection
    @CollectionTable(name = "logout_uris")
    @JoinColumn(name = "logout_uri")
    private List<String > postLogoutRedirectUris;

    @ElementCollection
    @CollectionTable(name = "client_scopes")
    @Column(name = "scopes")
    private List<String > scopes;

    @ElementCollection
    @CollectionTable(name = "client_grant_types")
    @Column(name = "grant_type")
    private List<String > grantTypes;

    private LocalDateTime clientIssuedAt = LocalDateTime.now();

    @FutureOrPresent
    private LocalDateTime clientSecretExpiresAt;
}
