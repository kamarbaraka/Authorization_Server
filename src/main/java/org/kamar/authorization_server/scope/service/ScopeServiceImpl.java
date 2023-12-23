package org.kamar.authorization_server.scope.service;

import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.scope.entity.Scope;
import org.kamar.authorization_server.scope.repository.ScopeRepository;
import org.springframework.stereotype.Service;

/**
 * implementation of the scope service contract.
 *
 * @author kamar baraka.*/


@Service
@RequiredArgsConstructor
public class ScopeServiceImpl implements ScopeService{

    private final ScopeRepository repository;

    /**
     * Creates a new scope and persists it.
     *
     * @param scope the scope object to be created and persisted
     * @return the created scope after persisting it
     */
    @Override
    public Scope createScope(Scope scope) {

        /*persist the scope and return*/
        return repository.save(scope);
    }
}
