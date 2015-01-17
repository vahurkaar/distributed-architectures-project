package ee.ttu.endpoints;

import ee.ttu.configuration.ServiceConfiguration;
import ee.ttu.repository.EndpointTestSupport;
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

import static org.junit.Assert.*;
import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = {ServiceConfiguration.class})
public class UserServiceImplTest extends EndpointTestSupport {

    @Before
    public void setUp() throws Exception {
        initialize("sql/users/services_test.sql", "test-cases/users/");
    }

    @Test
    public void testGetUserReturnsUser() throws Exception {
        executeServiceTest("GetUserReturnsUser");
    }

    @Test
    public void testGetUserReturnsNothingWhenUsernameIsNull() throws Exception {
        executeServiceTest("GetUserReturnsNothingWhenUsernameIsNull");
    }

    @Test
    public void testGetUserReturnsNothingWhenUserNotFoundFromDatabase() throws Exception {
        executeServiceTest("GetUserReturnsNothingWhenUserNotFoundFromDatabase");
    }

}