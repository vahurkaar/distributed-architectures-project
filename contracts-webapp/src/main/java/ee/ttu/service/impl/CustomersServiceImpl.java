package ee.ttu.service.impl;

import ee.ttu.form.ContractsSearchForm;
import ee.ttu.service.ClassifierService;
import ee.ttu.service.CustomersService;
import ee.ttu.xml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.List;

/**
 * Created by Vahur Kaar on 9.11.2014.
 */
@Service
public class CustomersServiceImpl implements CustomersService {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Autowired
    private ClassifierService classifierService;

    @Autowired
    private Environment environment;

    @Override
    public List<CustomerType> getCustomers(ContractsSearchForm contractsSearchForm) {
        GetCustomersRequest customersRequest = buildGetCustomersRequest(contractsSearchForm);
        GetCustomersResponse customersResponse =
                (GetCustomersResponse) webServiceTemplate.marshalSendAndReceive(customersRequest);

        return customersResponse.getCustomer();
    }

    private GetCustomersRequest buildGetCustomersRequest(ContractsSearchForm contractsSearchForm) {
        GetCustomersRequest getCustomersRequest = new GetCustomersRequest();
        getCustomersRequest.setName(contractsSearchForm.getCustomerName());
        getCustomersRequest.setContractName(contractsSearchForm.getContractName());
        getCustomersRequest.setContractNumber(contractsSearchForm.getContractNumber());

        return getCustomersRequest;
    }

    @Override
    public CustomerType getCustomerById(Long customerId) {
        GetCustomersRequest customersRequest = new GetCustomersRequest();
        customersRequest.setId(customerId);

        GetCustomersResponse response = (GetCustomersResponse) webServiceTemplate.marshalSendAndReceive(customersRequest);

        if (response.getCustomer().size() > 0) {
            return response.getCustomer().get(0);
        }

        return null;
    }

    @Override
    public void upgradeCustomer(Long id) {
        CustomerType customerType = getCustomerById(id);
        List<CustomerTypeType> customerTypeTypes = classifierService.getCustomerTypes();
        customerType.setCustomerType(getCustomerTypeByName(customerTypeTypes, environment.getProperty("customer.goodCustomerType")));

        SaveCustomerRequest saveCustomerRequest = new SaveCustomerRequest();
        saveCustomerRequest.setCustomer(customerType);
        SaveCustomerResponse response = (SaveCustomerResponse) webServiceTemplate.marshalSendAndReceive(saveCustomerRequest);

        if (!response.getResponseCode().equals("OK")) {
            throw new RuntimeException("Upgrade failed!");
        }
    }

    @Override
    public void downgradeCustomerIfNeccesary(Long id) {
        CustomerType customerType = getCustomerById(id);
        Double sum = findActiveContractsSum(customerType);

        if (sum < Double.valueOf(environment.getProperty("customer.goodCustomerThreshold"))) {
            List<CustomerTypeType> customerTypeTypes = classifierService.getCustomerTypes();
            customerType.setCustomerType(getCustomerTypeByName(customerTypeTypes, environment.getProperty("customer.regularCustomerType")));

            SaveCustomerRequest saveCustomerRequest = new SaveCustomerRequest();
            saveCustomerRequest.setCustomer(customerType);
            SaveCustomerResponse response = (SaveCustomerResponse) webServiceTemplate.marshalSendAndReceive(saveCustomerRequest);

            if (!response.getResponseCode().equals("OK")) {
                throw new RuntimeException("Downgrade failed!");
            }
        }
    }

    private Double findActiveContractsSum(CustomerType customerType) {
        Double sum = 0.0;
        Long activeContractStatusTypeId = getContractStatusTypeByName(environment.getProperty("customer.activeContractStatusType"));
        if (customerType.getContracts() != null) {
            for (ContractType contract : customerType.getContracts().getContract()) {
                if (activeContractStatusTypeId.equals(contract.getContractStatusType())) {
                    sum += contract.getValueAmount();
                }
            }
        }

        return sum;
    }

    private Long getContractStatusTypeByName(String name) {
        for (ContractStatusTypeType contractStatusTypeType : classifierService.getContractStatusTypes()) {
            if (name.equals(contractStatusTypeType.getName())) {
                return contractStatusTypeType.getId();
            }
        }

        return null;
    }

    private Long getCustomerTypeByName(List<CustomerTypeType> customerTypeTypes, String thresholdCustomerType) {
        for (CustomerTypeType customerTypeType : customerTypeTypes) {
            if (thresholdCustomerType.equals(customerTypeType.getName())) {
                return customerTypeType.getId();
            }
        }

        return null;
    }
}
