package ee.ttu.endpoints;

import ee.ttu.xml.LogAuthRequest;
import ee.ttu.xml.LogAuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by Vahur Kaar on 10.01.2015.
 */
@Endpoint
public class LoggingServiceImpl implements LoggingService {

    private final Logger logger = LoggerFactory.getLogger(LoggingService.class);

    private static final String LOG_AUTH_FORMAT = "User - {%s}, password - {%s}, result - {%s}";

    @PayloadRoot(namespace = NAMESPACE, localPart = LOG_AUTH_REQUEST)
    @ResponsePayload
    @Override
    public LogAuthResponse logAuth(@RequestPayload LogAuthRequest request) {
        LogAuthResponse response = new LogAuthResponse();
        logger.info(String.format(LOG_AUTH_FORMAT, request.getUsername(), request.getPassword(), request.getResult()));
        response.setResult("OK");
        return response;
    }
}
