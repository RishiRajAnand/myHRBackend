package com.erx.microservice.patientmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;


/**
 * Created by Latha on 05/11/17.
 */

@Entity
@Table(name = "user")
public class UserStaff extends BaseModel {

    @Column(name = "account_expired", nullable = true)
    private boolean accountExpired;

    @Column(name = "account_locked", nullable = true)
    private boolean accountLocked;

    @Column(name = "credentials_expired", nullable = true)
    private boolean credentialsExpired;

    @Column(name = "account_enabled")
    private boolean enabled;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_id", nullable = true)
    @JsonIgnore
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "primary_location_id", nullable = true)
    @JsonIgnore
    private ClinicLocation primaryClinicLocation;

    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_credential_id")
    @Valid
    @JsonIgnore
    private UserCredential userCredential;

    @Column(name = "first_name", nullable = false, length = 256)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 256)
    private String lastName;

    @Column(name = "mobile_number")
    @NotBlank(message = "{phone.required}")
    private String mobileNumber;

    @Column(name = "email", nullable = true)
    @Email(message = "{email.invalid}")
    private String email;

    @Column(name = "password", nullable = true)
    private String password;

    // required
    //    private String confirmPassword;
    @Column(name = "date_of_birth", nullable = true)
    private LocalDate dateOfBirth;

    @Transient
    private String dateOfBirthString;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "user_status", nullable = false)
    private String userStatus;

    @Column(name = "language", nullable = false)
    private String language;

    @Lob
    @Column(name = "profile_image", nullable = true)
    private byte[] profileImage;

    @Transient
    private String profileImageStr;

    @Column(name = "profile_image_format", nullable = true)
    private String profileImageFormat;

    @Column(name = "erx_user_id", nullable = false)
    private String erxUserId;

    @Column(name = "employment_id", nullable = true)
    private String employmentId;

    @Column(name = "user_information", nullable = true)
    private String userInformation;

    //Added by Brighty
    @OneToOne(mappedBy = "userStaff", fetch = FetchType.EAGER)
    private UserDetail userDetail;

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

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public ClinicLocation getPrimaryClinicLocation() {
        return primaryClinicLocation;
    }

    public void setPrimaryClinicLocation(ClinicLocation primaryClinicLocation) {
        this.primaryClinicLocation = primaryClinicLocation;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirthString() {
        return dateOfBirthString;
    }

    public void setDateOfBirthString(String dateOfBirthString) {
        this.dateOfBirthString = dateOfBirthString;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public String getProfileImageStr() {
        return profileImageStr;
    }

    public void setProfileImageStr(String profileImageStr) {
        this.profileImageStr = profileImageStr;
    }

    public String getProfileImageFormat() {
        return profileImageFormat;
    }

    public void setProfileImageFormat(String profileImageFormat) {
        this.profileImageFormat = profileImageFormat;
    }

    public String getErxUserId() {
        return erxUserId;
    }

    public void setErxUserId(String erxUserId) {
        this.erxUserId = erxUserId;
    }

    public String getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(String employmentId) {
        this.employmentId = employmentId;
    }

    public String getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(String userInformation) {
        this.userInformation = userInformation;
    }

    public UserCredential getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(UserCredential userCredential) {
        this.userCredential = userCredential;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }
}
