package org.kamar.authorization_server.scope.exception;


/**
 * exceptions arising from scope operation.
 * @author kamar baraka.*/

public class ScopeException extends RuntimeException {
    public ScopeException(String message) {
        super(message);
    }
}
