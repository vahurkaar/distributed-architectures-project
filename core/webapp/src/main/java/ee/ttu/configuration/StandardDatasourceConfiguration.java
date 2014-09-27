package ee.ttu.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Vahur Kaar on 20.09.2014.
 */
@Configuration
@PropertySource("classpath:core.properties")
public class StandardDatasourceConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public Properties jpaProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new ClassPathResource("hibernate.properties").getInputStream());
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.user"));
        dataSource.setPassword(environment.getProperty("jdbc.pass"));
        return dataSource;
    }

}
