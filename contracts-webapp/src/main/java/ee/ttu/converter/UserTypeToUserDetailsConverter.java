package ee.ttu.converter;

import ee.ttu.model.UserDetailsImpl;
import ee.ttu.xml.UserType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by Vahur Kaar on 9.11.2014.
 */
@Component
public class UserTypeToUserDetailsConverter implements Converter<UserType, UserDetails> {

    @Override
    public UserDetails convert(UserType source) {
        if (source == null) {
            return null;
        }

        UserDetailsImpl user = new UserDetailsImpl();
        user.setId(source.getId());
        user.setUsername(source.getUsername());
        user.setPassword(source.getPassword());

        return user;
    }
}
