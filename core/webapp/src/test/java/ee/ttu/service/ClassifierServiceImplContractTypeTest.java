package ee.ttu.service;

import ee.ttu.exception.CoreException;
import ee.ttu.model.classifier.ContractType;
import ee.ttu.model.classifier.CustomerType;
import ee.ttu.repository.classifier.ContractTypeRepository;
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

public class ClassifierServiceImplContractTypeTest {
    
    @InjectMocks
    private ClassifierService classifierService = new ClassifierServiceImpl();

    @Mock
    private ContractTypeRepository contractTypeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetContractTypeHandlesException() throws Exception {
        when(contractTypeRepository.findOne(anyLong())).thenThrow(HibernateException.class);

        try {
            classifierService.getContractType(1L);
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find ContractType with id '1'", e.getMessage());

            verify(contractTypeRepository).findOne(1L);
            return;
        }

        Assert.fail();
    }

    @Test
    public void testGetContractTypeDoesNotFindContractType() throws Exception {
        try {
            classifierService.getContractType(1L);
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find ContractType with id '1'", e.getMessage());

            verify(contractTypeRepository).findOne(1L);
            return;
        }

        Assert.fail();
    }

    @Test
    public void testGetCustomerTypeReturnsValidCustomerType() throws Exception {
        ContractType contractType = new ContractType();
        contractType.setId(1L);
        contractType.setName("Name");
        when(contractTypeRepository.findOne(1L)).thenReturn(contractType);

        Assert.assertEquals(contractType, classifierService.getContractType(1L));
        verify(contractTypeRepository).findOne(1L);
    }

    @Test
    public void testGetContractTypeIdMustNotBeNull() throws Exception {
        try {
            classifierService.getContractType(null);
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("ContractType id must not be null!", e.getMessage());
            return;
        }

        Assert.fail();
    }

    @Test
    public void testGetAllContractTypesHandlesException() throws Exception {
        when(contractTypeRepository.findAll()).thenThrow(HibernateException.class);

        List<ContractType> allContractTypes = classifierService.getAllContractTypes();
        Assert.assertNotNull(allContractTypes);
        Assert.assertEquals(0, allContractTypes.size());
        verify(contractTypeRepository).findAll();
    }

    @Test
    public void testGetAllContractTypesFindsNoResults() throws Exception {
        when(contractTypeRepository.findAll()).thenReturn(new ArrayList<ContractType>());

        List<ContractType> allContractTypes = classifierService.getAllContractTypes();
        Assert.assertNotNull(allContractTypes);
        Assert.assertEquals(0, allContractTypes.size());
        verify(contractTypeRepository).findAll();
    }

    @Test
    public void testGetAllContractTypesFindsResults() throws Exception {
        ContractType contractType = new ContractType();
        contractType.setId(1L);
        contractType.setName("Name");
        when(contractTypeRepository.findAll()).thenReturn(Arrays.asList(contractType));

        List<ContractType> allContractTypes = classifierService.getAllContractTypes();
        Assert.assertNotNull(allContractTypes);
        Assert.assertEquals(1, allContractTypes.size());
        Assert.assertEquals(contractType, allContractTypes.get(0));
        verify(contractTypeRepository).findAll();
    }

    @Test
    public void testFindByNameHandlesException() throws Exception {
        when(contractTypeRepository.findByName(anyString())).thenThrow(HibernateException.class);

        try {
            classifierService.findContractTypeByName("12");
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find ContractType with name '12'", e.getMessage());

            verify(contractTypeRepository).findByName("12");
            return;
        }

        Assert.fail();
    }

    @Test
    public void testFindByNameDoesNotFindAResult() throws Exception {
        try {
            classifierService.findContractTypeByName("name");
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find ContractType with name 'name'", e.getMessage());

            verify(contractTypeRepository).findByName("name");
            return;
        }

        Assert.fail();
    }

    @Test
    public void testFindByNameFindsResult() throws Exception {
        ContractType contractType= new ContractType();
        contractType.setId(1L);
        contractType.setName("Name");
        when(contractTypeRepository.findByName("Name")).thenReturn(contractType);

        Assert.assertEquals(contractType, classifierService.findContractTypeByName("Name"));
        verify(contractTypeRepository).findByName("Name");
    }

    @Test
    public void testFindByNameNameMustNotBeNull() throws Exception {
        try {
            classifierService.findContractTypeByName(null);
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("ContractType name must not be null!", e.getMessage());
            return;
        }

        Assert.fail();
    }
}