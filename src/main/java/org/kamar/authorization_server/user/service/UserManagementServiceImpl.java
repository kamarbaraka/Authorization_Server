package org.kamar.authorization_server.user.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.scope.entity.Scope;
import org.kamar.authorization_server.user.data.dto.UserRegistrationDto;
import org.kamar.authorization_server.user.entity.User;
import org.kamar.authorization_server.user.event.UserRegistrationEvent;
import org.kamar.authorization_server.user.exception.UserException;
import org.kamar.authorization_server.user.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * implementation of the user management contract.
 * @author kamar baraka.*/

@Service
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService {

    private final ApplicationEventPublisher eventPublisher;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Scope scope;
    private final User user;

    @Override
    public User registerUser(@NotNull UserRegistrationDto userRegistrationDto) {

        /*encode the password*/
        String encodedPassword = passwordEncoder.encode(userRegistrationDto.password());

        User appUser = user;
        appUser.setUsername(userRegistrationDto.username());
        appUser.setFirstname(userRegistrationDto.firstname());
        appUser.setLastname(userRegistrationDto.lastname());
        appUser.setPassword(encodedPassword);

        /*get the scopes*/
        List<Scope> scopes = userRegistrationDto.scopes().stream().map(scopeString -> {
            scope.setAuthority(scopeString);
            return scope;
        }).toList();

        appUser.getAuthorities().addAll(scopes);


        /*create and publish the event*/
        UserRegistrationEvent event = new UserRegistrationEvent(this, userRegistrationDto);

        eventPublisher.publishEvent(event);

        return userRepository.save(appUser);
    }

    @Override
    public User getUserByUsername(final String  username) {

        /*get the user with the id*/
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserException("no user with that username!"));
    }
}
