package org.kamar.authorization_server.scope.service;

import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.scope.entity.Scope;
import org.kamar.authorization_server.scope.exception.ScopeException;
import org.kamar.authorization_server.scope.repository.ScopeRepository;
import org.kamar.authorization_server.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * implementation of the scope service contract.
 *
 * @author kamar baraka.*/


@Service
@RequiredArgsConstructor
public class ScopeServiceImpl implements ScopeService{

    private final ScopeRepository repository;
    private final UserRepository userRepository;

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

    @Override
    public List<Scope> getUserScopesByUsername(final String username) {

        /*get the user scopes*/
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ScopeException("no such user to get the scopes!"))
                .getAuthorities();

    }

    @Override
    public Scope getScopeByAuthority(String authority) {

        /*get the scope by authority*/
        return repository.findById(authority)
                .orElseThrow(() -> new ScopeException("no scope with such authority!"));

    }
}
