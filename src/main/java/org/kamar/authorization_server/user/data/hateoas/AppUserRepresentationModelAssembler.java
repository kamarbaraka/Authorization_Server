package org.kamar.authorization_server.user.data.hateoas;


import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.user.controller.UserManagementController;
import org.kamar.authorization_server.user.data.model.AppUserPresentationModel;
import org.kamar.authorization_server.user.entity.AppUser;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Service;

/**
 * the assembler for the app user representation model.
 * @author kamar baraka.*/

@Service
public class AppUserRepresentationModelAssembler extends RepresentationModelAssemblerSupport<AppUser, AppUserPresentationModel> {


    private final AppUserPresentationModel model;

    public AppUserRepresentationModelAssembler(AppUserPresentationModel model) {
        super(UserManagementController.class, AppUserPresentationModel.class);
        this.model = model;
    }

    @Override
    public AppUserPresentationModel toModel( AppUser entity) {

        /*map the entity to the model*/
        model.setUserId(entity.getUserId());
        model.setUsername(entity.getUsername());
        model.setFirstname(entity.getFirstname());
        model.setLastname(entity.getLastname());

        /*add the links*/
        Link authoritiesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserManagementController.class)
                .getAuthoritiesByUserId(entity.getUserId())).withRel("authorities").withSelfRel();
        model.add(authoritiesLink);

        return model;

    }
}
