package ee.ttu.converter;

import ee.ttu.model.CustomerAddress;
import ee.ttu.xml.AddressType;
import ee.ttu.xml.ObjectFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Vahur Kaar on 24.09.2014.
 */
@Component
public class CustomerAddressConverter implements Converter<CustomerAddress, AddressType> {

    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    @Override
    public AddressType convert(CustomerAddress source) {
        if (source == null) {
            return null;
        }

        AddressType addressType = OBJECT_FACTORY.createAddressType();
        addressType.setId(source.getId());
        addressType.setZip(source.getZip());
        addressType.setHouse(source.getHouse());
        addressType.setAddress(source.getAddress());
        addressType.setCounty(source.getCounty());
        addressType.setParish(source.getParish());
        addressType.setTownCity(source.getTownCounty());
        addressType.setPhone(source.getPhone());
        addressType.setSms(source.getSms());
        addressType.setMobile(source.getMobile());
        addressType.setEmail(source.getEmail());
        addressType.setNote(source.getNote());
        addressType.setCountry(source.getCountry() != null ? source.getCountry().getId() : null);

        return addressType;

    }
}
