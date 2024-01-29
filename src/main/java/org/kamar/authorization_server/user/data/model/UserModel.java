package org.kamar.authorization_server.user.data.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * the user presentation model.
 * @author kamar baraka.*/

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
