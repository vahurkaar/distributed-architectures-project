package ee.ttu.service;

import ee.ttu.xml.*;

import java.util.List;

/**
 * Created by Vahur Kaar on 1.11.2014.
 */
public interface ClassifierService {

    List<CustomerTypeType> getCustomerTypes();

    List<CustomerStateTypeType> getCustomerStatusTypes();

    List<ContractTypeType> getContractTypes();

    List<ContractStatusTypeType> getContractStatusTypes();
}
