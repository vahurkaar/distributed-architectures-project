package ee.ttu.repository;

import ee.ttu.model.Customer;
import ee.ttu.model.CustomerAddress;
import ee.ttu.model.classifier.CustomerStateType;
import ee.ttu.repository.classifier.CustomerStateTypeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.List;

public class CustomerRepositoryTest extends RepositoryTestSupport {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerStateTypeRepository customerStateTypeRepository;

    @Before
    public void setUp() throws Exception {
        executeSqlScript("customer_repository_test.sql", false);
    }

    @Test
    public void testSavingSimpleCustomer() throws Exception {
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
    public void testSavingNewCustomerWithAddresses() throws Exception {
        Customer customer = new Customer();
        customer.setFirstname("Paul");
        customer.setLastname("Pihelgas");
        customer.setIdentityCode("111");

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setEmail("email.com");
        customer.getAddresses().add(customerAddress);

        customerAddress = new CustomerAddress();
        customerAddress.setZip("zipcode");
        customer.getAddresses().add(customerAddress);

        customerRepository.save(customer);

        Customer savedCustomer = customerRepository.findByNameLikeOrIdentityCode(null, "111").get(0);

        Assert.assertNotNull(savedCustomer);
        Assert.assertEquals(2, savedCustomer.getAddresses().size());
        Assert.assertEquals(new Long(3), savedCustomer.getAddresses().get(0).getId());
        Assert.assertEquals("email.com", savedCustomer.getAddresses().get(0).getEmail());
        Assert.assertEquals(new Long(4), savedCustomer.getAddresses().get(1).getId());
        Assert.assertEquals("zipcode", savedCustomer.getAddresses().get(1).getZip());
    }

    @Test
    public void testSavingExistingCustomerAndRemovingAnAddress() throws Exception {
        Customer existingCustomer = customerRepository.findOne(1L);

        existingCustomer.getAddresses().remove(0);
        customerRepository.save(existingCustomer);

        Customer updatedCustomer = customerRepository.findOne(1L);
        Assert.assertEquals(1, updatedCustomer.getAddresses().size());
        Assert.assertEquals("zip 2", updatedCustomer.getAddresses().get(0).getZip());
    }

    @Test
    public void testFindByNameAndIdentityCode() throws Exception {
        List<Customer> customers = customerRepository.findByNameLikeOrIdentityCode("VaH", "123");
        Customer customer = customers.get(0);
        Assert.assertEquals("Vahur", customer.getFirstname());
        Assert.assertEquals("123", customer.getIdentityCode());
    }

    @Test
    public void testCustomerHasAddresses() throws Exception {
        Customer customer = customerRepository.findOne(1L);

        Assert.assertNotNull(customer);
        Assert.assertEquals(2, customer.getAddresses().size());
        Assert.assertEquals(new Long(1), customer.getAddresses().get(0).getId());
        Assert.assertEquals(new Long(2), customer.getAddresses().get(1).getId());
    }
}