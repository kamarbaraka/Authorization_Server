package org.kamar.authorization_server.user.listener;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.user.data.UserRegistrationDto;
import org.kamar.authorization_server.user.entity.User;
import org.kamar.authorization_server.user.event.UserRegistrationEvent;
import org.kamar.authorization_server.user.repository.UserRepository;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * the listener for the user registration event.
 * @author kamar baraka.*/

@Service
@RequiredArgsConstructor
public class UserRegistrationListener {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(classes = {UserRegistrationEvent.class})
    public void onUserRegistration(@NotNull UserRegistrationEvent event) {

        UserRegistrationDto registrationDto = event.getRegistrationDto();

        /*encode the password*/
        String encodedPassword = passwordEncoder.encode(registrationDto.password());

        User user = new User();
        user.setUsername(registrationDto.username());
        user.setFirstname(registrationDto.firstname());
        user.setLastname(registrationDto.lastname());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }
}
