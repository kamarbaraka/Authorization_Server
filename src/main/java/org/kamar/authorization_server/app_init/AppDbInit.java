package org.kamar.authorization_server.app_init;

import org.kamar.authorization_server.app_props.AppProps;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * the database initialization class.
 * @author kamar baraka.*/


@Configuration
public class AppDbInit {


    /**
     * Initializes the database using the provided application properties and SQL initialization properties.
     *
     * @param appProps    application properties
     * @param sqlProps    sQL initialization properties
     * @return an instance of SqlDataSourceScriptDatabaseInitializer
     */
    @Bean
    public SqlDataSourceScriptDatabaseInitializer dbInit(AppProps appProps,
                                                         SqlInitializationProperties sqlProps){

        /*build the data source*/
        DataSource dataSource = DataSourceBuilder.create()
                .type(SimpleDriverDataSource.class)
                .url(appProps.dbUrl())
                .username(appProps.dbUsername())
                .password(appProps.dbPassword())
                .build();

        return new SqlDataSourceScriptDatabaseInitializer(dataSource, sqlProps);
    }
}
