package ee.ttu.endpoints;

import ee.ttu.converter.ContractConverter;
import ee.ttu.converter.ContractTypeConverter;
import ee.ttu.exception.CoreException;
import ee.ttu.model.Contract;
import ee.ttu.model.Customer;
import ee.ttu.repository.ContractRepository;
import ee.ttu.repository.CustomerRepository;
import ee.ttu.service.*;
import ee.ttu.xml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by Vahur Kaar on 18.10.2014.
 */
@Endpoint(value = "contractServiceEndpoint")
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ContractTypeConverter contractTypeConverter;

    @Autowired
    private ContractConverter contractConverter;

    @Autowired
    private ee.ttu.service.ClassifierService classifierService;

    @PayloadRoot(namespace = NAMESPACE, localPart = GET_CONTRACT_REQUEST)
    @ResponsePayload
    @Override
    public GetContractResponse getContract(@RequestPayload GetContractRequest request) {
        Contract contract = contractRepository.findOne(request.getId());

        GetContractResponse response = new GetContractResponse();
        if (contract != null) {
            response.setContract(contractTypeConverter.convert(contract));
        } else {
            throw new CoreException(String.format("Contract with id %s could not be found!", request.getId()));
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = SAVE_CONTRACT_REQUEST)
    @ResponsePayload
    @Override
    public SaveContractResponse saveContract(@RequestPayload SaveContractRequest request) {
        Contract contract = contractConverter.convert(request.getContract());
        validateContract(contract);

        Contract savedContract = contractRepository.save(contract);
        SaveContractResponse saveContractResponse = new SaveContractResponse();
        saveContractResponse.setResponseCode("OK");
        saveContractResponse.setDescription("Successfully saved contract!");
        saveContractResponse.setContract(contractTypeConverter.convert(savedContract));
        return saveContractResponse;
    }

    private void validateContract(Contract contract) {
        if (contract.getContractNumber() != null) {
            Contract foundContract = contractRepository.findByContractNumber(contract.getContractNumber());

            if (foundContract != null && (contract.getId() == null || !contract.getId().equals(foundContract.getId()))) {
                throw new CoreException(String.format("Contract with the number '%s' already exists!",
                        contract.getContractNumber()));
            }
        }

        if (contract.getName() != null) {
            Contract foundContract = contractRepository.findByName(contract.getName());

            if (foundContract != null && (contract.getId() == null || !contract.getId().equals(foundContract.getId()))) {
                throw new CoreException(String.format("Contract with the name '%s' already exists!",
                        contract.getName()));
            }
        }
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = DELETE_CONTRACT_REQUEST)
    @ResponsePayload
    @Override
    public DeleteContractResponse deleteContract(@RequestPayload DeleteContractRequest request) {
        if (!contractRepository.exists(request.getId())) {
            throw new CoreException(String.format("Contract with id '%s' does not exist!", request.getId()));
        }

        Contract contract = contractRepository.findOne(request.getId());
        contract.setContractStatusType(classifierService.findContractStatusTypeByName("Kustutatud"));
        contractRepository.save(contract);

        DeleteContractResponse response = new DeleteContractResponse();
        response.setResponseCode("OK");
        response.setDescription("Successfully deleted contract!");
        return response;
    }
}
