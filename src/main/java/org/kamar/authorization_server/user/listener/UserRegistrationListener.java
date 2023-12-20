package org.kamar.authorization_server.user.listener;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.user.data.dto.UserRegistrationDto;
import org.kamar.authorization_server.user.entity.AppUser;
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

        AppUser appUser = new AppUser();
        appUser.setUsername(registrationDto.username());
        appUser.setFirstname(registrationDto.firstname());
        appUser.setLastname(registrationDto.lastname());
        appUser.setPassword(encodedPassword);

        userRepository.save(appUser);
    }
}
