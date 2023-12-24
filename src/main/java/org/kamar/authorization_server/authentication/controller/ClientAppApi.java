package org.kamar.authorization_server.authentication.controller;

import org.kamar.authorization_server.authentication.data.dto.ClientAppCreationDto;
import org.kamar.authorization_server.authentication.data.model.ClientAppModel;

public interface ClientAppApi {

    ClientAppModel createClientApp(ClientAppCreationDto creationDto);
}
