package ee.ttu.converter;

import ee.ttu.model.classifier.CustomerStateType;
import ee.ttu.model.classifier.CustomerType;
import ee.ttu.xml.CustomerStateTypeType;
import ee.ttu.xml.CustomerTypeType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Vahur Kaar on 30.09.2014.
 */
@Component
public class CustomerStateTypeClassifierConverter implements Converter<CustomerStateType, CustomerStateTypeType> {

    @Override
    public CustomerStateTypeType convert(CustomerStateType source) {
        CustomerStateTypeType customerTypeType = new CustomerStateTypeType();
        customerTypeType.setId(source.getId());
        customerTypeType.setName(source.getName());

        return customerTypeType;
    }
}
