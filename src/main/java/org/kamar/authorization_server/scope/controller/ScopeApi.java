package org.kamar.authorization_server.scope.controller;

import org.kamar.authorization_server.scope.data.model.ScopeModel;
import org.kamar.authorization_server.scope.entity.Scope;
import org.springframework.http.ResponseEntity;

/**
 * the scope Api contract.
 *
 * @author kamar baraka.*/

public interface ScopeApi {

    ResponseEntity<ScopeModel> createScope(Scope scope);
}
