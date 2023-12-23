package org.kamar.authorization_server.scope.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;


@Component
@Data
@EqualsAndHashCode(callSuper = true)
public class ScopeModel extends RepresentationModel<ScopeModel> {

    private String scope;
}
