package ee.ttu.repository;

import ee.ttu.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Vahur Kaar on 20.09.2014.
 */
@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
