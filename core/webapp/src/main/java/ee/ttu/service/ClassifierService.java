package ee.ttu.service;

import ee.ttu.model.classifier.*;

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

    List<ContractType> getAllContractTypes();


    ContractType getContractType(Long id);

    CustomerStateType findCustomerStateTypeByName(String name);

    ContractType findContractTypeByName(String name);


    ContractStatusType getContractStatusType(Long id);

    List<ContractStatusType> getAllContractStatusTypes();

    ContractStatusType findContractStatusTypeByName(String name);
}
