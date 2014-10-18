package ee.ttu.repository;

import ee.ttu.model.Contract;
import ee.ttu.repository.classifier.ContractStatusTypeRepository;
import ee.ttu.repository.classifier.ContractTypeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.sql.DataSource;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class ContractRepositoryTest extends RepositoryTestSupport {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractStatusTypeRepository contractStatusTypeRepository;

    @Autowired
    private ContractTypeRepository contractTypeRepository;

    @Before
    public void setUp() throws Exception {
        executeSqlScript("sql/contracts/contract_repository_test.sql", false);
    }

    @Test
    public void testSaveNewContractToUserWhoDoesNotExist() throws Exception {
        try {
            Contract contract = prepareContract(22L);
            contractRepository.save(contract);
        } catch (DataAccessException e) {
            return;
        }

        Assert.fail("Should have thrown an exception!");
    }

    @Test
    public void testSaveNewContractToUserSuccessfully() throws Exception {
        Contract contract = prepareContract(1L);

        contractRepository.save(contract);

        Contract savedContract = contractRepository.findOne(2L);
        contract.setId(2L);
        assertContract(contract, savedContract);
    }

    @Test
    public void testUpdateContractSuccessfully() throws Exception {
        Contract contract = contractRepository.findOne(1L);
        contract.setValueAmount(new BigDecimal("5555"));

        contractRepository.save(contract);

        Contract savedContract = contractRepository.findOne(1L);
        assertContract(contract, savedContract);
    }

    private Contract prepareContract(Long customerId) throws ParseException {
        Contract contract = new Contract();
        contract.setCustomerId(customerId);
        contract.setContractType(contractTypeRepository.findOne(1L));
        contract.setContractStatusType(contractStatusTypeRepository.findOne(1L));
        contract.setContractNumber("123");
        contract.setValidFrom(getTimestamp("12.12.2013 00:15"));
        contract.setValidTo(getTimestamp("22.12.2014 18:00"));
        contract.setValueAmount(new BigDecimal("2222"));

        return contract;
    }

    private Timestamp getTimestamp(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return new Timestamp(simpleDateFormat.parse(time).getTime());
    }

    private static void assertContract(Contract expected, Contract actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getCustomerId(), actual.getCustomerId());
        Assert.assertEquals(expected.getContractType().getId(), actual.getContractType().getId());
        Assert.assertEquals(expected.getContractStatusType().getId(), actual.getContractStatusType().getId());
        Assert.assertEquals(expected.getContractNumber(), actual.getContractNumber());
        Assert.assertEquals(expected.getValidFrom(), actual.getValidFrom());
        Assert.assertEquals(expected.getValidTo(), actual.getValidTo());
        Assert.assertEquals(expected.getValueAmount(), actual.getValueAmount());
    }
}