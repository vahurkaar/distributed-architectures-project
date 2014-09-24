package ee.ttu.service;

import ee.ttu.xml.*;

/**
 * Created by Vahur Kaar on 24.09.2014.
 */
public interface CustomerService {

    public static final String NAMESPACE = "http://www.ttu.ee/hajusarhitektuurid";

    public static final String GET_CUSTOMERS_REQUEST = "GetCustomersRequest";
    public static final String SAVE_CUSTOMER_REQUEST = "SaveCustomerRequest";
    public static final String DELETE_CUSTOMER_REQUEST = "DeleteCustomerRequest";

    GetCustomersResponse getCustomers(GetCustomersRequest request);

    ResponseType saveCustomer(SaveCustomerRequest request);

    ResponseType deleteCustomer(DeleteCustomerRequest request);

}
