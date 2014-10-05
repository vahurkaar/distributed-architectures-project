package ee.ttu.repository;

import ee.ttu.model.EmployeeUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Vahur Kaar on 5.10.2014.
 */
public interface EmployeeUserRepository extends CrudRepository<EmployeeUser, Long> {

    EmployeeUser findByUsername(String username);

}
