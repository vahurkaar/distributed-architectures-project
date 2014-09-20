package ee.ttu.repository;

import ee.ttu.configuration.PersistenceConfiguration;
import ee.ttu.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = {PersistenceConfiguration.class, EmbeddedDatasourceConfiguration.class})
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testSavingTest() throws Exception {
        Customer customer = new Customer();
        customer.setFirstname("vahur");
        customer.setLastname("kaar");

        customerRepository.save(customer);

        Iterable<Customer> savedCustomers = customerRepository.findAll();
        Customer savedCustomer = savedCustomers.iterator().next();

        Assert.assertEquals(customer.getFirstname(), savedCustomer.getFirstname());
        Assert.assertEquals(customer.getLastname(), savedCustomer.getLastname());
    }
}