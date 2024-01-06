package org.kamar.authorization_server.user.listener;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.scope.entity.Scope;
import org.kamar.authorization_server.user.data.dto.UserRegistrationDto;
import org.kamar.authorization_server.user.entity.User;
import org.kamar.authorization_server.user.event.UserRegistrationEvent;
import org.kamar.authorization_server.user.repository.UserRepository;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * the listener for the user registration event.
 * @author kamar baraka.*/

@Service
@RequiredArgsConstructor
public class UserRegistrationListener {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Scope scope;

    @EventListener(classes = {UserRegistrationEvent.class})
    public void onUserRegistration(@NotNull UserRegistrationEvent event) {

        UserRegistrationDto registrationDto = event.getRegistrationDto();

        /*encode the password*/
        String encodedPassword = passwordEncoder.encode(registrationDto.password());

        User appUser = new User();
        appUser.setUsername(registrationDto.username());
        appUser.setFirstname(registrationDto.firstname());
        appUser.setLastname(registrationDto.lastname());
        appUser.setPassword(encodedPassword);

        /*get the scopes*/
        List<Scope> scopes = registrationDto.scopes().stream().map(scopeString -> {
            scope.setAuthority(scopeString);
            return scope;
        }).toList();

        appUser.getAuthorities().addAll(scopes);

        userRepository.save(appUser);
    }
}
