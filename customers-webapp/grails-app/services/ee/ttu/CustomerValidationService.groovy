package ee.ttu

import org.apache.commons.lang.StringUtils
import org.apache.commons.lang.math.NumberUtils
import org.apache.commons.lang.time.DateUtils

class CustomerValidationService {

    def customerService

    def validateCustomer(Customer customer) {
        validateCustomer(customer, false)
    }

    def validateCustomer(Customer customer, boolean existingCustomer) {
        validateEmptyFields(customer)

        if (!customer.hasErrors()) {
            validateIdentityCode(customer, existingCustomer)
            validateBirthDate(customer)
        }
    }

    def validateEmptyFields(Customer customer) {
        if (StringUtils.isEmpty(customer.identityCode)) {
            customer.errors.rejectValue('identityCode', 'default.blank.message.identityCode')
        }

        if (StringUtils.isEmpty(customer.firstname)) {
            customer.errors.rejectValue('firstname', 'default.blank.message.firstname')
        }

        if (StringUtils.isEmpty(customer.lastname)) {
            customer.errors.rejectValue('lastname', 'default.blank.message.lastname')
        }

        if (customer.birthDate == null) {
            customer.errors.rejectValue('birthDate', 'default.blank.message.birthDate')
        }
    }

    def validateIdentityCode(Customer customer, boolean existingCustomer) {
        if (!NumberUtils.isNumber(customer.identityCode)) {
            customer.errors.rejectValue('identityCode', 'default.notNumber.message.identityCode')
        }

        if (!customer.hasErrors() && customer.identityCode.length() != 11) {
            customer.errors.rejectValue('identityCode', 'default.length.message.identityCode')
        }

        if (!existingCustomer && !customer.hasErrors()) {
            validateIdentityCodeUniqueness(customer)
        }
    }

    def validateIdentityCodeUniqueness(Customer customer) {
        Customer existingCustomer = customerService.findByIdentityCode(customer.identityCode)
        if (existingCustomer != null) {
            customer.errors.rejectValue('identityCode', 'default.unique.message.identityCode')
        }
    }

    def validateBirthDate(Customer customer) {
        Date birthDate = customer.birthDate
        Date currentDate = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH)

        if (!birthDate.before(currentDate)) {
            customer.errors.rejectValue('birthDate', 'default.past.message.birthDate')
        }
    }
}
