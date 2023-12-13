package org.kamar.authorization_server.user.service;

import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.user.data.UserRegistrationDto;
import org.kamar.authorization_server.user.exception.UserException;
import org.kamar.authorization_server.user.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * implementation of the user management contract.
 * @author kamar baraka.*/

@Service
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService {

    private final UserRepository userRepository;

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) throws UserException {

    }
}
