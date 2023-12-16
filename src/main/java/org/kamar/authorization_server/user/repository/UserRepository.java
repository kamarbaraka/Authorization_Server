package org.kamar.authorization_server.user.repository;

import org.kamar.authorization_server.user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * the user repository.
 * @author kamar baraka.*/

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findUserByUsername(String username);
}
