package org.kamar.authorization_server.user.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.user.data.UserRegistrationDto;
import org.kamar.authorization_server.user.event.UserRegistrationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * implementation of the user management contract.
 * @author kamar baraka.*/

@Service
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void registerUser(@NotNull UserRegistrationDto userRegistrationDto) {

        /*create and publish the event*/
        UserRegistrationEvent event = new UserRegistrationEvent(this, userRegistrationDto);

        eventPublisher.publishEvent(event);

    }
}
