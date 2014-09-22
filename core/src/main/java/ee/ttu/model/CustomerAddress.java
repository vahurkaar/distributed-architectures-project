package ee.ttu.model;

import javax.persistence.*;

/**
 * Created by Vahur Kaar on 22.09.2014.
 */
@Entity
@Table(name = "cst_address", schema = "public")
public class CustomerAddress extends PersistentObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "cst_address_seq")
    @SequenceGenerator(name = "cst_address_seq", sequenceName = "s_cst_address", schema = "public")
    @Column(name = "cst_address")
    private Long id;

    @Column(name = "customer")
    private Long customerId;

    @Column(name = "zip")
    private String zip;

    @Column(name = "house")
    private String house;

    @Column(name = "address")
    private String address;

    @Column(name = "county")
    private String county;

    @Column(name = "parish")
    private String parish;

    @Column(name = "town_county")
    private String townCounty;

    @Column(name = "address_type")
    private Long addressType;

    @Column(name = "phone")
    private String phone;

    @Column(name = "sms")
    private String sms;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "country", referencedColumnName = "country")
    private Country country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getParish() {
        return parish;
    }

    public void setParish(String parish) {
        this.parish = parish;
    }

    public String getTownCounty() {
        return townCounty;
    }

    public void setTownCounty(String townCounty) {
        this.townCounty = townCounty;
    }

    public Long getAddressType() {
        return addressType;
    }

    public void setAddressType(Long addressType) {
        this.addressType = addressType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
