package ee.ttu.repository;

import ee.ttu.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vahur Kaar on 20.09.2014.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("select c from Customer c where trim(lower(concat(c.firstname, ' ', c.lastname))) like :name or c.identityCode = :identityCode")
    List<Customer> findByNameLikeOrIdentityCode(@Param("name") String name,
                                                @Param("identityCode") String identityCode);

}
