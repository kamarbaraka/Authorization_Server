package org.kamar.authorization_server.config;

import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.support.WebStack;

/**
 * hateoas configuration.
 *
 * @author kamar baraka.*/


@EnableHypermediaSupport(
        type = {
                EnableHypermediaSupport.HypermediaType.COLLECTION_JSON,
                EnableHypermediaSupport.HypermediaType.HAL,
                EnableHypermediaSupport.HypermediaType.HAL_FORMS,
                EnableHypermediaSupport.HypermediaType.HTTP_PROBLEM_DETAILS,
                EnableHypermediaSupport.HypermediaType.UBER
        },
        stacks = {
                WebStack.WEBMVC,
                WebStack.WEBFLUX
        }
)
public class HateoasConfig {
}
