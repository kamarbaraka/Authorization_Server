package org.kamar.authorization_server.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.user.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * the global error handler for this application.
 * @author kamar baraka.*/

@RestControllerAdvice
@RequiredArgsConstructor
public class AppGlobalExceptionHandler {

    private final ErrorDetailsUtility utility;
    private final HttpServletRequest request;

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> handleError(){

        /*set the press error and return*/
        ErrorDetails errorDetails = utility.createError(ErrorCode.USER_ERROR_CODE.getErrorCode(),
                ErrorCode.USER_ERROR_CODE.getMessage());

        errorDetails.setUrl(request.getRequestURI());
        errorDetails.setRequestMethod(request.getMethod());
        errorDetails.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.badRequest().body(errorDetails);
    }
}
