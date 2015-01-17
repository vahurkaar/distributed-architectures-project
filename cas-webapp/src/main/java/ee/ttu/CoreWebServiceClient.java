package ee.ttu;

import ee.ttu.xml.GetUserRequest;
import ee.ttu.xml.GetUserResponse;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

/**
 * Created by Vahur Kaar on 5.10.2014.
 */
@Component
public class CoreWebServiceClient extends WebServiceGatewaySupport {

    public GetUserResponse getUserByUsername(String username) {
        GetUserRequest request = new GetUserRequest();
        request.setUsername(username);

        GetUserResponse response = (GetUserResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response;
    }

}
