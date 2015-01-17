package ee.ttu.endpoints;

import ee.ttu.configuration.ServiceConfiguration;
import ee.ttu.repository.EndpointTestSupport;
import ee.ttu.repository.RepositoryTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ws.test.server.MockWebServiceClient;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = {ServiceConfiguration.class})
public class ClassifierServiceImplEndpointTest extends EndpointTestSupport {

    @Before
    public void setUp() throws Exception {
        initialize("sql/classifiers/services_test.sql", "test-cases/classifiers/");
    }

    @Test
    public void testGetCustomerTypes() throws Exception {
        executeServiceTest("GetCustomerTypes");
    }

    @Test
    public void testGetCustomerStateTypes() throws Exception {
        executeServiceTest("GetCustomerStateTypes");
    }

    @Test
    public void testGetCountries() throws Exception {
        executeServiceTest("GetCountries");
    }

    @Test
    public void testGetContractTypes() throws Exception {
        executeServiceTest("GetContractTypes");
    }

    @Test
    public void testGetContractStatusTypes() throws Exception {
        executeServiceTest("GetContractStatusTypes");
    }
}