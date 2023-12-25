package org.kamar.authorization_server.authentication.controller;

import org.kamar.authorization_server.authentication.data.dto.ClientAppCreationDto;
import org.kamar.authorization_server.authentication.data.model.ClientAppModel;
import org.springframework.http.ResponseEntity;

public interface ClientAppApi {

    ResponseEntity<ClientAppModel> createClientApp(ClientAppCreationDto creationDto);
}
