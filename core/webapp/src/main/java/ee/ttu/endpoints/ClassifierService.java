package ee.ttu.endpoints;

import ee.ttu.xml.*;

/**
 * Created by Vahur Kaar on 1.10.2014.
 */
public interface ClassifierService {

    public static final String NAMESPACE = "http://www.ttu.ee/hajusarhitektuurid";

    public static final String GET_CUSTOMER_TYPES_REQUEST = "GetCustomerTypesRequest";
    public static final String GET_CUSTOMER_STATE_TYPES_REQUEST = "GetCustomerStateTypesRequest";
    public static final String GET_COUNTRIES_REQUEST = "GetCountriesRequest";
    public static final String GET_CONTRACT_TYPES_REQUEST = "GetContractTypesRequest";
    public static final String GET_CONTRACT_STATUS_TYPES_REQUEST = "GetContractStatusTypesRequest";

    GetCustomerTypesResponse getCustomerTypes(GetCustomerTypesRequest request);

    GetCustomerStateTypesResponse getCustomerStateTypes(GetCustomerStateTypesRequest request);

    GetCountriesResponse getCountries(GetCountriesRequest request);

    GetContractTypesResponse getContractTypes(GetContractTypesRequest request);

    GetContractStatusTypesResponse getContractStatusTypes(GetContractStatusTypesRequest request);

}
