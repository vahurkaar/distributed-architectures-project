package ee.ttu.service;

import ee.ttu.form.ContractsSearchForm;
import ee.ttu.xml.CustomerType;

import java.util.List;

/**
 * Created by Vahur Kaar on 1.11.2014.
 */
public interface CustomersService {

    List<CustomerType> getCustomers(ContractsSearchForm contractsSearchForm);

    CustomerType getCustomerById(Long customerId);

    void upgradeCustomer(Long id);

    void downgradeCustomerIfNeccesary(Long id);
}
