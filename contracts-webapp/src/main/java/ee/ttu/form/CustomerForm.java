package ee.ttu.form;

import ee.ttu.xml.CustomerType;

/**
 * Created by Vahur Kaar on 3.11.2014.
 */
public class CustomerForm extends CustomerType {

    private String customerTypeName;

    private String customerStatusTypeName;

    public String getFullname() {
        return String.format("%s %s", getFirstname(), getLastname()).trim();
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }

    public String getCustomerStatusTypeName() {
        return customerStatusTypeName;
    }

    public void setCustomerStatusTypeName(String customerStatusTypeName) {
        this.customerStatusTypeName = customerStatusTypeName;
    }
}
