package ee.ttu.service;

import ee.ttu.exception.CoreException;
import ee.ttu.model.classifier.CustomerStateType;
import ee.ttu.repository.classifier.CustomerStateTypeRepository;
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

public class ClassifierServiceImplCustomerStateTypeTest {

    @InjectMocks
    private ClassifierService classifierService = new ClassifierServiceImpl();

    @Mock
    private CustomerStateTypeRepository customerStateTypeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCustomerStateTypeHandlesException() throws Exception {
        when(customerStateTypeRepository.findOne(anyLong())).thenThrow(HibernateException.class);

        try {
            classifierService.getCustomerStateType(1L);
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find CustomerStateType with id '1'", e.getMessage());

            verify(customerStateTypeRepository).findOne(1L);
            return;
        }

        Assert.fail();
    }

    @Test
    public void testGetCustomerStateTypeDoesNotFindCustomerStateType() throws Exception {
        try {
            classifierService.getCustomerStateType(1L);
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find CustomerStateType with id '1'", e.getMessage());

            verify(customerStateTypeRepository).findOne(1L);
            return;
        }

        Assert.fail();
    }

    @Test
    public void testGetCustomerStateTypeReturnsValidCustomerStateType() throws Exception {
        CustomerStateType customerStateType = new CustomerStateType();
        customerStateType.setId(1L);
        customerStateType.setName("Name");
        when(customerStateTypeRepository.findOne(1L)).thenReturn(customerStateType);

        Assert.assertEquals(customerStateType, classifierService.getCustomerStateType(1L));
        verify(customerStateTypeRepository).findOne(1L);
    }

    @Test
    public void testGetCustomerStateTypeIdMustNotBeNull() throws Exception {
        try {
            classifierService.getCustomerStateType(null);
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("CustomerStateType id must not be null!", e.getMessage());
            return;
        }

        Assert.fail();
    }

    @Test
    public void testGetAllCustomerStateTypesHandlesException() throws Exception {
        when(customerStateTypeRepository.findAll()).thenThrow(HibernateException.class);

        List<CustomerStateType> allCustomerStateTypes = classifierService.getAllCustomerStateTypes();
        Assert.assertNotNull(allCustomerStateTypes);
        Assert.assertEquals(0, allCustomerStateTypes.size());
        verify(customerStateTypeRepository).findAll();
    }

    @Test
    public void testGetAllCustomerStateTypesFindsNoResults() throws Exception {
        when(customerStateTypeRepository.findAll()).thenReturn(new ArrayList<CustomerStateType>());

        List<CustomerStateType> allCustomerStateTypes = classifierService.getAllCustomerStateTypes();
        Assert.assertNotNull(allCustomerStateTypes);
        Assert.assertEquals(0, allCustomerStateTypes.size());
        verify(customerStateTypeRepository).findAll();
    }

    @Test
    public void testGetAllCustomerStateTypesFindsResults() throws Exception {
        CustomerStateType customerStateType = new CustomerStateType();
        customerStateType.setId(1L);
        customerStateType.setName("Name");
        when(customerStateTypeRepository.findAll()).thenReturn(Arrays.asList(customerStateType));

        List<CustomerStateType> allCustomerStateTypes = classifierService.getAllCustomerStateTypes();
        Assert.assertNotNull(allCustomerStateTypes);
        Assert.assertEquals(1, allCustomerStateTypes.size());
        Assert.assertEquals(customerStateType, allCustomerStateTypes.get(0));
        verify(customerStateTypeRepository).findAll();
    }

    @Test
    public void testFindByNameHandlesException() throws Exception {
        when(customerStateTypeRepository.findByName(anyString())).thenThrow(HibernateException.class);

        try {
            classifierService.findCustomerStateTypeByName("12");
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find CustomerStateType with name '12'", e.getMessage());

            verify(customerStateTypeRepository).findByName("12");
            return;
        }

        Assert.fail();
    }

    @Test
    public void testFindByNameDoesNotFindAResult() throws Exception {
        try {
            classifierService.findCustomerStateTypeByName("name");
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find CustomerStateType with name 'name'", e.getMessage());

            verify(customerStateTypeRepository).findByName("name");
            return;
        }

        Assert.fail();
    }

    @Test
    public void testFindByNameFindsResult() throws Exception {
        CustomerStateType customertype = new CustomerStateType();
        customertype.setId(1L);
        customertype.setName("Name");
        when(customerStateTypeRepository.findByName("Name")).thenReturn(customertype);

        Assert.assertEquals(customertype, classifierService.findCustomerStateTypeByName("Name"));
        verify(customerStateTypeRepository).findByName("Name");
    }

    @Test
    public void testFindByNameNameMustNotBeNull() throws Exception {
        try {
            classifierService.findCustomerStateTypeByName(null);
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("CustomerStateType name must not be null!", e.getMessage());
            return;
        }

        Assert.fail();
    }
}