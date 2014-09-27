package ee.ttu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Vahur Kaar on 20.09.2014.
 */
@Configuration
public class EmbeddedDatasourceConfiguration {

    @Bean
    public Properties jpaProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new ClassPathResource("test-hibernate.properties").getInputStream());
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .build();
    }

}
