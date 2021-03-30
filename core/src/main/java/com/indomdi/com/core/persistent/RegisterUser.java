package com.indomdi.com.core.persistent;


import com.indomdi.com.core.config.GuiRoles;
import com.indomdi.com.core.persistent.common.AuditedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "REGISTER_USERS", indexes = {@Index(columnList = "username", unique = true)})
public class RegisterUser extends AuditedEntity {

    private static final long serialVersionUID = -7923360064479999122L;

    @Column(name = "guiRole", nullable = true, length = 25)
    private GuiRoles role;
    @Column(name = "firstName", nullable = true, length = 25)
    private String firstName;
    @Column(name = "lastName", nullable = true, length = 40)
    private String lastName;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "country", nullable = true, length = 30)
    private String country;
    @Column(name = "city", nullable = true, length = 40)
    private String city;
    @Column(name = "securityAnswer", nullable = true, length = 80)
    private String securityAnswer;
    @Column(name = "organization", nullable = true, length = 80)
    private String organization;
    @Column(name = "securityQuestion", nullable = true, length = 80)
    private String securityQuestion;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "secureCode", nullable = false, length = 65)
    private String secureCode;

    /**
     * @return the securityQuestion
     */
    public String getSecurityQuestion() {
        return securityQuestion;
    }

    /**
     * @param securityQuestion the securityQuestion to set
     */
    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the city
     */
    public String getCity() { return city; }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the secureCode
     */
    public String getSecureCode() {
        return secureCode;
    }

    /**
     * @param secureCode the secureCode to set
     */
    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
    }

    /**
     * @return the securityAnswer
     */
    public String getSecurityAnswer() {
        return securityAnswer;
    }

    /**
     * @param securityAnswer the secureCode to set
     */
    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    /**
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public GuiRoles getRole() {
        return role;
    }

    public void setRole(GuiRoles role) {
        this.role = role;
    }
}
