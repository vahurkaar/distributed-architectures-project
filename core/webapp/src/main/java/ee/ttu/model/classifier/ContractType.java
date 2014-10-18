package ee.ttu.model.classifier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Vahur Kaar on 18.10.2014.
 */
@Entity
@Table(name = "contract_type", schema = "public")
public class ContractType {

    @Id
    @Column(name = "contract_type")
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
