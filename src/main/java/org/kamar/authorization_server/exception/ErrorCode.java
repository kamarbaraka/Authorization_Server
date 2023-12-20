package org.kamar.authorization_server.exception;


import lombok.Data;
import lombok.Getter;

/**
 * representation of all the available error codes.
 * @author kamar baraka.*/


@Getter
public enum ErrorCode {

    USER_ERROR_CODE("0001", "System unable to process your request at this time, Pleas Contact Support!");

    private final String errorCode;
    private final String message;

    ErrorCode(final String errorCode, final String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
