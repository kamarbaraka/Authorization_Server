package org.kamar.authorization_server.authentication.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.List;
import org.kamar.authorization_server.authentication.data.dto.ClientAppCreationDto;
import org.kamar.authorization_server.authentication.entity.ClientApp;
import org.kamar.authorization_server.authentication.repository.ClientAppRepository;
import org.kamar.authorization_server.scope.entity.Scope;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ClientAppServiceImpl implements ClientAppService {

    private final ClientAppRepository repository;
    private final ClientApp clientApp;
    private final Scope scope;

    @Override
    public ClientApp createClientApp(ClientAppCreationDto creationDto) {

        /*configure the client app*/
        clientApp.setClientName(creationDto.clientName());
        clientApp.setRedirectUri(creationDto.redirectUri());
        clientApp.setPostLogoutRedirectUri(creationDto.postLogoutRedirectUri());

        clientApp.getAuthorizationGrantTypes().addAll(Set.of(
                AuthorizationGrantType.AUTHORIZATION_CODE,
                AuthorizationGrantType.REFRESH_TOKEN
        ));

        clientApp.getScopes().addAll(Set.of(
                OidcScopes.OPENID,
                OidcScopes.PROFILE
        ));

        /*set the defaults and save*/
        return repository.save(clientApp);
    }
}
