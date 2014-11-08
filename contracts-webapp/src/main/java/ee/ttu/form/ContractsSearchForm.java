package ee.ttu.form;

import java.io.Serializable;

/**
 * Created by Vahur Kaar on 3.11.2014.
 */
public class ContractsSearchForm implements Serializable {

    private String customerName;

    private String contractName;

    private String contractNumber;


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
}
