package ee.ttu.controller;

import ee.ttu.form.ContractForm;
import ee.ttu.model.ContractDeletionResult;
import ee.ttu.model.ContractSavingResult;
import ee.ttu.service.ClassifierService;
import ee.ttu.service.ContractsService;
import ee.ttu.service.CustomersService;
import ee.ttu.util.XMLGregorianCalendarEditor;
import ee.ttu.validator.ContractFormValidator;
import ee.ttu.xml.ContractStatusTypeType;
import ee.ttu.xml.ContractType;
import ee.ttu.xml.ContractTypeType;
import ee.ttu.xml.CustomerType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.soap.client.SoapFaultClientException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

/**
 * Created by Vahur Kaar on 9.11.2014.
 */
@Controller
@RequestMapping(value = "/contract")
public class ContractController {

    @Autowired
    private ClassifierService classifierService;

    @Autowired
    private ContractsService contractsService;

    @Autowired
    private CustomersService customersService;

    @Autowired
    private ContractFormValidator contractFormValidator;

    @ModelAttribute
    public List<ContractTypeType> getContractTypes() {
        return classifierService.getContractTypes();
    }

    @ModelAttribute
    public List<ContractStatusTypeType> getContractStatusTypes() {
        return classifierService.getContractStatusTypes();
    }


    @RequestMapping(value = "/view")
    public ModelAndView viewContract(@ModelAttribute ContractForm contractForm) {
        ModelAndView modelAndView = new ModelAndView("contract/view");
        ContractType contractType = contractsService.findContractById(contractForm.getId());
        populateContractForm(contractForm, contractType);
        modelAndView.addObject("readOnly", true);
        return modelAndView;
    }

    private void populateContractForm(ContractForm contractForm, ContractType contractType) {
        contractForm.setId(contractType.getId());
        contractForm.setConditions(contractType.getConditions());
        contractForm.setContractNumber(contractType.getContractNumber());
        contractForm.setContractStatusType(contractType.getContractStatusType());
        contractForm.setContractType(contractType.getContractType());
        contractForm.setCustomerId(contractType.getCustomerId());
        contractForm.setDescription(contractType.getDescription());
        contractForm.setName(contractType.getName());
        contractForm.setNote(contractType.getNote());
        contractForm.setValidFrom(contractType.getValidFrom());
        contractForm.setValidTo(contractType.getValidTo());
        contractForm.setValueAmount(contractType.getValueAmount());
    }

    @RequestMapping(value = "/new")
    public ModelAndView newContract(@RequestParam Long customerId,
                                    @ModelAttribute ContractForm contractForm) {
        ModelAndView modelAndView = new ModelAndView("contract/view");
        contractForm.setCustomerId(customerId);
        return modelAndView;
    }

    @RequestMapping(value = "/edit")
    public ModelAndView editContract(@ModelAttribute ContractForm contractForm) {
        ModelAndView modelAndView = new ModelAndView("contract/view");
        ContractType contractType = contractsService.findContractById(contractForm.getId());
        populateContractForm(contractForm, contractType);
        return modelAndView;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public String saveContract(@ModelAttribute ContractForm contractForm, Errors errors) {
        contractFormValidator.validate(contractForm, errors);

        if (errors.hasErrors()) {
            return errors.getAllErrors().get(0).getDefaultMessage();
        }

        ContractSavingResult result = saveContractAndRegisterErrors(contractForm);
        if (result.getResult().equals("OK")) {
            customersService.downgradeCustomerIfNeccesary(contractForm.getCustomerId());
            return "OK";
        } else {
            return result.getMessage();
        }
    }

    private ContractSavingResult saveContractAndRegisterErrors(ContractForm contractForm) {
        try {
            return contractsService.saveContract(contractForm);
        } catch (SoapFaultClientException ex) {
            ContractSavingResult result = new ContractSavingResult();
            result.setResult("NOT_OK");
            result.setMessage(ex.getMessage());
            return result;
        }
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteContract(@ModelAttribute ContractForm contractForm) {
        ContractDeletionResult result = contractsService.deleteContract(contractForm.getId());

        if (result.getResult().equals("OK")) {
            customersService.downgradeCustomerIfNeccesary(contractForm.getCustomerId());
            return new ModelAndView("redirect:/customer/view?id=" + contractForm.getCustomerId());
        }

        return null;
    }

}
