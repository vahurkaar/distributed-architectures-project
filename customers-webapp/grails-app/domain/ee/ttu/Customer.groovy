package ee.ttu

import ee.ttu.classifier.CustomerStatusType
import ee.ttu.classifier.CustomerType
import org.apache.commons.lang.StringUtils

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

    static hasMany = [addresses: CustomerAddress]

    static constraints = {
    }

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
                ", addresses=" + addresses +
                '}';
    }
}
