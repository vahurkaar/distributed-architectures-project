package ee.ttu.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.ResourceSource;

import javax.xml.transform.Source;
import java.io.IOException;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

/**
 * Created by Vahur Kaar on 10.11.2014.
 */
public abstract class EndpointTestSupport extends RepositoryTestSupport {

    private MockWebServiceClient client;

    private String testCasesLocation;

    public void initialize(String setupSql, String testCasesLocation) throws Exception {
        this.testCasesLocation = testCasesLocation;

        client = MockWebServiceClient.createClient(applicationContext);
        setSqlScriptEncoding("UTF-8");
        executeSqlScript(setupSql, false);
    }

    public void executeServiceTest(String testCase) throws IOException {
        Source request = new ResourceSource(new ClassPathResource(testCasesLocation + testCase + "Request.xml"));
        Source response = new ResourceSource(new ClassPathResource(testCasesLocation + testCase + "Response.xml"));

        client.sendRequest(withPayload(request))
                .andExpect(payload(response));
    }

}
