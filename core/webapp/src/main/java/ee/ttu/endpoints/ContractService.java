package ee.ttu.endpoints;

import ee.ttu.xml.*;

/**
 * Created by Vahur Kaar on 24.09.2014.
 */
public interface ContractService {

    public static final String NAMESPACE = "http://www.ttu.ee/hajusarhitektuurid";

    public static final String GET_CONTRACT_REQUEST = "GetContractRequest";
    public static final String SAVE_CONTRACT_REQUEST = "SaveContractRequest";
    public static final String DELETE_CONTRACT_REQUEST = "DeleteContractRequest";

    GetContractResponse getContract(GetContractRequest request);

    SaveContractResponse saveContract(SaveContractRequest request);

    DeleteContractResponse deleteContract(DeleteContractRequest request);

}
