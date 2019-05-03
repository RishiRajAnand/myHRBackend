package com.erx.microservice.patientmanagement.domain;

/*
 * created by latha on 20-11-2017
 * */

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_id", nullable = true)
    @JsonIgnore
    private Clinic clinic;

    @Column(name = "name", nullable = false)
    private String patientName;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "registration_type", nullable = false)
    private String registrationType;

    @Column(name = "nationality", nullable = true)
    private String nationality;

    @OneToOne
    @JoinColumn(name = "marital_status_lookup_id")
    private LookupValue maritalStatus;

    @OneToOne
    @JoinColumn(name = "occupation_lookup_id")
    private LookupValue occupation;

    @Column(name = "allergies", nullable = true)
    private String allergies;

    @OneToOne
    @JoinColumn(name = "blood_group_lookup_id")
    private LookupValue bloodGroup;

    @Column(name = "language", nullable = true)
    private String language;

    @Column(name = "opt_in", nullable = true)
    private String optIn;

    @Column(name = "patient_id", nullable = true)
    private String patientId;

    @Column(name = "patient_id_external", nullable = true)
    private String patientIdExternal;

    @Column(name = "is_registered")
    private boolean registered;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_location_id", nullable = true)
    @JsonIgnore
    private ClinicLocation clinicLocation;

    @Lob
    @Column(name = "profile_image", nullable = true)
    private byte[] profileImage;

    @Column(name = "profile_image_format", nullable = true)
    private String imageformat;

    @Column(name = "registration_date", nullable = true)
    private LocalDateTime patientRegisteredDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "patient_category_id", nullable = true)
    @JsonIgnore
    private PatientCategory patientCategory;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private List<IpAdmission> ipAdmission;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private List<PatientAppointment> patientAppointments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private List<PatientCampRegistration> patientCampRegistrations;

    @OneToOne(mappedBy = "patient", fetch = FetchType.EAGER)
    private PatientAdditionalDetail patientAdditionalDetail;

    @OneToOne(mappedBy = "patient", fetch = FetchType.LAZY)
    private AddressInfo addressInfo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private List<PatientRefund> patientRefunds;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private List<PatientUhIdentifier> patientUhIdentifiers;

    @OneToOne
    @JoinColumn(name = "salutation_lookup_id")
    private LookupValue patientSalutation;

    //getters and setters

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOptIn() {
        return optIn;
    }

    public void setOptIn(String optIn) {
        this.optIn = optIn;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientIdExternal() {
        return patientIdExternal;
    }

    public void setPatientIdExternal(String patientIdExternal) {
        this.patientIdExternal = patientIdExternal;
    }

    public ClinicLocation getClinicLocation() {
        return clinicLocation;
    }

    public void setClinicLocation(ClinicLocation clinicLocation) {
        this.clinicLocation = clinicLocation;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public String getImageformat() {
        return imageformat;
    }

    public void setImageformat(String imageformat) {
        this.imageformat = imageformat;
    }

    public LocalDateTime getPatientRegisteredDate() {
        return patientRegisteredDate;
    }

    public void setPatientRegisteredDate(LocalDateTime patientRegisteredDate) {
        this.patientRegisteredDate = patientRegisteredDate;
    }

    public PatientCategory getPatientCategory() {
        return patientCategory;
    }

    public void setPatientCategory(PatientCategory patientCategory) {
        this.patientCategory = patientCategory;
    }

    public List<IpAdmission> getIpAdmission() {
        return ipAdmission;
    }

    public void setIpAdmission(List<IpAdmission> ipAdmission) {
        this.ipAdmission = ipAdmission;
    }

    public PatientAdditionalDetail getPatientAdditionalDetail() {
        return patientAdditionalDetail;
    }

    public void setPatientAdditionalDetail(PatientAdditionalDetail patientAdditionalDetail) {
        this.patientAdditionalDetail = patientAdditionalDetail;
    }

    public List<PatientAppointment> getPatientAppointments() {
        return patientAppointments;
    }

    public void setPatientAppointments(List<PatientAppointment> patientAppointments) {
        this.patientAppointments = patientAppointments;
    }

    public List<PatientCampRegistration> getPatientCampRegistrations() {
        return patientCampRegistrations;
    }

    public void setPatientCampRegistrations(List<PatientCampRegistration> patientCampRegistrations) {
        this.patientCampRegistrations = patientCampRegistrations;
    }

    public List<PatientRefund> getPatientRefunds() {
        return patientRefunds;
    }

    public void setPatientRefunds(List<PatientRefund> patientRefunds) {
        this.patientRefunds = patientRefunds;
    }

    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    public List<PatientUhIdentifier> getPatientUhIdentifiers() {
        return patientUhIdentifiers;
    }

    public void setPatientUhIdentifiers(List<PatientUhIdentifier> patientUhIdentifiers) {
        this.patientUhIdentifiers = patientUhIdentifiers;
    }

    public LookupValue getPatientSalutation() {
        return patientSalutation;
    }

    public void setPatientSalutation(LookupValue patientSalutation) {
        this.patientSalutation = patientSalutation;
    }

    public LookupValue getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(LookupValue maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public LookupValue getOccupation() {
        return occupation;
    }

    public void setOccupation(LookupValue occupation) {
        this.occupation = occupation;
    }

    public LookupValue getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(LookupValue bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
}
