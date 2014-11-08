package ee.ttu.repository;

import ee.ttu.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Vahur Kaar on 20.09.2014.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByIdentityCode(String identityCode);

}
