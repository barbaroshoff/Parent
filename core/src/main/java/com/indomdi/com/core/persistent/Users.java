package com.indomdi.com.core.persistent;

import com.indomdi.com.core.config.GuiRoles;
import com.indomdi.com.core.persistent.common.AuditedEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "USERS", indexes = { @Index(columnList = "username", unique = true) },
        uniqueConstraints={@UniqueConstraint(columnNames={"username","email"})}
)
public class Users extends AuditedEntity {


    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Authorities> authorities;


    @Column(name="role", nullable = true,length = 25)
    private GuiRoles guiRoles;
    @Column(name = "firstName", nullable = true, length = 25)
    private String firstName;
    @Column(name = "lastName", nullable = true, length = 40)
    private String lastName;
    @Column(name = "email", nullable = true, length = 50)
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

    public Users() {
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
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
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the enabled
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the authorities
     */
    public List<Authorities> getAuthorities() {
        return authorities;
    }

    /**
     * @param authorities
     *            the authorities to set
     */
    public void setAuthorities(List<Authorities> authorities) {
        this.authorities = authorities;
    }


    public GuiRoles getGuiRoles() {
        return guiRoles;
    }

    public void setGuiRoles(GuiRoles guiRoles) {
        this.guiRoles = guiRoles;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *            the city to set
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
     * @param lastName
     *            the lastName to set
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
     * @param email
     *            the email to set
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
     * @param country
     *            the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the securityAnswer
     */
    public String getSecurityAnswer() {
        return securityAnswer;
    }

    /**
     * @param securityAnswer
     *            the securityAnswer to set
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
     * @param organization
     *            the country to set
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * @return the securityQuestion
     */
    public String getSecurityQuestion() {
        return securityQuestion;
    }

    /**
     * @param securityQuestion
     *            the securityQuestion to set
     */
    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(authorities, country, email, enabled, firstName, lastName, city, password, username, securityAnswer, organization, securityQuestion);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Users)) return false;
        final Users other = (Users) obj;
        return Objects.equals(authorities, other.authorities) && Objects.equals(country, other.country) && Objects.equals(email, other.email)
                && Objects.equals(enabled, other.enabled) && Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
                && Objects.equals(city, other.city) && Objects.equals(password, other.password) && Objects.equals(username, other.username)
                && Objects.equals(securityAnswer, other.securityAnswer) && Objects.equals(organization, other.organization)
                && Objects.equals(securityQuestion, other.securityQuestion);
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                ", guiRoles=" + guiRoles +
                ", firstName='" + firstName + '\'' +
                ", city='" + city + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", securityAnswer='" + securityAnswer + '\'' +
                ", organization='" + organization + '\'' +
                ", securityQuestion='" + securityQuestion + '\'' +
                '}';
    }
}
