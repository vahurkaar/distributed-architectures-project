package ee.ttu.endpoints;

import ee.ttu.xml.*;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by Vahur Kaar on 24.09.2014.
 */
public interface CustomerService {

    public static final String NAMESPACE = "http://www.ttu.ee/hajusarhitektuurid";

    public static final String GET_CUSTOMERS_REQUEST = "GetCustomersRequest";
    public static final String SAVE_CUSTOMER_REQUEST = "SaveCustomerRequest";
    public static final String DELETE_CUSTOMER_REQUEST = "DeleteCustomerRequest";

    GetCustomersResponse getCustomers(GetCustomersRequest request);

    SaveCustomerResponse saveCustomer(SaveCustomerRequest request);

    DeleteCustomerResponse deleteCustomer(DeleteCustomerRequest request);

}
