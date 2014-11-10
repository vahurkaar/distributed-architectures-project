package ee.ttu.controller;

import ee.ttu.form.CustomerForm;
import ee.ttu.service.ClassifierService;
import ee.ttu.service.CustomersService;
import ee.ttu.xml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Vahur Kaar on 9.11.2014.
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomersService customersService;

    @Autowired
    private ClassifierService classifierService;

    @Autowired
    private Environment environment;

    @RequestMapping(value = "/upgrade", method = RequestMethod.POST)
    public ModelAndView upgradeUser(@RequestParam Long id) {
        customersService.upgradeCustomer(id);
        return new ModelAndView("redirect:/customer/view?id=" + id);
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view(@RequestParam Long id,
                             @ModelAttribute CustomerForm customerForm) {
        ModelAndView modelAndView = new ModelAndView();
        populateCustomerForm(customerForm, customersService.getCustomerById(id));
        modelAndView.addObject("customerTypes", classifierService.getCustomerTypes());
        modelAndView.addObject("suggestStatusUpgrade", customerIsNotSuperClient(customerForm) && isContractSumOverThreshold(customerForm));
        return modelAndView;
    }

    private boolean isContractSumOverThreshold(CustomerForm customerForm) {
        Double sum = 0.0;
        Long activeContractStatusTypeId = getContractStatusTypeByName(environment.getProperty("customer.activeContractStatusType"));

        for (ContractType contract : customerForm.getContracts().getContract()) {
            if (contract.getContractStatusType().equals(activeContractStatusTypeId)) {
                sum += contract.getValueAmount();
            }
        }

        return sum > Double.valueOf(environment.getProperty("customer.goodCustomerThreshold"));
    }

    private boolean customerIsNotSuperClient(CustomerForm customerForm) {
        Long goodCustomerTypeId = getCustomerTypeId(environment.getProperty("customer.goodCustomerType"));

        if (!goodCustomerTypeId.equals(customerForm.getCustomerType())) {
            return true;
        }

        return false;
    }

    private Long getCustomerTypeId(String name) {
        for (CustomerTypeType customerType : classifierService.getCustomerTypes()) {
            if (customerType.getName().equals(name)) {
                return customerType.getId();
            }
        }

        return null;
    }

    private Long getContractStatusTypeByName(String name) {
        for (ContractStatusTypeType contractStatusTypeType : classifierService.getContractStatusTypes()) {
            if (contractStatusTypeType.getName().equals(name)) {
                return contractStatusTypeType.getId();
            }
        }

        return null;
    }

    private void populateCustomerForm(CustomerForm customerForm, CustomerType customer) {
        customerForm.setId(customer.getId());
        customerForm.setFirstname(customer.getFirstname());
        customerForm.setLastname(customer.getLastname());
        customerForm.setIdentityCode(customer.getIdentityCode());
        customerForm.setBirthDate(customer.getBirthDate());
        customerForm.setCustomerType(customer.getCustomerType());
        customerForm.setCustomerTypeName(getCustomerTypeName(customer.getCustomerType()));
        customerForm.setCustomerStatusType(customer.getCustomerStatusType());
        customerForm.setCustomerStatusTypeName(getCustomerStatusTypeName(customer.getCustomerStatusType()));
        customerForm.setModifier(customer.getModifier());
        customerForm.setNote(customer.getNote());
        customerForm.setAddresses(customer.getAddresses());
        customerForm.setContracts(customer.getContracts());
    }

    private String getCustomerTypeName(Long customerTypeId) {
        if (customerTypeId != null) {
            for (CustomerTypeType customerType : classifierService.getCustomerTypes()) {
                if (customerType.getId() == customerTypeId) {
                    return customerType.getName();
                }
            }
        }

        return null;
    }

    private String getCustomerStatusTypeName(Long customerStatusTypeId) {
        if (customerStatusTypeId != null) {
            for (CustomerStateTypeType customerStateType : classifierService.getCustomerStatusTypes()) {
                if (customerStateType.getId() == customerStatusTypeId) {
                    return customerStateType.getName();
                }
            }
        }

        return null;
    }


}
