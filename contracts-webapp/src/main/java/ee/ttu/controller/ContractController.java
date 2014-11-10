package ee.ttu.controller;

import ee.ttu.form.ContractForm;
import ee.ttu.model.ContractDeletionResult;
import ee.ttu.model.ContractSavingResult;
import ee.ttu.service.ClassifierService;
import ee.ttu.service.ContractsService;
import ee.ttu.service.CustomersService;
import ee.ttu.util.XMLGregorianCalendarEditor;
import ee.ttu.xml.ContractStatusTypeType;
import ee.ttu.xml.ContractType;
import ee.ttu.xml.ContractTypeType;
import ee.ttu.xml.CustomerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView saveContract(@ModelAttribute ContractForm contractForm) {
        ContractSavingResult result = contractsService.saveContract(contractForm);
        if (result.getResult().equals("OK")) {
            customersService.downgradeCustomerIfNeccesary(contractForm.getCustomerId());
            return new ModelAndView("redirect:/contract/view?id=" + result.getContractType().getId());
        } else {
            ModelAndView modelAndView = new ModelAndView("contract/view");
            modelAndView.addObject("error", result.getMessage());
            return modelAndView;
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
