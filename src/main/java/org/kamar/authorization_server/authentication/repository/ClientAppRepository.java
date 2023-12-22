package org.kamar.authorization_server.authentication.repository;

import org.kamar.authorization_server.authentication.entity.ClientApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * the {@link org.kamar.authorization_server.authentication.entity.ClientApp} repository.
 *
 * @author kamar baraka.*/

@Repository
public interface ClientAppRepository extends JpaRepository<ClientApp, String > {

    Optional<ClientApp> findClientAppByClientId(String clientId);
}
