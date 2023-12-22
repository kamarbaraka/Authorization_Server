package org.kamar.authorization_server.exception;


import lombok.Data;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;


/**
 * class to represent internal errors in a user-friendly manner.
 * @author kamar baraka.*/

@Data
@Component
@Description("component to represent system errors.")
public class ErrorDetails {

    private String errorCode;

    private String message;

    private int statusCode;

    private String url = "Not available!";

    private String requestMethod = "Not available!";

}
