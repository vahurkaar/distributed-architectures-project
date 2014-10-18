package ee.ttu.converter;

import ee.ttu.model.Contract;
import ee.ttu.util.XMLCalendarUtil;
import ee.ttu.xml.ContractType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Vahur Kaar on 18.10.2014.
 */
@Component
public class ContractTypeConverter implements Converter<Contract, ContractType> {

    @Override
    public ContractType convert(Contract source) {
        ContractType contractType = new ContractType();
        contractType.setId(source.getId());
        contractType.setCustomerId(source.getId());
        contractType.setContractType(source.getContractType() != null ? source.getContractType().getId() : null);
        contractType.setContractStatusType(source.getContractStatusType() != null ? source.getContractStatusType().getId() : null);
        contractType.setContractNumber(source.getContractNumber());
        contractType.setName(source.getName());
        contractType.setDescription(source.getDescription());
        contractType.setValidFrom(XMLCalendarUtil.dateToXmlCalendar(source.getValidFrom()));
        contractType.setValidTo(XMLCalendarUtil.dateToXmlCalendar(source.getValidTo()));
        contractType.setConditions(source.getConditions());
        contractType.setNote(source.getNote());
        contractType.setValueAmount(source.getValueAmount() != null ? source.getValueAmount().doubleValue() : 0.0);

        return contractType;
    }
}
