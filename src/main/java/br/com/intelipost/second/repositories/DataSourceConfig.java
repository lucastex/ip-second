package br.com.intelipost.second.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by lucastex on 01/07/17.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig extends HikariConfig {

    @Bean
    public DataSource dataSource() throws SQLException {
        return new HikariDataSource(this);
    }

}