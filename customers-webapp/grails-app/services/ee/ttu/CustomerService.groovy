package ee.ttu

import grails.transaction.Transactional
import org.apache.commons.lang.StringUtils
import org.apache.commons.lang.time.DateUtils

@Transactional
class CustomerService {

    def soapClient

    def classifierService

    def saveCustomer(Customer customerInstance) {
        def response = soapClient.send {
            body {
                SaveCustomerRequest('xmlns':'http://www.ttu.ee/hajusarhitektuurid') {
                    customer {
                        id(customerInstance.customerId)
                        firstname(customerInstance.firstname)
                        lastname(customerInstance.lastname)
                        identityCode(customerInstance.identityCode)
                        note(customerInstance.note)
                        birthDate(customerInstance.birthDate?.format("yyyy-MM-dd"))
                        customerStatusType(customerInstance?.customerStatusType.customerStatusTypeId)
                        customerType(customerInstance?.customerType.customerTypeId)
                        addresses()
                        modifier(1)
                    }
                }
            }
        }
    }

    def deleteCustomer(Long customerId) {
        def response = soapClient.send {
            body {
                DeleteCustomerRequest('xmlns':'http://www.ttu.ee/hajusarhitektuurid') {
                    id(customerId)
                }
            }
        }
    }

    def findByName(String customerName) {
        List<Customer> customers = new ArrayList<>()
        def response = soapClient.send {
            body {
                GetCustomersRequest('xmlns':'http://www.ttu.ee/hajusarhitektuurid') {
                    name(customerName)
                }
            }
        }

        response.GetCustomersResponse.customer.each { node ->
            customers.add(parseCustomer(node))
        }

        return customers
    }

    def findByIdentityCode(String customerIdentityCode) {
        def response = soapClient.send {
            body {
                GetCustomersRequest('xmlns':'http://www.ttu.ee/hajusarhitektuurid') {
                    identityCode(customerIdentityCode)
                }
            }
        }

        return parseCustomer(response.GetCustomersResponse.customer)
    }

    def findById(Long customerId) {
        def response = soapClient.send {
            body {
                GetCustomersRequest('xmlns':'http://www.ttu.ee/hajusarhitektuurid') {
                    id(customerId)
                }
            }
        }

        return parseCustomer(response.GetCustomersResponse.customer)
    }

    private def parseCustomer(customerNode) {
        def customer = new Customer(
                customerId: customerNode.id?.text(),
                identityCode: customerNode.identityCode?.text(),
                firstname: customerNode.firstname?.text(),
                lastname: customerNode.lastname?.text(),
                note: customerNode.note?.text(),
                birthDate: StringUtils.isNotBlank(customerNode.birthDate.text()) ?
                        Date.parse("yyyy-MM-dd", customerNode.birthDate.text()) : null,
                customerType: StringUtils.isNotBlank(customerNode.customerType.text()) ?
                        classifierService.getCustomerTypeById(Long.parseLong(customerNode.customerType.text())) : null,
                customerStatusType: StringUtils.isNotBlank(customerNode.customerStatusType.text()) ?
                        classifierService.getCustomerStatusTypeById(Long.parseLong(customerNode.customerStatusType.text())) : null,
                modifier: customerNode.modifier?.text())

        return customer
    }

}
