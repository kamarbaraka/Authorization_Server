package org.kamar.authorization_server.authentication.data.hateoas;

import org.kamar.authorization_server.authentication.controller.ClientAppManagementController;
import org.kamar.authorization_server.authentication.data.model.ClientAppModel;
import org.kamar.authorization_server.authentication.entity.ClientApp;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;


/**
 * This class is responsible for converting a ClientApp entity to a ClientAppModel representation.
 * It extends the RepresentationModelAssemblerSupport class for supporting HATEOAS.
 *
 * @author kamar baraka.
 */
@Service
public class ClientAppModelAssembler extends RepresentationModelAssemblerSupport<ClientApp, ClientAppModel> {

    private final ClientAppModel model;

    public ClientAppModelAssembler(ClientAppModel model) {
        super(ClientAppManagementController.class, ClientAppModel.class);
        this.model = model;
    }

    /**
     * Converts a ClientApp entity to a ClientAppModel representation.
     *
     * This method copies properties from the entity to the model and adds links.
     * It returns the resulting model.
     *
     * @param entity The ClientApp entity to be converted.
     *
     * @return The ClientAppModel representation of the entity.
     */
    @Override
    public ClientAppModel toModel(ClientApp entity) {

        /*copy properties from the entity to the model*/
        BeanUtils.copyProperties(entity, model);

        /*add links*/
        Link selfLink = WebMvcLinkBuilder.linkTo(ClientAppManagementController.class).withSelfRel();
        model.add(selfLink);

        /*return the model*/
        return model;
    }
}
