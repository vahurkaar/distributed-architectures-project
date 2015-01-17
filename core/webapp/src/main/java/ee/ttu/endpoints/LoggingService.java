package ee.ttu.endpoints;

import ee.ttu.xml.LogAuthRequest;
import ee.ttu.xml.LogAuthResponse;

/**
 * Created by Vahur Kaar on 10.01.2015.
 */
public interface LoggingService {

    public static final String NAMESPACE = "http://www.ttu.ee/hajusarhitektuurid";

    public static final String LOG_AUTH_REQUEST = "LogAuthRequest";

    LogAuthResponse logAuth(LogAuthRequest request);
}
