package acnbatch.job.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author fagerholm
 */
@Entity
@Table(name ="EMPLOYEE")
public class EmployeeOutputRecord implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;

    private String personalNumber;

    private String name;

    private String email;

    private String phone;

    private String enterpriseId;

    public EmployeeOutputRecord() {
    }

    public EmployeeOutputRecord(EmployeeInputRecord record) {
        personalNumber = record.getPersonalNumber();
        name = record.getName();
        email = record.getEmail();
        phone = record.getPhone();
        enterpriseId = record.getEnterpriseId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }    
}
