package ee.ttu.repository;

import ee.ttu.model.Customer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Vahur Kaar on 19.10.2014.
 */
@Repository
public class CustomerJpaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Customer> findCustomers(String name, String contractNumber, String contractName) {
        StringBuilder queryString = new StringBuilder("select c from Customer c");
        if (StringUtils.isNotBlank(contractNumber) || StringUtils.isNotBlank(contractName)) {
            queryString.append(" left join c.contracts ct");
        }

        queryString.append(" where 1 = 1");

        if (StringUtils.isNotBlank(name)) {
            queryString.append(" and (:name is null or trim(lower(concat(c.firstname, ' ', c.lastname))) like lower(concat('%', :name, '%')))");
        }

        if (StringUtils.isNotBlank(contractNumber)) {
            queryString.append(" and (:contractNumber is null or lower(ct.contractNumber) like lower(concat('%', :contractNumber, '%')))");
        }

        if (StringUtils.isNotBlank(contractName)) {
            queryString.append(" and (:contractName is null or lower(ct.name) like lower(concat('%', :contractName, '%')))");
        }

        Query query = entityManager.createQuery(queryString.toString());

        if (StringUtils.isNotBlank(name)) {
            query.setParameter("name", name);
        }

        if (StringUtils.isNotBlank(contractName)) {
            query.setParameter("contractName", contractName);
        }

        if (StringUtils.isNotBlank(contractNumber)) {
            query.setParameter("contractNumber", contractNumber);
        }

        return (List<Customer>) query.getResultList();
    }

}
