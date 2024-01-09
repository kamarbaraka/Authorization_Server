package org.kamar.authorization_server.user.service;

import org.kamar.authorization_server.user.data.dto.UserRegistrationDto;
import org.kamar.authorization_server.user.entity.User;

/**
 * contract for managing users.
 * @author kamar baraka.*/



public interface UserManagementService {

    User registerUser(UserRegistrationDto userRegistrationDto) ;

    User getUserByUsername(String  userId);
}
