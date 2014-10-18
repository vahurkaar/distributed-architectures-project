package ee.ttu.endpoints;

import ee.ttu.configuration.ServiceConfiguration;
import ee.ttu.repository.RepositoryTestSupport;
import org.junit.Before;
import org.junit.Ignore;
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
public class ContractServiceImplTest extends RepositoryTestSupport {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient client;

    @Before
    public void setUp() throws Exception {
        client = MockWebServiceClient.createClient(applicationContext);
        executeSqlScript("sql/contracts/services_test.sql", false);
    }

    @Test
    public void testGetContract() throws Exception {
        executeServiceTest("GetContract");
    }

    @Test
    public void testGetContractDoesNotExist() throws Exception {
        executeServiceTest("GetContractDoesNotExist");
    }

    @Test
    public void testSaveContract() throws Exception {
        executeServiceTest("SaveContract");
    }

    @Test
    public void testSaveContractNameExists() throws Exception {
        executeServiceTest("SaveContractNameExists");
    }

    @Test
    public void testSaveContractNumberExists() throws Exception {
        executeServiceTest("SaveContractNumberExists");
    }

    @Test
    public void testSaveContractThrowsException() throws Exception {
        executeServiceTest("SaveContractThrowsException");
    }

    @Test
    public void testDeleteContract() throws Exception {
        executeServiceTest("DeleteContract");
    }

    @Test
    public void testDeleteContractThrowsException() throws Exception {
        executeServiceTest("DeleteContractDoesNotExist");
    }

    @Test
    public void testGetCustomerWithContracts() throws Exception {
        executeServiceTest("GetCustomerWithContracts");
    }

    private void executeServiceTest(String testCase) throws IOException {
        Source request = new ResourceSource(new ClassPathResource("test-cases/contracts/" + testCase + "Request.xml"));
        Source response = new ResourceSource(new ClassPathResource("test-cases/contracts/" + testCase + "Response.xml"));

        client.sendRequest(withPayload(request))
                .andExpect(payload(response));
    }
}