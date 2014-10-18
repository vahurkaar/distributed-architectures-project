package ee.ttu.service;

import ee.ttu.exception.CoreException;
import ee.ttu.model.classifier.ContractStatusType;
import ee.ttu.repository.classifier.ContractStatusTypeRepository;
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

public class ClassifierServiceImplContractStatusTypeTest {
    
    @InjectMocks
    private ClassifierService classifierService = new ClassifierServiceImpl();

    @Mock
    private ContractStatusTypeRepository contractStatusTypeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetContractStatusTypeHandlesException() throws Exception {
        when(contractStatusTypeRepository.findOne(anyLong())).thenThrow(HibernateException.class);

        try {
            classifierService.getContractStatusType(1L);
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find ContractStatusType with id '1'", e.getMessage());

            verify(contractStatusTypeRepository).findOne(1L);
            return;
        }

        Assert.fail();
    }

    @Test
    public void testGetContractStatusTypeDoesNotFindContractStatusType() throws Exception {
        try {
            classifierService.getContractStatusType(1L);
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find ContractStatusType with id '1'", e.getMessage());

            verify(contractStatusTypeRepository).findOne(1L);
            return;
        }

        Assert.fail();
    }

    @Test
    public void testGetCustomerTypeReturnsValidCustomerType() throws Exception {
        ContractStatusType contractStatusType = new ContractStatusType();
        contractStatusType.setId(1L);
        contractStatusType.setName("Name");
        when(contractStatusTypeRepository.findOne(1L)).thenReturn(contractStatusType);

        Assert.assertEquals(contractStatusType, classifierService.getContractStatusType(1L));
        verify(contractStatusTypeRepository).findOne(1L);
    }

    @Test
    public void testGetContractStatusTypeIdMustNotBeNull() throws Exception {
        try {
            classifierService.getContractStatusType(null);
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("ContractStatusType id must not be null!", e.getMessage());
            return;
        }

        Assert.fail();
    }

    @Test
    public void testGetAllContractStatusTypesHandlesException() throws Exception {
        when(contractStatusTypeRepository.findAll()).thenThrow(HibernateException.class);

        List<ContractStatusType> allContractStatusTypes = classifierService.getAllContractStatusTypes();
        Assert.assertNotNull(allContractStatusTypes);
        Assert.assertEquals(0, allContractStatusTypes.size());
        verify(contractStatusTypeRepository).findAll();
    }

    @Test
    public void testGetAllContractStatusTypesFindsNoResults() throws Exception {
        when(contractStatusTypeRepository.findAll()).thenReturn(new ArrayList<ContractStatusType>());

        List<ContractStatusType> allContractStatusTypes = classifierService.getAllContractStatusTypes();
        Assert.assertNotNull(allContractStatusTypes);
        Assert.assertEquals(0, allContractStatusTypes.size());
        verify(contractStatusTypeRepository).findAll();
    }

    @Test
    public void testGetAllContractStatusTypesFindsResults() throws Exception {
        ContractStatusType contractStatusType = new ContractStatusType();
        contractStatusType.setId(1L);
        contractStatusType.setName("Name");
        when(contractStatusTypeRepository.findAll()).thenReturn(Arrays.asList(contractStatusType));

        List<ContractStatusType> allContractStatusTypes = classifierService.getAllContractStatusTypes();
        Assert.assertNotNull(allContractStatusTypes);
        Assert.assertEquals(1, allContractStatusTypes.size());
        Assert.assertEquals(contractStatusType, allContractStatusTypes.get(0));
        verify(contractStatusTypeRepository).findAll();
    }

    @Test
    public void testFindByNameHandlesException() throws Exception {
        when(contractStatusTypeRepository.findByName(anyString())).thenThrow(HibernateException.class);

        try {
            classifierService.findContractStatusTypeByName("12");
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find ContractStatusType with name '12'", e.getMessage());

            verify(contractStatusTypeRepository).findByName("12");
            return;
        }

        Assert.fail();
    }

    @Test
    public void testFindByNameDoesNotFindAResult() throws Exception {
        try {
            classifierService.findContractStatusTypeByName("name");
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find ContractStatusType with name 'name'", e.getMessage());

            verify(contractStatusTypeRepository).findByName("name");
            return;
        }

        Assert.fail();
    }

    @Test
    public void testFindByNameFindsResult() throws Exception {
        ContractStatusType contractStatusType= new ContractStatusType();
        contractStatusType.setId(1L);
        contractStatusType.setName("Name");
        when(contractStatusTypeRepository.findByName("Name")).thenReturn(contractStatusType);

        Assert.assertEquals(contractStatusType, classifierService.findContractStatusTypeByName("Name"));
        verify(contractStatusTypeRepository).findByName("Name");
    }

    @Test
    public void testFindByNameNameMustNotBeNull() throws Exception {
        try {
            classifierService.findContractStatusTypeByName(null);
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("ContractStatusType name must not be null!", e.getMessage());
            return;
        }

        Assert.fail();
    }
}