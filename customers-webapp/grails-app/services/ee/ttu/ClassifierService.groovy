package ee.ttu

import ee.ttu.classifier.Country
import ee.ttu.classifier.CustomerStatusType
import ee.ttu.classifier.CustomerType
import grails.transaction.Transactional

@Transactional
class ClassifierService {

    def soapClient

    def getAllCustomerStatusTypes() {
        List<CustomerStatusType> customerStatusTypes = new ArrayList<>()
        def response = soapClient.send {
            body {
                GetCustomerStateTypesRequest('xmlns':'http://www.ttu.ee/hajusarhitektuurid')
            }
        }

        response.GetCustomerStateTypesResponse.customerStateType.each { node ->
            customerStatusTypes.add(new CustomerStatusType(
                    customerStatusTypeId: node.id.text(),
                    name: node.name.text()))
        }

        return customerStatusTypes
    }

    def getCustomerTypeById(Long customerTypeId) {
        CustomerType customerType = null
        getAllCustomerTypes().each { type ->
            if (type.getCustomerTypeId().equals(customerTypeId)) {
                customerType = type
            }
        }

        return customerType
    }

    def getAllCustomerTypes() {
        List<CustomerType> customerTypes = new ArrayList<>()
        def response = soapClient.send {
            body {
                GetCustomerTypesRequest('xmlns':'http://www.ttu.ee/hajusarhitektuurid')
            }
        }

        response.GetCustomerTypesResponse.customerType.each { node ->
            customerTypes.add(new CustomerType(
                    customerTypeId: node.id.text(),
                    name: node.name.text()))
        }

        return customerTypes
    }

    def getCustomerStatusTypeById(Long customerStatusTypeId) {
        CustomerStatusType customerStatusType = null
        getAllCustomerStatusTypes().each { type ->
            if (type.getCustomerStatusTypeId().equals(customerStatusTypeId)) {
                customerStatusType = type
            }
        }

        return customerStatusType
    }

    def getAllCountries() {
        List<Country> countries = new ArrayList<>()
        def response = soapClient.send {
            body {
                GetCountriesRequest('xmlns':'http://www.ttu.ee/hajusarhitektuurid')
            }
        }

        response.GetCountriesResponse.customerType.each { node ->
            countries.add(new Country(
                    countryId: node.id.text(),
                    name: node.name.text()))
        }

        return countries
    }

    def getCountryById(Long countryId) {
        Country countryObject = null
        getAllCountries().each { country ->
            if (country.getCountryId().equals(countryId)) {
                countryObject = country
            }
        }

        return countryObject
    }
}
