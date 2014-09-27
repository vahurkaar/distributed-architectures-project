package ee.ttu.service;

import ee.ttu.exception.CoreException;
import ee.ttu.model.classifier.CustomerType;
import ee.ttu.repository.classifier.CustomerTypeRepository;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClassifierServiceImplCustomerTypeTest {
    
    @InjectMocks
    private ClassifierService classifierService = new ClassifierServiceImpl();

    @Mock
    private CustomerTypeRepository customerTypeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCustomerTypeHandlesException() throws Exception {
        when(customerTypeRepository.findOne(anyLong())).thenThrow(HibernateException.class);

        try {
            classifierService.getCustomerType(1L);
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find CustomerType with id '1'", e.getMessage());

            verify(customerTypeRepository).findOne(1L);
            return;
        }

        Assert.fail();
    }

    @Test
    public void testGetCustomerTypeDoesNotFindCustomerType() throws Exception {
        try {
            classifierService.getCustomerType(1L);
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find CustomerType with id '1'", e.getMessage());

            verify(customerTypeRepository).findOne(1L);
            return;
        }

        Assert.fail();
    }

    @Test
    public void testGetCustomerTypeReturnsValidCustomerType() throws Exception {
        CustomerType customerType = new CustomerType();
        customerType.setId(1L);
        customerType.setName("Name");
        when(customerTypeRepository.findOne(1L)).thenReturn(customerType);

        Assert.assertEquals(customerType, classifierService.getCustomerType(1L));
        verify(customerTypeRepository).findOne(1L);
    }

    @Test
    public void testGetCustomerTypeIdMustNotBeNull() throws Exception {
        try {
            classifierService.getCustomerType(null);
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("CustomerType id must not be null!", e.getMessage());
            return;
        }

        Assert.fail();
    }

    @Test
    public void testGetAllCustomerTypesHandlesException() throws Exception {
        when(customerTypeRepository.findAll()).thenThrow(HibernateException.class);

        List<CustomerType> allCustomerTypes = classifierService.getAllCustomerTypes();
        Assert.assertNotNull(allCustomerTypes);
        Assert.assertEquals(0, allCustomerTypes.size());
        verify(customerTypeRepository).findAll();
    }

    @Test
    public void testGetAllCustomerTypesFindsNoResults() throws Exception {
        when(customerTypeRepository.findAll()).thenReturn(new ArrayList<>());

        List<CustomerType> allCustomerTypes = classifierService.getAllCustomerTypes();
        Assert.assertNotNull(allCustomerTypes);
        Assert.assertEquals(0, allCustomerTypes.size());
        verify(customerTypeRepository).findAll();
    }

    @Test
    public void testGetAllCustomerTypesFindsResults() throws Exception {
        CustomerType customerType = new CustomerType();
        customerType.setId(1L);
        customerType.setName("Name");
        when(customerTypeRepository.findAll()).thenReturn(Arrays.asList(customerType));

        List<CustomerType> allCustomerTypes = classifierService.getAllCustomerTypes();
        Assert.assertNotNull(allCustomerTypes);
        Assert.assertEquals(1, allCustomerTypes.size());
        Assert.assertEquals(customerType, allCustomerTypes.get(0));
        verify(customerTypeRepository).findAll();
    }

    @Test
    public void testFindByNameHandlesException() throws Exception {
        when(customerTypeRepository.findByName(anyString())).thenThrow(HibernateException.class);

        try {
            classifierService.findCustomerTypeByName("12");
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find CustomerType with name '12'", e.getMessage());

            verify(customerTypeRepository).findByName("12");
            return;
        }

        Assert.fail();
    }

    @Test
    public void testFindByNameDoesNotFindAResult() throws Exception {
        try {
            classifierService.findCustomerTypeByName("name");
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find CustomerType with name 'name'", e.getMessage());

            verify(customerTypeRepository).findByName("name");
            return;
        }

        Assert.fail();
    }

    @Test
    public void testFindByNameFindsResult() throws Exception {
        CustomerType customertype = new CustomerType();
        customertype.setId(1L);
        customertype.setName("Name");
        when(customerTypeRepository.findByName("Name")).thenReturn(customertype);

        Assert.assertEquals(customertype, classifierService.findCustomerTypeByName("Name"));
        verify(customerTypeRepository).findByName("Name");
    }

    @Test
    public void testFindByNameNameMustNotBeNull() throws Exception {
        try {
            classifierService.findCustomerTypeByName(null);
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("CustomerType name must not be null!", e.getMessage());
            return;
        }

        Assert.fail();
    }
}