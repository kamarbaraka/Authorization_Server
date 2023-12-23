package org.kamar.authorization_server.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kamar.authorization_server.scope.exception.ScopeException;
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
@Log4j2
public class AppGlobalExceptionHandler {

    private final ErrorDetailsUtility utility;
    private final HttpServletRequest request;

    private ErrorDetails assembleErrorDetails(ErrorDetails errorDetails, Exception exception){

        /*log the exception*/
        log.error(exception.getMessage());
        errorDetails.setUrl(request.getRequestURI());
        errorDetails.setRequestMethod(request.getMethod());
        errorDetails.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return errorDetails;
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> handleError(UserException exception){


        /*set the press error and return*/
        ErrorDetails errorDetails = utility.createError(ErrorCode.USER_ERROR_CODE.getErrorCode(),
                ErrorCode.USER_ERROR_CODE.getMessage());

        /*assemble*/
        assembleErrorDetails(errorDetails, exception);

        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler({ScopeException.class})
    public ResponseEntity<ErrorDetails> handleScopeException(ScopeException exception){

        /*create the error details*/
        ErrorDetails errorDetails = utility.createError(ErrorCode.SCOPE.getErrorCode(), ErrorCode.SCOPE.getMessage());

        /*assemble the details*/
        assembleErrorDetails(errorDetails, exception);

        return ResponseEntity.badRequest().build();
    }
}
