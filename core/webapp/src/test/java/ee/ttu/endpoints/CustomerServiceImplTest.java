package ee.ttu.endpoints;

import ee.ttu.configuration.ServiceConfiguration;
import ee.ttu.repository.RepositoryTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.ResourceSource;

import javax.xml.transform.Source;
import java.io.IOException;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = {ServiceConfiguration.class})
public class CustomerServiceImplTest extends RepositoryTestSupport {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient client;

    @Before
    public void setUp() throws Exception {
        client = MockWebServiceClient.createClient(applicationContext);
        executeSqlScript("services_test.sql", false);
    }

    @Test
    public void testGetCustomerById() throws Exception {
        executeServiceTest("GetCustomerById");
    }

    @Test
    public void testGetCustomerByIdentityCode() throws Exception {
        executeServiceTest("GetCustomerByIdentityCode");
    }

    @Test
    public void testGetCustomerByName() throws Exception {
        executeServiceTest("GetCustomerByName");
    }

    @Test
    public void testSaveCustomerSuccess() throws Exception {
        executeServiceTest("SaveCustomerSuccess");
    }

    @Test
    public void testSaveCustomerExists() throws Exception {
        executeServiceTest("SaveCustomerExists");
    }

    @Test
    public void testSaveCustomerWithIdExistsRequest() throws Exception {
        executeServiceTest("SaveCustomerWithIdExists");
    }

    @Test
    public void testSaveCustomerThrowsException() throws Exception {
        executeServiceTest("SaveCustomerThrowsException");
    }

    @Test
    public void testDeleteCustomerSuccess() throws Exception {
        executeServiceTest("DeleteCustomerSuccess");
    }

    @Test
    public void testDeleteCustomerThrowsException() throws Exception {
        executeServiceTest("DeleteCustomerDoesNotExist");
    }


    private void executeServiceTest(String testCase) throws IOException {
        Source request = new ResourceSource(new ClassPathResource("test-cases/" + testCase + "Request.xml"));
        Source response = new ResourceSource(new ClassPathResource("test-cases/" + testCase + "Response.xml"));

        client.sendRequest(withPayload(request))
                .andExpect(payload(response));
    }
}