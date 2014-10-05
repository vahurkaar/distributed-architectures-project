package ee.ttu.endpoints;

import ee.ttu.converter.*;
import ee.ttu.model.Customer;
import ee.ttu.model.classifier.*;
import ee.ttu.repository.CustomerRepository;
import ee.ttu.service.ClassifierService;
import ee.ttu.xml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

/**
 * Created by Vahur Kaar on 24.09.2014.
 */
@Endpoint(value = "customerServiceEndpoint")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private CustomerTypeConverter customerTypeConverter;

    @PayloadRoot(namespace = NAMESPACE, localPart = GET_CUSTOMERS_REQUEST)
    @ResponsePayload
    @Override
    public GetCustomersResponse getCustomers(@RequestPayload GetCustomersRequest request) {
        GetCustomersResponse getCustomersResponse = new GetCustomersResponse();

        Customer singleCustomer = null;
        if (request.getId() != null) {
            singleCustomer = customerRepository.findOne(request.getId());
        } else if (request.getIdentityCode() != null) {
            singleCustomer = customerRepository.findByIdentityCode(request.getIdentityCode());
        }

        if (singleCustomer != null) {
            getCustomersResponse.getCustomer().add(customerConverter.convert(singleCustomer));
            return getCustomersResponse;
        }

        if (request.getName() != null) {
            List<Customer> customers = customerRepository.findByNameLike(request.getName());
            for (Customer customer : customers) {
                getCustomersResponse.getCustomer().add(customerConverter.convert(customer));
            }
        }

        return getCustomersResponse;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = SAVE_CUSTOMER_REQUEST)
    @ResponsePayload
    @Override
    public SaveCustomerResponse saveCustomer(@RequestPayload SaveCustomerRequest request) {
        SaveCustomerResponse saveCustomerResponse = new SaveCustomerResponse();
        Customer customer = customerTypeConverter.convert(request.getCustomer());

        if (customerExists(customer)) {
            saveCustomerResponse.setResponseCode("NOT OK");
            saveCustomerResponse.setDescription(String.format("Customer with identity code (%s) already exists!", customer.getIdentityCode()));
            return saveCustomerResponse;
        }

        customerRepository.save(customer);
        saveCustomerResponse.setResponseCode("OK");
        saveCustomerResponse.setDescription("Successfully saved customer!");

        return saveCustomerResponse;
    }

    private boolean customerExists(Customer customer) {
        if (customer != null && customer.getIdentityCode() != null) {
            Customer existingCustomer = customerRepository.findByIdentityCode(customer.getIdentityCode());
            return (customer.getId() == null || !customerRepository.exists(customer.getId()))
                    && existingCustomer != null;
        }

        return false;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = DELETE_CUSTOMER_REQUEST)
    @ResponsePayload
    @Override
    public DeleteCustomerResponse deleteCustomer(@RequestPayload DeleteCustomerRequest request) {
        DeleteCustomerResponse deleteCustomerResponse = new DeleteCustomerResponse();
        Long id = request.getId();

        if (customerRepository.exists(id)) {
            customerRepository.delete(id);

            deleteCustomerResponse.setResponseCode("OK");
            deleteCustomerResponse.setDescription("Successfully deleted customer!");
        } else {
            deleteCustomerResponse.setResponseCode("NOT OK");
            deleteCustomerResponse.setDescription("Customer does not exist!");
        }

        return deleteCustomerResponse;
    }
}