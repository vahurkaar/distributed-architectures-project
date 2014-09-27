package ee.ttu.converter;

import ee.ttu.model.Customer;
import ee.ttu.model.CustomerAddress;
import ee.ttu.model.classifier.CustomerStateType;
import ee.ttu.service.ClassifierService;
import ee.ttu.util.XMLCalendarUtil;
import ee.ttu.xml.AddressType;
import ee.ttu.xml.CustomerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vahur Kaar on 24.09.2014.
 */
@Component
public class CustomerTypeConverter implements Converter<CustomerType, Customer> {

    @Autowired
    private AddressTypeConverter addressTypeConverter;

    @Autowired
    private ClassifierService classifierService;

    @Override
    public Customer convert(CustomerType source) {
        if (source == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setId(source.getId());
        customer.setFirstname(source.getFirstname());
        customer.setLastname(source.getLastname());
        customer.setIdentityCode(source.getIdentityCode());
        customer.setNote(source.getNote());
        customer.setBirthDate(XMLCalendarUtil.xmlCalendarToDate(source.getBirthDate()));

        if (source.getCustomerType() != null) {
            ee.ttu.model.classifier.CustomerType customerType = classifierService.getCustomerType(source.getCustomerType());
            customer.setCustomerType(customerType);
        }

        if (source.getCustomerStatusType() != null) {
            CustomerStateType customerStateType = classifierService.getCustomerStateType(source.getCustomerStatusType());
            customer.setCustomerStateType(customerStateType);
        }

        customer.setAddresses(getAddresses(source));
        customer.setUpdatedBy(source.getModifier());

        return customer;
    }

    private List<CustomerAddress> getAddresses(CustomerType source) {
        CustomerType.Addresses customerAddresses = source.getAddresses();
        List<CustomerAddress> convertedAddresses = new ArrayList<>();

        for (AddressType addressType : customerAddresses.getAddress()) {
            convertedAddresses.add(addressTypeConverter.convert(addressType));
        }

        return convertedAddresses;
    }

}
