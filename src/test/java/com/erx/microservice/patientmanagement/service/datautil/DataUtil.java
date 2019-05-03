package com.erx.microservice.patientmanagement.service.datautil;

import com.erx.microservice.patientmanagement.domain.*;

import java.time.LocalDate;


public class DataUtil {

    //set country
    public static Country getCountry() {
        Country country = new Country();
        country.setName("India");
        country.setDescription("No");
        country.setIso("ac");
        country.setIso3("12");
        country.setNicename("Hindustan");
        country.setNumcode(11);
        country.setPhonecode(123245);
        country.setRecordStatus(1);
        country.setUpdatedBy(1L);
        return country;
    }

    //set State
    public static State getState() {
        State state = new State();
        state.setCode("123");
        state.setDescription("No");
        state.setName("Karnataka");
        state.setRecordStatus(1);
        state.setUpdatedBy(1L);
        state.setCode("12");
        return state;
    }

    //set City
    public static City getCity() {
        City city = new City();
        city.setCode("122");
        city.setDescription("No");
        city.setName("Banglore");
        city.setRecordStatus(1);
        city.setUpdatedBy(1);
        return city;
    }

    //set Pincode
    public static Pincode getPincode() {
        Pincode pincode = new Pincode();
        pincode.setDescription("Description");
        pincode.setPin("12");
        pincode.setUpdatedBy(1);
        pincode.setRecordStatus(1);
        return pincode;
    }

    //set Location
    public static Location getLocation() {
        Location location = new Location();
        Country country = DataUtil.getCountry();
        State state = DataUtil.getState();
        City city = DataUtil.getCity();
        Pincode pincode = DataUtil.getPincode();
        location.setName("btm");
        location.setRecordStatus(1);
        location.setUpdatedBy(1);
        return location;
    }

    //set UserCredential
    public static UserCredential getUserCredential() {
        UserCredential userCredential = new UserCredential();
        userCredential.setFirstName("raushan");
        userCredential.setLastName("kr");
        userCredential.setEmail("raushan01@erxindia.com");
        userCredential.setPhoneNumber("0987654321");
        userCredential.setEnabled(false);
        userCredential.setAccountExpired(false);
        userCredential.setCredentialsExpired(false);
        userCredential.setAccountLocked(false);
        userCredential.setPassword("71d9d93bb66c33798caa7df63a63112e8f6f1226");
        return userCredential;
    }

    //set UserStaff
    public static UserStaff getUserStaff() {
        UserStaff userStaff = new UserStaff();
        UserCredential userCredential = DataUtil.getUserCredential();
        userStaff.setEmail("raushan01@erxindia.com");
        userStaff.setFirstName("Raushan");
        userStaff.setLastName("R");
        userStaff.setGender("Male");
        userStaff.setMobileNumber("0987654321");
        userStaff.setUserCredential(userCredential);
        userStaff.setAccountExpired(true);
        userStaff.setUserStatus("1");
        userStaff.setAccountLocked(true);
        userStaff.setRecordStatus(1);
        userStaff.setErxUserId("erxsolution");
        userStaff.setLanguage("English");
        return userStaff;
    }

    //set UserDetail
    public static UserDetail getUserDetail() {
        UserDetail userDetail = new UserDetail();
        userDetail.setRegistration_No("65655");
        return userDetail;
    }

    //set ClinicLocation
    public static ClinicLocation getClinicLocation() {
        ClinicLocation clinicLocation = new ClinicLocation();
        clinicLocation.setClinicStatus("1");
        clinicLocation.setCode("1");
        clinicLocation.setAddress1("whitefield");
        clinicLocation.setDefaultLocation(true);
        clinicLocation.setFacilityType("clinic");
        clinicLocation.setPhoneNumber("7689098788");
        clinicLocation.setDefaultLocation(true);
        return clinicLocation;
    }

    //set Clinic
    public static Clinic getClinic() {
        Clinic clinic = new Clinic();
        clinic.setAddress1("whitefield");
        clinic.setLocationType("single");
        clinic.setAddress2("near post office");
        clinic.setName("erx Test Clinic");
        clinic.setPhoneNumber("21345677888");
        return clinic;
    }

    //set patient

    public static Patient getPatient() {
        Patient patient = new Patient();
        patient.setPatientName("Prashant");
        patient.setGender("Male");
        patient.setMobileNumber("0987654321");
        patient.setDateOfBirth(LocalDate.now());
        return patient;
    }
}
