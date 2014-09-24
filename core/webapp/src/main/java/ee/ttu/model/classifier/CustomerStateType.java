package ee.ttu.model.classifier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Vahur Kaar on 21.09.2014.
 */
@Entity
@Table(name = "cst_state_type", schema = "public")
public class CustomerStateType {

    @Id
    @Column(name = "cst_state_type")
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
