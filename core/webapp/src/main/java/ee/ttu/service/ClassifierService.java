package ee.ttu.service;

import ee.ttu.model.classifier.Country;
import ee.ttu.model.classifier.CustomerStateType;
import ee.ttu.model.classifier.CustomerType;

import java.util.List;

/**
 * Created by Vahur Kaar on 27.09.2014.
 */
public interface ClassifierService {

    CustomerType getCustomerType(Long id);

    List<CustomerType> getAllCustomerTypes();

    CustomerType findCustomerTypeByName(String name);


    Country getCountry(Long id);

    List<Country> getAllCountries();

    Country findCountryByName(String name);


    CustomerStateType getCustomerStateType(Long id);

    List<CustomerStateType> getAllCustomerStateTypes();

    CustomerStateType findCustomerStateTypeByName(String name);
}
