package ee.ttu.converter;

import ee.ttu.model.Customer;
import ee.ttu.model.CustomerAddress;
import ee.ttu.util.XMLCalendarUtil;
import ee.ttu.xml.CustomerType;
import ee.ttu.xml.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Vahur Kaar on 24.09.2014.
 */
@Component
public class CustomerConverter implements Converter<Customer, CustomerType> {

    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    @Autowired
    private CustomerAddressConverter customerAddressConverter;

    @Override
    public CustomerType convert(Customer source) {
        CustomerType customerType = OBJECT_FACTORY.createCustomerType();
        customerType.setId(source.getId());
        customerType.setFirstname(source.getFirstname());
        customerType.setLastname(source.getLastname());
        customerType.setIdentityCode(source.getIdentityCode());
        customerType.setNote(source.getNote());
        customerType.setBirthDate(XMLCalendarUtil.dateToXmlCalendar(source.getBirthDate()));
        customerType.setCustomerType(source.getCustomerType() != null ? source.getCustomerType().getId() : null);
        customerType.setCustomerStatusType(source.getCustomerStateType() != null ? source.getCustomerStateType().getId() : null);
        customerType.setAddresses(getAddresses(source));
        customerType.setModifier(getModifier(source));

        return customerType;
    }

    private CustomerType.Addresses getAddresses(Customer source) {
        List<CustomerAddress> customerAddresses = source.getAddresses();
        CustomerType.Addresses customerTypeAddresses = OBJECT_FACTORY.createCustomerTypeAddresses();

        for (CustomerAddress customerAddress : customerAddresses) {
            customerTypeAddresses.getAddress().add(customerAddressConverter.convert(customerAddress));
        }

        return customerTypeAddresses;
    }

    private long getModifier(Customer source) {
        Long createdBy = source.getCreatedBy();
        Long updatedBy = source.getUpdatedBy();

        if (updatedBy != null) {
            return updatedBy;
        }

        return createdBy;
    }



}
