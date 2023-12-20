package org.kamar.authorization_server.user.event;

import lombok.Getter;
import org.kamar.authorization_server.user.data.dto.UserRegistrationDto;
import org.springframework.context.ApplicationEvent;

/**
 * representation of the user registration event.
 * @author kamar baraka.*/

@Getter
public class UserRegistrationEvent extends ApplicationEvent {

    private final UserRegistrationDto registrationDto;

    public UserRegistrationEvent(Object source, UserRegistrationDto registrationDto) {
        super(source);
        this.registrationDto = registrationDto;
    }
}
