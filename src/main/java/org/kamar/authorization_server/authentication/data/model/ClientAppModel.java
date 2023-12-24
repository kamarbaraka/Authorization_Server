package org.kamar.authorization_server.authentication.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

/**
 * Represents a client application in the system.
 *
 * This class extends the `RepresentationModel` class, which provides support for HATEOAS.
 *
 * It contains properties to store the client's information such as name, ID, and secret.
 *
 * @author kamar baraka.
 */

@Component
@Data
@EqualsAndHashCode(callSuper = true)
public class ClientAppModel extends RepresentationModel<ClientAppModel> {

    private String clientName;

    private String clientId;

    private String clientSecret;
}
