package ee.ttu.endpoints;

import ee.ttu.converter.CountryClassifierConverter;
import ee.ttu.converter.CustomerStateTypeClassifierConverter;
import ee.ttu.converter.CustomerTypeClassifierConverter;
import ee.ttu.model.classifier.Country;
import ee.ttu.model.classifier.CustomerStateType;
import ee.ttu.xml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by Vahur Kaar on 1.10.2014.
 */
@Endpoint(value = "classifierServiceEndpoint")
public class ClassifierServiceImpl implements ClassifierService {

    @Autowired
    private ee.ttu.service.ClassifierService classifierService;

    @Autowired
    private CustomerTypeClassifierConverter customerTypeClassifierConverter;

    @Autowired
    private CustomerStateTypeClassifierConverter customerStateTypeClassifierConverter;

    @Autowired
    private CountryClassifierConverter countryClassifierConverter;

    @PayloadRoot(namespace = NAMESPACE, localPart = GET_CUSTOMER_TYPES_REQUEST)
    @ResponsePayload
    @Override
    public GetCustomerTypesResponse getCustomerTypes(@RequestPayload GetCustomerTypesRequest request) {
        GetCustomerTypesResponse response = new GetCustomerTypesResponse();
        for (ee.ttu.model.classifier.CustomerType customerType : classifierService.getAllCustomerTypes()) {
            response.getCustomerType().add(customerTypeClassifierConverter.convert(customerType));
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = GET_CUSTOMER_STATE_TYPES_REQUEST)
    @ResponsePayload
    @Override
    public GetCustomerStateTypesResponse getCustomerStateTypes(@RequestPayload GetCustomerStateTypesRequest request) {
        GetCustomerStateTypesResponse response = new GetCustomerStateTypesResponse();
        for (CustomerStateType customerStateType : classifierService.getAllCustomerStateTypes()) {
            response.getCustomerStateType().add(customerStateTypeClassifierConverter.convert(customerStateType));
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = GET_COUNTRIES_REQUEST)
    @ResponsePayload
    @Override
    public GetCountriesResponse getCountries(@RequestPayload GetCountriesRequest request) {
        GetCountriesResponse response = new GetCountriesResponse();
        for (Country country : classifierService.getAllCountries()) {
            response.getCountry().add(countryClassifierConverter.convert(country));
        }

        return response;
    }
}
