package acnbatch.job.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author fagerholm
 */
@Entity
@Table(name ="employee")
@XmlRootElement
public class EmployeeOutputRecord implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PERSONAL_NUMBER")
    private String personalNumber;

    private String name;

    private String email;

    private String phone;

    @Column(name = "ENTERPRISE_ID")
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
