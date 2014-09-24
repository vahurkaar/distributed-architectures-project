package ee.ttu.service;

import ee.ttu.converter.CustomerConverter;
import ee.ttu.model.Customer;
import ee.ttu.repository.CustomerRepository;
import ee.ttu.xml.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    private CustomerRepository customerRepository;

    private CustomerConverter customerConverter;

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
    public ResponseType saveCustomer(@RequestPayload SaveCustomerRequest request) {
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = DELETE_CUSTOMER_REQUEST)
    @ResponsePayload
    @Override
    public ResponseType deleteCustomer(@RequestPayload DeleteCustomerRequest request) {
        return null;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setCustomerConverter(CustomerConverter customerConverter) {
        this.customerConverter = customerConverter;
    }
}
