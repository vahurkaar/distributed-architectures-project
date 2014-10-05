package ee.ttu.model;

import javax.persistence.*;

/**
 * Created by Vahur Kaar on 5.10.2014.
 */
@Entity
@Table(name = "emp_user", schema = "public")
public class EmployeeUser extends PersistentObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "emp_user_seq")
    @SequenceGenerator(name = "emp_user_seq", sequenceName = "s_emp_user", schema = "public")
    @Column(name = "emp_user")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "passw")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
