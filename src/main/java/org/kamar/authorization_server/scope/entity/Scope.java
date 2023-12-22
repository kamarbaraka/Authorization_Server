package org.kamar.authorization_server.scope.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * representation of user authorities.
 * @author kamar baraka.*/

@Entity(name = "user_authorities")
@Data
public class Scope implements GrantedAuthority {

    @Id
    private String authority;
}
