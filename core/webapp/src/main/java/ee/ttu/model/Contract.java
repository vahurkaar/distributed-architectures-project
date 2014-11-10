package ee.ttu.model;

import ee.ttu.model.classifier.ContractStatusType;
import ee.ttu.model.classifier.ContractType;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Vahur Kaar on 18.10.2014.
 */
@Entity
@Table(name = "contract", schema = "public")
public class Contract extends PersistentObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "contract_seq")
    @SequenceGenerator(name = "contract_seq", sequenceName = "s_contract", schema = "public")
    @Column(name = "contract")
    private Long id;

    @Column(name = "customer")
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "contract_status_type")
    private ContractStatusType contractStatusType;

    @ManyToOne
    @JoinColumn(name = "contract_type")
    private ContractType contractType;

    @Column(name = "cnt_number")
    private String contractNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "valid_from")
    private Timestamp validFrom;

    @Column(name = "valid_to")
    private Timestamp validTo;

    @Column(name = "conditions")
    private String conditions;

    @Column(name = "note")
    private String note;

    @Column(name = "value_amount")
    private BigDecimal valueAmount;

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

    public ContractStatusType getContractStatusType() {
        return contractStatusType;
    }

    public void setContractStatusType(ContractStatusType contractStatusType) {
        this.contractStatusType = contractStatusType;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Timestamp validFrom) {
        this.validFrom = validFrom;
    }

    public Timestamp getValidTo() {
        return validTo;
    }

    public void setValidTo(Timestamp validTo) {
        this.validTo = validTo;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getValueAmount() {
        return valueAmount;
    }

    public void setValueAmount(BigDecimal valueAmount) {
        this.valueAmount = valueAmount;
    }
}
