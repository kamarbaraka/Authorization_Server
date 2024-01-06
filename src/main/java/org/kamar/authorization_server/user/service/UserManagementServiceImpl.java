package org.kamar.authorization_server.user.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.user.data.dto.UserRegistrationDto;
import org.kamar.authorization_server.user.entity.User;
import org.kamar.authorization_server.user.event.UserRegistrationEvent;
import org.kamar.authorization_server.user.exception.UserException;
import org.kamar.authorization_server.user.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * implementation of the user management contract.
 * @author kamar baraka.*/

@Service
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService {

    private final ApplicationEventPublisher eventPublisher;
    private final UserRepository userRepository;

    @Override
    public void registerUser(@NotNull UserRegistrationDto userRegistrationDto) {

        /*create and publish the event*/
        UserRegistrationEvent event = new UserRegistrationEvent(this, userRegistrationDto);

        eventPublisher.publishEvent(event);

    }

    @Override
    public User getUserByUsername(final String  username) {

        /*get the user with the id*/
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserException("no user with that username!"));
    }
}
