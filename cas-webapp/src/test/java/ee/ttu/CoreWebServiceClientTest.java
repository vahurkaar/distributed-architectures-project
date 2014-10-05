package ee.ttu;

import ee.ttu.xml.GetUserResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:webServiceConfiguration.xml"})
public class CoreWebServiceClientTest {

    @Autowired
    private CoreWebServiceClient coreWebServiceClient;

    @Test
    public void testFindUser() throws Exception {
        GetUserResponse response = coreWebServiceClient.getUserByUsername("casuser");

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getUser());
        Assert.assertEquals("casuser", response.getUser().getUsername());
        Assert.assertEquals("a2300ca109b99c659d886ca9d620e546817b0a04", response.getUser().getPassword());
    }
}