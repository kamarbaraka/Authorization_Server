package org.kamar.authorization_server.scope.data.hateoas;


import org.kamar.authorization_server.scope.controller.ScopeManagementController;
import org.kamar.authorization_server.scope.data.model.ScopeModel;
import org.kamar.authorization_server.scope.entity.Scope;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * assembler for the scope model.
 * @author kamar baraka.*/

@Service
public class ScopeModelAssembler extends RepresentationModelAssemblerSupport<Scope, ScopeModel> {

    private final ScopeModel model;

    public ScopeModelAssembler(ScopeModel model) {
        super(ScopeManagementController.class, ScopeModel.class);
        this.model = model;
    }

    @Override
    public ScopeModel toModel(Scope entity) {

        /*model the entity*/
        BeanUtils.copyProperties(entity, model);
        model.setScope(entity.getAuthority());

        /*create the links*/
        Link selfLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(ScopeManagementController.class).getScopeByAuthority(entity.getAuthority())
        ).withSelfRel();

        /*add the links to model*/
        model.add(selfLink);

        return model;
    }
}
