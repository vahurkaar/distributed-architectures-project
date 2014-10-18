package ee.ttu.converter;

import ee.ttu.model.Contract;
import ee.ttu.repository.classifier.ContractTypeRepository;
import ee.ttu.service.ClassifierService;
import ee.ttu.util.XMLCalendarUtil;
import ee.ttu.xml.ContractType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Vahur Kaar on 18.10.2014.
 */
@Component
public class ContractConverter implements Converter<ContractType, Contract> {

    @Autowired
    private ClassifierService classifierService;

    @Override
    public Contract convert(ContractType source) {
        Contract contract = new Contract();
        contract.setId(source.getId());
        contract.setCustomerId(source.getId());
        contract.setContractNumber(source.getContractNumber());
        contract.setName(source.getName());
        contract.setDescription(source.getDescription());
        contract.setConditions(source.getConditions());
        contract.setNote(source.getNote());
        contract.setValueAmount(new BigDecimal(source.getValueAmount()));

        if (source.getContractType() != null) {
            contract.setContractType(classifierService.getContractType(source.getContractType()));
        }
        if (source.getContractStatusType() != null) {
            contract.setContractStatusType(classifierService.getContractStatusType(source.getContractStatusType()));
        }
        if (source.getValidFrom() != null) {
            contract.setValidFrom(new Timestamp(XMLCalendarUtil.xmlCalendarToDate(source.getValidFrom()).getTime()));
        }
        if (source.getValidTo() != null) {
            contract.setValidTo(new Timestamp(XMLCalendarUtil.xmlCalendarToDate(source.getValidTo()).getTime()));
        }

        return contract;
    }
}
