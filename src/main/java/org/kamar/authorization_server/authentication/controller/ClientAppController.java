package org.kamar.authorization_server.authentication.controller;


import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.authentication.data.dto.ClientAppCreationDto;
import org.kamar.authorization_server.authentication.data.hateoas.ClientAppModelAssembler;
import org.kamar.authorization_server.authentication.data.model.ClientAppModel;
import org.kamar.authorization_server.authentication.service.ClientAppService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"api/v1/clientApps"})
@RequiredArgsConstructor
public class ClientAppController implements ClientAppApi {

    private final ClientAppModelAssembler assembler;
    private final ClientAppService service;

    @Override
    public ClientAppModel createClientApp(ClientAppCreationDto creationDto) {
        return null;
    }
}
