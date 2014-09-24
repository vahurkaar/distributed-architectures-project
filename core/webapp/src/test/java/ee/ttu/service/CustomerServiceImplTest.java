package ee.ttu.service;

import ee.ttu.configuration.ServiceConfiguration;
import ee.ttu.repository.RepositoryTestSupport;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.ResourceSource;

import javax.xml.transform.Source;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

@RunWith(JUnitParamsRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = {ServiceConfiguration.class})
public class CustomerServiceImplTest extends RepositoryTestSupport {

    private TestContextManager testContextManager;

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient client;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
        client = MockWebServiceClient.createClient(applicationContext);
        executeSqlScript("services_test.sql", false);
    }

    @Test
    @FileParameters("classpath:test-cases.csv")
    public void testServices(String testCase) throws Exception {
        Source request = new ResourceSource(new ClassPathResource("test-cases/" + testCase + "Request.xml"));
        Source response = new ResourceSource(new ClassPathResource("test-cases/" + testCase + "Response.xml"));

        client.sendRequest(withPayload(request))
            .andExpect(payload(response));
    }
}