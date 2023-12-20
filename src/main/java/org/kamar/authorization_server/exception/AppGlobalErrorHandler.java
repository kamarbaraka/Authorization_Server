package org.kamar.authorization_server.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.user.exception.UserException;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

/**
 * the global error handler for this application.
 * @author kamar baraka.*/

@RestControllerAdvice
@RequiredArgsConstructor
public class AppGlobalErrorHandler {

    private final UserPresErrorUtility utility;
    private final HttpServletRequest request;

    @ExceptionHandler(UserException.class)
    public ResponseEntity<UserPresError> handleError(){

        /*set the press error and return*/
        UserPresError userPresError = utility.createError(ErrorCode.USER_ERROR_CODE.getErrorCode(),
                ErrorCode.USER_ERROR_CODE.getMessage());

        userPresError.setUrl(request.getRequestURI());
        userPresError.setRequestMethod(request.getMethod());
        userPresError.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.badRequest().body(userPresError);
    }
}
