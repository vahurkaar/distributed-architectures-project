package ee.ttu.converter;

import ee.ttu.model.classifier.CustomerType;
import ee.ttu.xml.CustomerTypeType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Vahur Kaar on 30.09.2014.
 */
@Component
public class CustomerTypeClassifierConverter implements Converter<CustomerType, CustomerTypeType> {

    @Override
    public CustomerTypeType convert(CustomerType source) {
        CustomerTypeType customerTypeType = new CustomerTypeType();
        customerTypeType.setId(source.getId());
        customerTypeType.setName(source.getName());

        return customerTypeType;
    }
}
