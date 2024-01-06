package org.kamar.authorization_server.authentication.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.authentication.data.dto.ClientAppCreationDto;
import org.kamar.authorization_server.authentication.data.hateoas.ClientAppModelAssembler;
import org.kamar.authorization_server.authentication.data.model.ClientAppModel;
import org.kamar.authorization_server.authentication.entity.ClientApp;
import org.kamar.authorization_server.authentication.service.ClientAppService;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The ClientAppController class is responsible for handling requests related to client applications.
 * It implements the ClientAppApi interface to provide the necessary methods for client application management.
 *
 * @author kamar baraka.
 */
@RestController
@RequestMapping(value = {"api/v1/clientApps"})
@RequiredArgsConstructor
public class ClientAppController implements ClientAppApi {

    private final ClientAppModelAssembler assembler;
    private final ClientAppService service;

    @PostMapping
    @Operation(
                tags = {"Client Management"},
                summary = "Api to create a client application.",
                description = "create a client application",
                requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(),
                parameters = {},
                responses = {},
                security = {},
                servers = {}
    )
    @Override
    public ResponseEntity<ClientAppModel> createClientApp(@RequestBody ClientAppCreationDto creationDto) {
        /*create the client*/
        ClientApp clientApp = service.createClientApp(creationDto);
        /*create the client model payload and return*/
        ClientAppModel model = assembler.toModel(clientApp);

        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }
}
