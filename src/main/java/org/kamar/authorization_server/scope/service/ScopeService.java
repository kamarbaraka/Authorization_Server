package org.kamar.authorization_server.scope.service;

import org.kamar.authorization_server.scope.entity.Scope;

import java.util.List;

/**
 * the scope service contract.
 *
 * @author kamar baraka.*/


public interface ScopeService {

    Scope createScope(Scope scope);

    List<Scope> getUserScopesByUsername(String username);

    Scope getScopeByAuthority(String authority);
}
