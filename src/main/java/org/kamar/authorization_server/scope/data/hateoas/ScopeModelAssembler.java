package org.kamar.authorization_server.scope.data.hateoas;


import lombok.RequiredArgsConstructor;
import org.kamar.authorization_server.scope.controller.ScopeController;
import org.kamar.authorization_server.scope.data.model.ScopeModel;
import org.kamar.authorization_server.scope.entity.Scope;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Service;

/**
 * assembler for the scope model.
 * @author kamar baraka.*/

@Service
public class ScopeModelAssembler extends RepresentationModelAssemblerSupport<Scope, ScopeModel> {

    private final ScopeModel model;

    public ScopeModelAssembler(ScopeModel model) {
        super(ScopeController.class, ScopeModel.class);
        this.model = model;
    }

    @Override
    public ScopeModel toModel(Scope entity) {

        /*model the entity*/
        BeanUtils.copyProperties(entity, model);

        return model;
    }
}
