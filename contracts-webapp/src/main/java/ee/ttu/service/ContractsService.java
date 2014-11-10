package ee.ttu.service;

import ee.ttu.model.ContractDeletionResult;
import ee.ttu.model.ContractSavingResult;
import ee.ttu.xml.ContractType;

/**
 * Created by Vahur Kaar on 1.11.2014.
 */
public interface ContractsService {

    ContractType findContractById(Long id);

    ContractSavingResult saveContract(ContractType contract);


    ContractDeletionResult deleteContract(Long contractId);
}
