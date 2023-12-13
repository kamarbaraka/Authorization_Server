package org.kamar.authorization_server.user_authorities.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * representation of user authorities.
 * @author kamar baraka.*/

@Entity
@Data
public class UserAuthority implements GrantedAuthority {

    @Id
    private String authority;
}
