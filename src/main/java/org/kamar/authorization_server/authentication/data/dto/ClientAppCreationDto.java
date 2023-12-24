package org.kamar.authorization_server.authentication.data.dto;

import java.util.List;

/**
 * This class represents the data transfer object for creating a client app.
 *
 * @author kamar baraka.
 */
public record ClientAppCreationDto(

        String clientName,
        String redirectUri,
        String postLogoutRedirectUri,
        List<String > scopes
) {
}
