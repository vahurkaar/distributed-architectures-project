package ee.ttu.repository.classifier;

import ee.ttu.model.classifier.CustomerType;
import ee.ttu.repository.RepositoryTestSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerTypeRepositoryTest extends RepositoryTestSupport {

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @Before
    public void setUp() throws Exception {
        executeSqlScript("sql/customers/customer_type_repository_test.sql", false);
    }

    @Test
    public void testFindByName() throws Exception {
        CustomerType customerType = customerTypeRepository.findByName("Suurklient");

        Assert.assertNotNull(customerType);
        Assert.assertEquals(new Long(4), customerType.getId());
        Assert.assertEquals("Suurklient", customerType.getName());
    }

    @Test
    public void testFindAll() throws Exception {
        List<CustomerType> customerTypeList = customerTypeRepository.findAll();

        Assert.assertEquals(4, customerTypeList.size());
    }
}