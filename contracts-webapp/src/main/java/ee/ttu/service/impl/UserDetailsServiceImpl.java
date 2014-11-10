package ee.ttu.service.impl;

import ee.ttu.converter.UserTypeToUserDetailsConverter;
import ee.ttu.xml.GetUserRequest;
import ee.ttu.xml.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Created by Vahur Kaar on 8.11.2014.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Autowired
    private UserTypeToUserDetailsConverter userTypeToUserDetailsConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserFromCore(username);
    }

    private UserDetails findUserFromCore(String username) {
        GetUserRequest userRequest = createUserRequest(username);
        GetUserResponse userRespone = (GetUserResponse) webServiceTemplate.marshalSendAndReceive(userRequest);
        return userTypeToUserDetailsConverter.convert(userRespone.getUser());
    }

    private GetUserRequest createUserRequest(String username) {
        GetUserRequest userRequest = new GetUserRequest();
        userRequest.setUsername(username);
        return userRequest;
    }
}
