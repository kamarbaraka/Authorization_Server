package org.kamar.authorization_server.user.exception;

import java.io.IOException;

/**
 * the user exceptions.
 * @author kamar baraka.*/

public class UserException extends IOException {

    public UserException(String message) {
        super(message);
    }
}
