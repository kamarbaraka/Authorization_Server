package org.kamar.authorization_server.user.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.kamar.authorization_server.user_authorities.entity.UserAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * representation of the app user.
 * @author kamar baraka.*/

@Component("appUser")
@Entity
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "username", nullable = false, updatable = false, unique = true)
    private String username;

    private String firstname;

    private String lastname;

    private String password;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
    fetch = FetchType.EAGER
    )
    private final List<UserAuthority> authorities = new ArrayList<>();

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;

    @Column(name = "date_created", nullable = false, updatable = false)
    private final LocalDate createdOn = LocalDate.now();
}
