package org.kamar.authorization_server.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * utility for creating user-friendly presentation of system errors.
 * @author kamar baraka.*/

@Service
@RequiredArgsConstructor
public class ErrorDetailsUtility {

    private final ErrorDetails errorDetails;

    public ErrorDetails createError(final String errorCode, final String message){

        /*create the user pres error object and return*/
        errorDetails.setErrorCode(errorCode);
        errorDetails.setMessage(message);

        return errorDetails;
    }
}
