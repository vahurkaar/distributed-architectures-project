package ee.ttu.converter;

import ee.ttu.model.classifier.ContractStatusType;
import ee.ttu.xml.ContractStatusTypeType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Vahur Kaar on 9.11.2014.
 */
@Component
public class ContractStatusTypeToContractStatusTypeTypeConverter implements Converter<ContractStatusType, ContractStatusTypeType> {

    @Override
    public ContractStatusTypeType convert(ContractStatusType source) {
        ContractStatusTypeType contractStatusTypeType = new ContractStatusTypeType();
        contractStatusTypeType.setId(source.getId());
        contractStatusTypeType.setName(source.getName());

        return contractStatusTypeType;
    }
}
