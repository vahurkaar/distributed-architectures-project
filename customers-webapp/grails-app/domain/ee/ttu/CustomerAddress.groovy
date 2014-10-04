package ee.ttu

import ee.ttu.classifier.Country

class CustomerAddress {

    Long addressId
    String zip
    String house
    String address
    String county
    String parish
    String townCity
    Country country
    String phone
    String sms
    String mobile
    String email
    String note

    static constraints = {
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CustomerAddress{");
        sb.append("addressId=").append(addressId);
        sb.append(", zip='").append(zip).append('\'');
        sb.append(", house='").append(house).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", county='").append(county).append('\'');
        sb.append(", parish='").append(parish).append('\'');
        sb.append(", townCity='").append(townCity).append('\'');
        sb.append(", country=").append(country);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", sms='").append(sms).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", note='").append(note).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
