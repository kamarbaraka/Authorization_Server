package org.kamar.authorization_server.authentication.service;

import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.authentication.entity.ClientApp;
import org.kamar.authorization_server.authentication.repository.ClientAppRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientAppDetails implements RegisteredClientRepository {

    private final ClientApp clientApp;
    private final ClientAppRepository repository;

    @Override
    public void save(RegisteredClient registeredClient) {

        /*copy registered client properties to the client app and persist*/
        BeanUtils.copyProperties(registeredClient, clientApp);

        /*assign the default grant types*/
        clientApp.getAuthorizationGrantTypes().addAll(List.of(AuthorizationGrantType.AUTHORIZATION_CODE,
                AuthorizationGrantType.REFRESH_TOKEN));
        /*assign the authentication methods*/
        clientApp.getClientAuthenticationMethods().add(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);

        repository.save(clientApp);

    }

    @Override
    public RegisteredClient findById(String id) {

        /*find the registered client by id*/
        return repository.findById(id).orElseThrow();
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {

        /*find the client by client id*/
        return repository.findClientAppByClientId(clientId).orElseThrow();
    }

}