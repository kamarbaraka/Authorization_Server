package org.kamar.authorization_server.api_doc;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

/**
 * configuration class for api documentation.
 * @author kamar baraka.*/


@RestController
@OpenAPIDefinition(
        tags = {
                @Tag(name = "User Management.", description = "Apis for managing users of the server.")
        },
        info = @Info(
                title = "Authorization Server",
                summary = "Documentation for the Authorization server Apis",
                description = "This is the extensive documentation of the available Apis for the authorization server.",
                termsOfService = "https://opensource.org/licenses/MIT",
                version = "v1",
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                ),
                contact = @Contact(
                        name = "kamar baraka",
                        email = "kamar254baraka@gmail.com"
                )
        )
)

@SecurityScheme(
        type = SecuritySchemeType.OAUTH2,
        name = "oauth2",
        description = "security scheme for oauth2 authentication.",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER,
        flows = @OAuthFlows(
                authorizationCode = @OAuthFlow(
                        authorizationUrl = "",
                        tokenUrl = "",
                        refreshUrl = "",
                        scopes = {
                                @OAuthScope(name = "", description = "")
                        }
                )
        )

)
@SecurityScheme(
        type = SecuritySchemeType.OPENIDCONNECT,
        name = "openId",
        description = "Security scheme for openId connect.",
        openIdConnectUrl = ""
)

public class ApiDoc {
}
