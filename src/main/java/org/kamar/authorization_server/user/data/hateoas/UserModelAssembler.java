package org.kamar.authorization_server.user.data.hateoas;


import jakarta.validation.constraints.NotNull;
import org.kamar.authorization_server.scope.controller.ScopeController;
import org.kamar.authorization_server.user.controller.UserManagementController;
import org.kamar.authorization_server.user.data.model.UserModel;
import org.kamar.authorization_server.user.entity.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

/**
 * the assembler for the app user representation model.
 * @author kamar baraka.*/

@Service
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {


    private final UserModel model;

    public UserModelAssembler(UserModel model) {
        super(UserManagementController.class, UserModel.class);
        this.model = model;
    }

    @Override
    public UserModel toModel(@NotNull User entity) {

        /*map the entity to the model*/
        model.setUserId(entity.getUserId());
        model.setUsername(entity.getUsername());
        model.setFirstname(entity.getFirstname());
        model.setLastname(entity.getLastname());

        /*add the links*/
        Link authoritiesLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ScopeController.class)
                .getUserScopesByUsername(entity.getUsername())).withRel("authorities");

        model.add(authoritiesLink);

        return model;

    }


}
