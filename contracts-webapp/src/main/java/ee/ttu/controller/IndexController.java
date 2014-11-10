package ee.ttu.controller;

import ee.ttu.form.ContractsSearchForm;
import ee.ttu.service.ClassifierService;
import ee.ttu.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Vahur Kaar on 1.11.2014.
 */
@Controller
public class IndexController {

    @Autowired
    private CustomersService customersService;

    @Autowired
    private ClassifierService classifierService;

    @RequestMapping(value = "/")
    public ModelAndView index(@ModelAttribute ContractsSearchForm contractsSearchForm) {
        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }

    @RequestMapping(value = "/searchContracts", method = RequestMethod.POST)
    public ModelAndView searchContracts(@ModelAttribute ContractsSearchForm contractsSearchForm) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("customers", customersService.getCustomers(contractsSearchForm));
        modelAndView.addObject("customerTypes", classifierService.getCustomerTypes());
        return modelAndView;
    }

}
