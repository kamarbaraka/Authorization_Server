package org.kamar.authorization_server.authentication.service;

import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.authentication.entity.ClientApp;
import org.kamar.authorization_server.authentication.repository.ClientAppRepository;
import org.kamar.authorization_server.scope.entity.Scope;
import org.springframework.beans.BeanUtils;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientAppDetails implements RegisteredClientRepository {

    private final ClientApp clientApp;
    private final ClientAppRepository repository;

    @Override
    public void save(RegisteredClient registeredClient) {

        /*copy registered client properties to the client app and persist*/
        BeanUtils.copyProperties(registeredClient, clientApp);

        repository.save(clientApp);

    }

    @Override
    public RegisteredClient findById(String id) {

        /*find the registered client by id*/
        ClientApp client = repository.findById(id).orElseThrow();

        /*get the registered client and return*/
        return getRegisteredClientFromClientApp(client);


    }

    @Override
    public RegisteredClient findByClientId(String clientId) {

        /*find the client by client id*/
        ClientApp client = repository.findClientAppByClientId(clientId).orElseThrow();

        /*get the registered client and return*/
        return getRegisteredClientFromClientApp(client);
    }

    private RegisteredClient getRegisteredClientFromClientApp(ClientApp entity){

        /*create the registered client*/
        RegisteredClient.Builder rCBuilder = RegisteredClient.withId(entity.getId());
        rCBuilder.clientName(entity.getClientName());
        rCBuilder.clientId(entity.getClientId());
        rCBuilder.clientSecret(entity.getClientSecret());

        /*set the authorization grant types*/
        entity.getAuthorizationGrantTypes()
                .forEach(rCBuilder::authorizationGrantType);

        /*set the scopes*/
        entity.getScopes().stream().map(Scope::getAuthority).forEach(rCBuilder::scope);

        /*set the redirect uris*/
        rCBuilder.redirectUri(entity.getRedirectUri());
        rCBuilder.postLogoutRedirectUri(entity.getPostLogoutRedirectUris());

        return rCBuilder.build();
    }

}
