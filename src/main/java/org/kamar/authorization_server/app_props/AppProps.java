package org.kamar.authorization_server.app_props;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * the application properties.
 * @author kamar baraka.*/


@ConfigurationProperties("app.init")
public record AppProps(

        String dbUrl,
        String dbUsername,
        String dbPassword
) {
}
