package org.kamar.authorization_server.scope.repository;

import org.kamar.authorization_server.scope.entity.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * contract to manage user authorities.
 * @author kamar baraka.*/

@Repository
public interface ScopeRepository extends JpaRepository<Scope, String > {
}
