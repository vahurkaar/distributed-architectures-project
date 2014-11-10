package ee.ttu.model;

import ee.ttu.xml.ContractType;

/**
 * Created by Vahur Kaar on 9.11.2014.
 */
public class ContractSavingResult {

    private ContractType contractType;

    private String result;

    private String message;

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
