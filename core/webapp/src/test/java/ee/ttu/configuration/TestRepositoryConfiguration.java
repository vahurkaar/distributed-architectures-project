package ee.ttu.configuration;

import ee.ttu.repository.CustomerRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Vahur Kaar on 24.09.2014.
 */
@Configuration
public class TestRepositoryConfiguration {

    @Bean
    public CustomerRepository customerRepository() {
        return Mockito.mock(CustomerRepository.class);
    }

}
