package ee.ttu.validator;

import ee.ttu.form.ContractForm;
import ee.ttu.util.XMLCalendarUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Vahur Kaar on 10.01.2015.
 */
@Component
public class ContractFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ContractForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ContractForm contractForm = (ContractForm) target;

        if (StringUtils.isEmpty(contractForm.getContractNumber())) {
            errors.reject("isNull.contractNumber", "Contract number is mandatory!");
        }

        if (contractForm.getValidTo() != null && contractForm.getValidFrom() != null) {
            if (!XMLCalendarUtil.xmlCalendarToDate(contractForm.getValidTo()).after(
                    XMLCalendarUtil.xmlCalendarToDate(contractForm.getValidFrom()))) {
                errors.reject("dateDiff.validTo", "ValidTo has to be after ValidFrom!");
            }
        }

        if (contractForm.getValueAmount() < 0.0) {
            errors.reject("negative.valueAmount", "Contract amount must not be negative!");
        }
    }
}
