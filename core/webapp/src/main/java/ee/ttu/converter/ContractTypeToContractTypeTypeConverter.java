package ee.ttu.converter;

import ee.ttu.model.classifier.ContractType;
import ee.ttu.xml.ContractTypeType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Vahur Kaar on 9.11.2014.
 */
@Component
public class ContractTypeToContractTypeTypeConverter implements Converter<ContractType, ContractTypeType> {

    @Override
    public ContractTypeType convert(ContractType source) {
        ContractTypeType contractTypeType = new ContractTypeType();
        contractTypeType.setId(source.getId());
        contractTypeType.setName(source.getName());

        return contractTypeType;
    }
}
