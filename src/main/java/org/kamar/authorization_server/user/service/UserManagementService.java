package org.kamar.authorization_server.user.service;

import org.kamar.authorization_server.user.data.UserRegistrationDto;
import org.kamar.authorization_server.user.exception.UserException;

/**
 * contract for managing users.
 * @author kamar baraka.*/



public interface UserManagementService {

    void registerUser(UserRegistrationDto userRegistrationDto) throws UserException;
}
