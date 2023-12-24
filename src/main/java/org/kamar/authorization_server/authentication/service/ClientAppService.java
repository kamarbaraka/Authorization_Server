package org.kamar.authorization_server.authentication.service;

import org.kamar.authorization_server.authentication.data.dto.ClientAppCreationDto;
import org.kamar.authorization_server.authentication.entity.ClientApp;

/**
 * This interface defines the contract for managing client applications.
 * It provides methods for creating a client application.
 *
 * @since 1.0.1
 *
 * @author kamar baraka.
 */
public interface ClientAppService {


    ClientApp createClientApp(ClientAppCreationDto creationDto);
}
