package ee.ttu.endpoints;

import ee.ttu.converter.EmployeeUserConverter;
import ee.ttu.model.EmployeeUser;
import ee.ttu.repository.EmployeeUserRepository;
import ee.ttu.xml.GetUserRequest;
import ee.ttu.xml.GetUserResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by Vahur Kaar on 5.10.2014.
 */
@Endpoint
public class UserServiceImpl implements UserService {

    @Autowired
    private EmployeeUserRepository employeeUserRepository;

    @Autowired
    private EmployeeUserConverter employeeUserConverter;

    @PayloadRoot(namespace = NAMESPACE, localPart = GET_USER_REQUEST)
    @ResponsePayload
    @Override
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();

        if (StringUtils.isNotEmpty(request.getUsername())) {
            EmployeeUser employeeUser = employeeUserRepository.findByUsername(request.getUsername());

            if (employeeUser != null) {
                response.setUser(employeeUserConverter.convert(employeeUser));
            }
        }

        return response;
    }
}
