package ee.ttu.repository;

import ee.ttu.model.CustomerType;

import javax.transaction.Transactional;

/**
 * Created by Vahur Kaar on 21.09.2014.
 */
@Transactional
public interface CustomerTypeRepository extends ClassifierRepository<CustomerType, Long> {
}
