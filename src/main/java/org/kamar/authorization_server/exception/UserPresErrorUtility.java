package org.kamar.authorization_server.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * utility for creating user-friendly presentation of system errors.
 * @author kamar baraka.*/

@Service
@RequiredArgsConstructor
public class UserPresErrorUtility {

    private final UserPresError userPresError;

    public UserPresError createError(final String errorCode, final String message){

        /*create the user pres error object and return*/
        userPresError.setErrorCode(errorCode);
        userPresError.setMessage(message);

        return userPresError;
    }
}
