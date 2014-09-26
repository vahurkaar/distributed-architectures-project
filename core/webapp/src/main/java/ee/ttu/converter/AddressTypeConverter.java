package ee.ttu.converter;

import ee.ttu.model.CustomerAddress;
import ee.ttu.model.classifier.Country;
import ee.ttu.repository.classifier.CountryRepository;
import ee.ttu.xml.AddressType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Vahur Kaar on 24.09.2014.
 */
@Component
public class AddressTypeConverter implements Converter<AddressType, CustomerAddress> {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public CustomerAddress convert(AddressType source) {
        if (source == null) {
            return null;
        }

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setId(source.getId());
        customerAddress.setZip(source.getZip());
        customerAddress.setHouse(source.getHouse());
        customerAddress.setAddress(source.getAddress());
        customerAddress.setCounty(source.getCounty());
        customerAddress.setParish(source.getParish());
        customerAddress.setTownCounty(source.getTownCity());
        customerAddress.setPhone(source.getPhone());
        customerAddress.setSms(source.getSms());
        customerAddress.setMobile(source.getMobile());
        customerAddress.setEmail(source.getEmail());
        customerAddress.setNote(source.getNote());

        if (source.getId() != null) {
            Country country = countryRepository.findOne(source.getId());
            customerAddress.setCountry(country);
        }

        return customerAddress;

    }
}
