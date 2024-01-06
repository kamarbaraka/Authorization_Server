package org.kamar.authorization_server.scope.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * representation of user authorities.
 * @author kamar baraka.*/

@Entity(name = "scopes")
@Data
@Component
public class Scope implements GrantedAuthority {
    @Id
    private String authority;

    private Instant updatedOn = Instant.now();
}
