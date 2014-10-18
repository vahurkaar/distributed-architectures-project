package ee.ttu.repository.classifier;

import ee.ttu.model.classifier.CustomerStateType;
import ee.ttu.repository.RepositoryTestSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerStateTypeRepositoryTest extends RepositoryTestSupport {

    @Autowired
    private CustomerStateTypeRepository customerStateTypeRepository;

    @Before
    public void setUp() throws Exception {
        executeSqlScript("sql/customers/customer_state_type_repository_test.sql", false);
    }

    @Test
    public void testFindByName() throws Exception {
        CustomerStateType customerType = customerStateTypeRepository.findByName("Suletud");

        Assert.assertNotNull(customerType);
        Assert.assertEquals(new Long(2), customerType.getId());
        Assert.assertEquals("Suletud", customerType.getName());
    }

    @Test
    public void testFindAll() throws Exception {
        List<CustomerStateType> customerTypeList = customerStateTypeRepository.findAll();

        Assert.assertEquals(2, customerTypeList.size());
    }

}