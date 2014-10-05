package ee.ttu.endpoints;

import ee.ttu.xml.GetUserRequest;
import ee.ttu.xml.GetUserResponse;

/**
 * Created by Vahur Kaar on 5.10.2014.
 */
public interface UserService {

    public static final String NAMESPACE = "http://www.ttu.ee/hajusarhitektuurid";

    public static final String GET_USER_REQUEST = "GetUserRequest";

    GetUserResponse getUser(GetUserRequest request);

}
