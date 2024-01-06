package org.kamar.authorization_server.authentication.service;

import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.authentication.data.dto.ClientAppCreationDto;
import org.kamar.authorization_server.authentication.entity.ClientApp;
import org.kamar.authorization_server.authentication.repository.ClientAppRepository;
import org.kamar.authorization_server.scope.entity.Scope;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientAppServiceImpl implements ClientAppService {

    private final ClientAppRepository repository;
    private final ClientApp clientApp;
    private final Scope scope;

    @Override
    public ClientApp createClientApp(ClientAppCreationDto creationDto) {

        /*configure the client app*/
        List<Scope> scopes = creationDto.scopes().stream().map(scopeString -> {
            scope.setAuthority(scopeString);
            return scope;
        }).toList();
        clientApp.setClientName(creationDto.clientName());
        clientApp.setRedirectUri(creationDto.redirectUri());
        clientApp.setPostLogoutRedirectUris(creationDto.postLogoutRedirectUri());
        clientApp.getScopes().addAll(scopes);

        /*set the defaults and save*/
        return repository.save(clientApp);
    }
}
