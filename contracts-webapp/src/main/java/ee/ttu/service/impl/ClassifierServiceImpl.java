package ee.ttu.service.impl;

import ee.ttu.service.ClassifierService;
import ee.ttu.xml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.List;

/**
 * Created by Vahur Kaar on 9.11.2014.
 */
@Service
public class ClassifierServiceImpl implements ClassifierService {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Override
    public List<CustomerTypeType> getCustomerTypes() {
        GetCustomerTypesResponse response =
                (GetCustomerTypesResponse) webServiceTemplate.marshalSendAndReceive(new GetCustomerTypesRequest());
        return response.getCustomerType();
    }

    @Override
    public List<CustomerStateTypeType> getCustomerStatusTypes() {
        GetCustomerStateTypesResponse response =
                (GetCustomerStateTypesResponse) webServiceTemplate.marshalSendAndReceive(new GetCustomerStateTypesRequest());
        return response.getCustomerStateType();
    }

    @Override
    public List<ContractTypeType> getContractTypes() {
        GetContractTypesResponse response =
                (GetContractTypesResponse) webServiceTemplate.marshalSendAndReceive(new GetContractTypesRequest());
        return response.getContractType();
    }

    @Override
    public List<ContractStatusTypeType> getContractStatusTypes() {
        GetContractStatusTypesResponse response =
                (GetContractStatusTypesResponse) webServiceTemplate.marshalSendAndReceive(new GetContractStatusTypesRequest());
        return response.getContractStatusType();
    }
}
