package org.kamar.authorization_server.user.data.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

/**
 * the user presentation model.
 * @author kamar baraka.*/

@Component
@Data
@EqualsAndHashCode(callSuper = true)
public class UserModel extends RepresentationModel<UserModel> {

        @NotNull
        private long userId;
        @NotNull
        private String username;
        private String firstname;
        private String lastname;
}
