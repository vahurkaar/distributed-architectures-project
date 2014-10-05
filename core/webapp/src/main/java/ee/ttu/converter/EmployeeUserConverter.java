package ee.ttu.converter;

import ee.ttu.model.EmployeeUser;
import ee.ttu.xml.UserType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Vahur Kaar on 5.10.2014.
 */
@Component
public class EmployeeUserConverter implements Converter<EmployeeUser, UserType> {

    @Override
    public UserType convert(EmployeeUser source) {
        UserType userType = new UserType();
        userType.setId(source.getId());
        userType.setUsername(source.getUsername());
        userType.setPassword(source.getPassword());

        return userType;
    }
}
