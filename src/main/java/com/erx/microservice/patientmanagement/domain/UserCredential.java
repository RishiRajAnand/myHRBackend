package com.erx.microservice.patientmanagement.domain;

/*
* created by Latha on 28-11-2017
* */

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "user_credential")
public class UserCredential extends BaseModel {

    /* @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;*/
    @Column(name = "account_expired", nullable = false)
    private boolean accountExpired;

    @Column(name = "account_locked", nullable = false)
    private boolean accountLocked;

    @Column(name = "credentials_expired", nullable = false)
    private boolean credentialsExpired;

    @Column(name = "account_enabled")
    private boolean enabled;

    @Column(name = "first_name", nullable = false, length = 50)
    @NotBlank(message = "{first.name.required}")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @NotBlank(message = "{last.name.required}")
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    private String email;

    @Column(name = "phone_number")
    @NotBlank(message = "{phone.required}")
    private String phoneNumber;

    @Column(nullable = false)
    @XmlTransient
    @JsonIgnore
    private String password;

    @Transient
    @XmlTransient
    @JsonIgnore
    private String confirmPassword;

    // private Set<NewRole> roles = new HashSet<NewRole>();

    /**
     * Create a new instance and set the username.
     *
     * @param email email for user.
     */
    public UserCredential(final String email) {
        this.email = email;
    }

    public UserCredential() {

    }


   /* public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }*/


    public boolean isAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }


    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }


    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

   /* @Transient //not sure is valid or not.
    public Set<NewRole> getRoles() {
        return roles;
    }
    public void setRoles(Set<NewRole> roles) {
        this.roles = roles;
    }

    public void addRole(NewRole role) {
        getRoles().add(role);
    }
*/
   /* @Transient
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();
        authorities.addAll(roles);
        return authorities;
    }*/

    /**
     * Get username
     *
     * @return username
     */
//
    //  @Override
    @Transient
    public String getUsername() {
        return email;
    }

    @Transient
    public boolean isAccountNonExpired() {
        return !isAccountExpired();
    }

    @Transient
    public boolean isAccountNonLocked() {
        return !isAccountLocked();
    }

    @Transient
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this,
                ToStringStyle.MULTI_LINE_STYLE).append("email", this.email)
                .append("firstName", this.firstName)
                .append("lastName", this.lastName)
                .append("enabled", this.enabled)
                .append("accountExpired", this.accountExpired)
                .append("credentialsExpired", this.credentialsExpired)
                .append("accountLocked", this.accountLocked);

       /* if (roles != null) {
            sb.append("Granted Authorities: ");

            int i = 0;
            for (NewRole role : roles) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(role.toString());
                i++;
            }
        } else {
            sb.append("No Granted Authorities");
        }*/
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserCredential)) {
            return false;
        }
        final UserCredential user = (UserCredential) o;

        return !(email != null ? !email.equals(user.getEmail()) : user
                .getEmail() != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (email != null ? email.hashCode() : 0);
    }

    @Transient
    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    @Transient
    public boolean hasAuthority(String authority) {
        if (authority == null)
            return false;
        /*for (NewRole role : getRoles()) {
            if (authority.equals(role.getAuthority())) {
                return true;
            }
        }*/
        return false;
    }

}
