package ee.ttu.repository;

import ee.ttu.configuration.PersistenceConfiguration;
import ee.ttu.model.Customer;
import ee.ttu.model.CustomerStateType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = {PersistenceConfiguration.class, EmbeddedDatasourceConfiguration.class})
@TransactionConfiguration(transactionManager = "transactionManager")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerStateTypeRepository customerStateTypeRepository;

    @BeforeTransaction
    public void setUp() throws Exception {
        executeSqlScript("customer_repository_test.sql", false);
    }

    @Test
    @Transactional
    @Rollback
    public void testSavingTest() throws Exception {
        CustomerStateType customerStateType = customerStateTypeRepository.findOne(1L);
        Customer customer = new Customer();
        customer.setFirstname("toomas");
        customer.setLastname("sinikas");
        customer.setCustomerStateType(customerStateType);

        customerRepository.save(customer);

        Customer savedCustomer = customerRepository.findOne(2L);

        Assert.assertEquals(new Long(2), savedCustomer.getId());
        Assert.assertEquals(customer.getFirstname(), savedCustomer.getFirstname());
        Assert.assertEquals(customer.getLastname(), savedCustomer.getLastname());
        Assert.assertNotNull(savedCustomer.getCreated());
        Assert.assertNotNull(savedCustomer.getCustomerStateType());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindByNameAndIdentityCode() throws Exception {
        List<Customer> customers = customerRepository.findByNameLikeOrIdentityCode("VaH", "123");
        Customer customer = customers.get(0);
        Assert.assertEquals("Vahur", customer.getFirstname());
        Assert.assertEquals("123", customer.getIdentityCode());
    }

    @Test
    @Transactional
    @Rollback
    public void testCustomerHasAddresses() throws Exception {
        Customer customer = customerRepository.findOne(1L);

        Assert.assertNotNull(customer);
        Assert.assertEquals(2, customer.getAddresses().size());
        Assert.assertEquals(customer.getId(), customer.getAddresses().get(0).getCustomerId());
        Assert.assertEquals(customer.getId(), customer.getAddresses().get(1).getCustomerId());
    }
}