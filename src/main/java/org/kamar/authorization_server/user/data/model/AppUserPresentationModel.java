package org.kamar.authorization_server.user.data.model;

import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

/**
 * the user presentation model.
 * @author kamar baraka.*/

@Component
@NoArgsConstructor
@Data
public class AppUserPresentationModel extends RepresentationModel<AppUserPresentationModel> {


        @Nonnull
        private long userId;
        @Nonnull
        private String username;
        private String firstname;
        private String lastname;
}
