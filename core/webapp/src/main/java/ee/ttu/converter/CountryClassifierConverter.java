package ee.ttu.converter;

import ee.ttu.model.classifier.Country;
import ee.ttu.model.classifier.CustomerStateType;
import ee.ttu.xml.CountryType;
import ee.ttu.xml.CustomerStateTypeType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Vahur Kaar on 30.09.2014.
 */
@Component
public class CountryClassifierConverter implements Converter<Country, CountryType> {

    @Override
    public CountryType convert(Country source) {
        CountryType customerTypeType = new CountryType();
        customerTypeType.setId(source.getId());
        customerTypeType.setName(source.getName());

        return customerTypeType;
    }
}
