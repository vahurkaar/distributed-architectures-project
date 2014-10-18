package ee.ttu.repository;

import ee.ttu.model.Contract;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Vahur Kaar on 18.10.2014.
 */
public interface ContractRepository extends CrudRepository<Contract, Long> {

    Contract findByContractNumber(String contractNumber);

    Contract findByName(String contractNumber);
}
