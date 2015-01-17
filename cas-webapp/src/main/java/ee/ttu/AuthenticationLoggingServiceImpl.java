package ee.ttu;

import ee.ttu.xml.LogAuthRequest;
import ee.ttu.xml.LogAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.concurrent.Future;

/**
 * Created by Vahur Kaar on 10.01.2015.
 */
@Service
public class AuthenticationLoggingServiceImpl extends WebServiceGatewaySupport implements AuthenticationLoggingService {

    @Async
    public Future<String> logAuthentication(String username, String password, String result) {
        LogAuthRequest request = new LogAuthRequest();
        request.setUsername(username);
        request.setPassword(password);
        request.setResult(result);

        // Show progress...
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Execution should never enter here...
        }

        LogAuthResponse response = (LogAuthResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return new AsyncResult<>(response.getResult());
    }

}
