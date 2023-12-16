package org.kamar.authorization_server.authentication.service;

import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.user.entity.AppUser;
import org.kamar.authorization_server.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

/**
 * implementation of contract to manage user details.
 * @author kamar baraka.*/

@Service("appUserDetailsService")
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsManager {

    /*inject dependencies*/
    private final UserRepository userRepository;

    @Override
    public void createUser(UserDetails user) {

        /*persist the user*/
        AppUser appUser = (AppUser) user;
        userRepository.save(appUser);

    }

    @Override
    public void updateUser(UserDetails user) {

        /*update user*/
        AppUser appUser = (AppUser) user;
        userRepository.save(appUser);

    }

    @Override
    public void deleteUser(String username) {

        /*check if user exists and delete*/
        AppUser appUser = userRepository.findUserByUsername(username).orElseThrow();
        userRepository.delete(appUser);

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        /*check if user exists*/
        return userRepository.findUserByUsername(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*get the user*/
        return userRepository.findUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("no such user")
        );
    }
}
