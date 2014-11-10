package ee.ttu.service.impl;

import ee.ttu.model.ContractDeletionResult;
import ee.ttu.model.ContractSavingResult;
import ee.ttu.service.ContractsService;
import ee.ttu.xml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Created by Vahur Kaar on 9.11.2014.
 */
@Service
public class ContractsServiceImpl implements ContractsService {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Override
    public ContractType findContractById(Long id) {
        GetContractRequest getContractRequest = new GetContractRequest();
        getContractRequest.setId(id);

        GetContractResponse response = (GetContractResponse) webServiceTemplate.marshalSendAndReceive(getContractRequest);
        return response.getContract();
    }

    @Override
    public ContractSavingResult saveContract(ContractType contract) {
        SaveContractRequest request = new SaveContractRequest();
        request.setContract(contract);

        SaveContractResponse response = (SaveContractResponse) webServiceTemplate.marshalSendAndReceive(request);

        ContractSavingResult contractSavingResult = new ContractSavingResult();
        contractSavingResult.setContractType(response.getContract());
        contractSavingResult.setResult(response.getResponseCode());
        contractSavingResult.setMessage(response.getDescription());
        return contractSavingResult;
    }

    @Override
    public ContractDeletionResult deleteContract(Long contractId) {
        DeleteContractRequest deleteContractRequest = new DeleteContractRequest();
        deleteContractRequest.setId(contractId);

        DeleteContractResponse response = (DeleteContractResponse) webServiceTemplate.marshalSendAndReceive(deleteContractRequest);

        ContractDeletionResult result = new ContractDeletionResult();
        result.setResult(response.getResponseCode());
        result.setMessage(response.getDescription());
        return result;
    }
}
