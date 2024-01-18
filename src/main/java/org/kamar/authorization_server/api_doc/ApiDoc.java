package org.kamar.authorization_server.api_doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(
        tags = {
                @Tag(name = "User Management.", description = "— Apis for managing ``users`` of the server."),
                @Tag(name = "Client Management.", description = "Apis for managing ``client applications``."),
                @Tag(name = "Scope Management.", description = "Apis for managing ``user and application scopes``.")
        },
        info = @Info(
                title = "Authorization Server",
                summary = "Documentation for the Authorization server Apis",
                description = "This is the extensive documentation of the available Apis for the `authorization server`.",
                termsOfService = "https://opensource.org/licenses/MIT",
                version = "v1.0.1",
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                ),
                contact = @Contact(
                        name = "kamar baraka",
                        email = "kamar254baraka@gmail.com"
                )
        ),
        servers = {@Server(url = "http://localhost:9080/", description = "authorization server.")}
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
                                @OAuthScope(name = "register user", description = "able to register a user."),
                                @OAuthScope(name = "get user data",description = "able to get the user's info."),
                                @OAuthScope(name = "profile", description = "get the requested user profile.")
                        }
                )
        )
)

@SecurityScheme(
        type = SecuritySchemeType.OPENIDCONNECT,
        name = "openId",
        description = "Security scheme for openId connect.",
        openIdConnectUrl = "YourOpenIdConnectUrlHere"
)

public class ApiDoc {
}