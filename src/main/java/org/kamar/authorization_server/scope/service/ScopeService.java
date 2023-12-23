package org.kamar.authorization_server.scope.service;

import org.kamar.authorization_server.scope.entity.Scope;

/**
 * the scope service contract.
 *
 * @author kamar baraka.*/


public interface ScopeService {

    Scope createScope(Scope scope);
}
