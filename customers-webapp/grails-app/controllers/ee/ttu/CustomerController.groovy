package ee.ttu

import org.apache.commons.lang.StringUtils
import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured('IS_AUTHENTICATED_FULLY')
class CustomerController {

    CustomerService customerService
    ClassifierService classifierService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

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
        [
                customer: customerService.findById(customerId),
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

    def save() {
        Customer customer = new Customer(params)

        if (customer.hasErrors()) {
            respond customer.errors, view:'create'
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

    def update() {
        Customer customer = new Customer(params)

        if (customer.hasErrors()) {
            respond customer.errors, view:'edit'
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
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

}
