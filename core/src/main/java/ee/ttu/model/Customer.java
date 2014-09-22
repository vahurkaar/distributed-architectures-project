package ee.ttu.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vahur Kaar on 20.09.2014.
 */
@Entity
@Table(name = "customer", schema = "public")
public class Customer extends PersistentObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_id", schema = "public")
    @Column(name = "customer")
    private Long id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "identity_code")
    private String identityCode;

    @Column(name = "note")
    private String note;

    @Column(name = "birth_date")
    private Date birthDate;
    
    @ManyToOne
    @JoinColumn(name = "cst_state_type")
    private CustomerStateType customerStateType;

    @ManyToOne
    @JoinColumn(name = "cst_type")
    private CustomerType customerType;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer")
    private List<CustomerAddress> addresses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public CustomerStateType getCustomerStateType() {
        return customerStateType;
    }

    public void setCustomerStateType(CustomerStateType customerStateType) {
        this.customerStateType = customerStateType;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public List<CustomerAddress> getAddresses() {
        if (addresses == null) {
            addresses = new ArrayList<>();
        }
        return addresses;
    }

    public void setAddresses(List<CustomerAddress> addresses) {
        this.addresses = addresses;
    }
}
