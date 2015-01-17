package ee.ttu

import grails.plugin.springsecurity.SpringSecurityService
import org.apache.commons.lang.StringUtils
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder

import static org.springframework.http.HttpStatus.*

@Secured('IS_AUTHENTICATED_FULLY')
class CustomerController {

    CustomerService customerService
    ClassifierService classifierService
    SpringSecurityService springSecurityService
    CustomerValidationService customerValidationService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def afterInterceptor = { model ->
        model.user = springSecurityService.principal
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
    }

    def search() {
        def customers
        String searchName = params.'query-name'
        String searchIdentiyCode = params.'query-identityCode'

        if (StringUtils.isNotEmpty(searchIdentiyCode)) {
            customers = customerService.findByIdentityCode(searchIdentiyCode)
        } else {
            customers = customerService.findByName(searchName)
        }

        [customers: customers]
    }

    def edit() {
        Long customerId = Long.valueOf(params.customerId)
        Customer customer = customerService.findById(customerId)
        [
                customer: customer,
                command: new CustomerCommand(addresses: customer.addresses),
                customerTypes: classifierService.getAllCustomerTypes(),
                customerStatusTypes: classifierService.getAllCustomerStatusTypes()
        ]
    }

    def create() {
        [
                customerTypes: classifierService.getAllCustomerTypes(),
                customerStatusTypes: classifierService.getAllCustomerStatusTypes()
        ]
    }

    def save(CustomerCommand command) {
        Customer customer = new Customer(params)
        customer.setAddresses(command.addresses)
        customerValidationService.validateCustomer(customer)
        if (customer.hasErrors()) {
            render view:'create', model: [
                    customer: customer,
                    command: command,
                    customerTypes: classifierService.getAllCustomerTypes(),
                    customerStatusTypes: classifierService.getAllCustomerStatusTypes()
            ]
            return
        }

        customerService.saveCustomer(customer);

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'customer.label', default: 'Customer'), customer.identityCode])
                redirect action: 'index'
            }
            '*' { respond customer, [status: CREATED] }
        }
    }

    def update(CustomerCommand command) {
        Customer customer = new Customer(params)
        customer.setAddresses(command.addresses)
        customerValidationService.validateCustomer(customer, true)
        log.error(customer.hasErrors())
        if (customer.hasErrors()) {
            render view:'edit', model: [
                    customer: customer,
                    command: command,
                    customerTypes: classifierService.getAllCustomerTypes(),
                    customerStatusTypes: classifierService.getAllCustomerStatusTypes()
            ]
            return
        }

        customerService.saveCustomer(customer);

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Customer.label', default: 'Customer'), customer.identityCode])
                redirect action: 'edit', params: [customerId: customer.customerId]
            }
            '*'{ respond customer, [status: OK] }
        }
    }

    def delete() {
        Long customerId = Long.parseLong(params.customerId)

        customerService.deleteCustomer(customerId)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Customer.label', default: 'Customer'), params.identityCode])
                render view: 'delete'
            }
            '*'{ respond customerId, status: OK }
        }
    }

}
