package ee.ttu.service;

import ee.ttu.converter.CustomerConverter;
import ee.ttu.converter.CustomerTypeConverter;
import ee.ttu.model.Customer;
import ee.ttu.repository.CustomerRepository;
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
@Endpoint
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

        if (request.getId() != null) {
            Customer customer = customerRepository.findOne(request.getId());

            if (customer != null) {
                getCustomersResponse.getCustomer().add(customerConverter.convert(customer));
                return getCustomersResponse;
            }
        }

        List<Customer> customers = customerRepository
                .findByNameLikeOrIdentityCode(request.getName(), request.getIdentityCode());

        for (Customer customer : customers) {
            getCustomersResponse.getCustomer().add(customerConverter.convert(customer));
        }

        return getCustomersResponse;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = SAVE_CUSTOMER_REQUEST)
    @ResponsePayload
    @Override
    public SaveCustomerResponse saveCustomer(@RequestPayload SaveCustomerRequest request) {
        SaveCustomerResponse saveCustomerResponse = new SaveCustomerResponse();

        Customer customer = customerTypeConverter.convert(request.getCustomer());
        customerRepository.save(customer);

        saveCustomerResponse.setResponseCode("OK");
        saveCustomerResponse.setDescription("Successfully saved customer!");

        return saveCustomerResponse;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = DELETE_CUSTOMER_REQUEST)
    @ResponsePayload
    @Override
    public DeleteCustomerResponse deleteCustomer(@RequestPayload DeleteCustomerRequest request) {
        DeleteCustomerResponse deleteCustomerResponse = new DeleteCustomerResponse();
        Long id = request.getId();

        customerRepository.delete(id);

        deleteCustomerResponse.setResponseCode("OK");
        deleteCustomerResponse.setDescription("Successfully deleted customer!");

        return deleteCustomerResponse;
    }

}
