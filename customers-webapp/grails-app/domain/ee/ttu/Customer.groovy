package ee.ttu

import ee.ttu.classifier.CustomerStatusType
import ee.ttu.classifier.CustomerType
import org.apache.commons.lang.StringUtils

import grails.validation.Validateable
import org.apache.commons.collections.ListUtils
import org.apache.commons.collections.Factory

@Validateable
class Customer {

    Long customerId
    String firstname
    String lastname
    String identityCode
    String note
    Date birthDate
    CustomerType customerType
    CustomerStatusType customerStatusType
    Long modifier
    List<CustomerAddress> addresses = ListUtils.lazyList([], {new CustomerAddress()} as Factory)

    String getFullName() {
        return (StringUtils.defaultString(firstname, "") + " " +
                StringUtils.defaultString(lastname, "")).trim()
    }


    @Override
    public String toString() {
        return "Customer{" +
                "version=" + version +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", identityCode='" + identityCode + '\'' +
                ", note='" + note + '\'' +
                ", birthDate=" + birthDate +
                ", customerType=" + customerType +
                ", customerStateType=" + customerStatusType +
                ", modifier=" + modifier +
                '}';
    }
}
