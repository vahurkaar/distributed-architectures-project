package ee.ttu;

import java.util.concurrent.Future;

/**
 * Created by Vahur Kaar on 10.01.2015.
 */
public interface AuthenticationLoggingService {

    Future<String> logAuthentication(String username, String password, String result);

}
