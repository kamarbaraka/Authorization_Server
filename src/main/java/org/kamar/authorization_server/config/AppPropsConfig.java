package org.kamar.authorization_server.config;

import org.kamar.authorization_server.app_props.AppProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * configuration for application properties.
 * @author kamar baraka.*/


@Configuration
@EnableConfigurationProperties(value = {AppProps.class})
@EnableTransactionManagement
public class AppPropsConfig {
}
