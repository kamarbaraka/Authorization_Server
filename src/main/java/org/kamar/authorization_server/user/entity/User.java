package org.kamar.authorization_server.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.kamar.authorization_server.scope.entity.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * representation of the app user.
 * @author kamar baraka.*/

@Entity(name = "users")
@Data
@Component
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "username", nullable = false, updatable = false, unique = true)
    private String username;

    @Size(min = 1, max = 50, message = "firstname too long!")
    @Column(name = "firstname")
    private String firstname;

    @Size(min = 1, max = 50, message = "lastname too long!")
    @Column(name = "lastname")
    private String lastname;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
    fetch = FetchType.EAGER
    )
    @JoinTable(name = "users_authorities", joinColumns = {@JoinColumn(name = "app_user")},
            inverseJoinColumns = {@JoinColumn(name = "authority")})
    private final List<Scope> authorities = new ArrayList<>();

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;

    @Column(name = "date_created", nullable = false, updatable = false)
    private final Instant createdOn = Instant.now();

    private Instant updatedOn = Instant.now();

}
