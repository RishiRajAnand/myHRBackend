package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.PatientManagementApp;
import com.erx.microservice.patientmanagement.service.datautil.DataUtil;
import com.erx.microservice.patientmanagement.domain.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

/**
 * Created by Raushan on 07/11/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PatientManagementApp.class)
public class ITClinicLocationRepositoryTest {
    @Autowired
    ClinicLocationRepository clinicLocationRepository;
    @Autowired
    UserStaffRepository userStaffRepository;
    @Autowired
    UserCredentialRepository userCredentialRepository;
    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    PincodeRepository pincodeRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    StateRepository stateRepository;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void saveFindDeleteClinicLocation() {

        //***********Country*************
        // getting the Country data from datautil
        Country country = DataUtil.getCountry();

        //when
        Country savedCountry = countryRepository.save(country);
        //then
        assertNotNull(savedCountry);
        assertNotNull(savedCountry.getId());

        //***********State*************
        // getting the State data from datautil
        State state = DataUtil.getState();
        //setting country into state
        state.setCountry(country);

        //when (state)
        State savedState = stateRepository.save(state);
        //then
        assertNotNull(savedState);
        assertNotNull(savedState.getId());

        //***********City*************
        // getting the City data from datautil
        City city = DataUtil.getCity();
        //setting state into city
        city.setState(savedState);
        //When (city)
        City savedCity = cityRepository.save(city);
        //then
        assertNotNull(savedCity);
        assertNotNull(savedCity.getId());

        //***********Pincode*************
        // getting the Pincode data from datautil
        Pincode pincode = DataUtil.getPincode();

        //setting city into pincode
        pincode.setCity(savedCity);
        //when (pincode)
        Pincode savedpincode = pincodeRepository.save(pincode);
        //then
        assertNotNull(savedpincode);
        assertNotNull(savedpincode.getId());

        //***********Location*************
        // getting the Location data from datautil
        Location location = DataUtil.getLocation();
        //setting city into Location
        location.setCity(city);
        //setting pincode into Location
        location.setPincode(pincode);
        //setting state into Location
        location.setState(state);

        //when (Location)
        Location savedLocation = locationRepository.save(location);
        //then
        assertNotNull(savedLocation);
        assertNotNull(savedLocation.getId());

        //***********Clinic*************
        // getting the Clinic data from datautil
        Clinic clinic = DataUtil.getClinic();
        //setting city into Clinic
        clinic.setCity(savedCity);
        //setting State into Clinic
        clinic.setState(savedState);
        //setting Country into Clinic
        clinic.setCountry(savedCountry);
        //setting Pincode into Clinic
        clinic.setPincode(savedpincode);
        //setting Location into Clinic
        clinic.setLocation(savedLocation);

        //when
        Clinic clinic1 = clinicRepository.save(clinic);

        clinic1.setClinicId(String.format("%03d", clinic1.getId()));

        //when (Clinic)
        Clinic savedclinic = clinicRepository.save(clinic1);
        //Then (Clinic)
        assertNotNull(savedclinic);
        assertNotNull(savedclinic.getId());

        //***********UserCredential*************
        // getting the UserCredential data from datautil
        UserCredential userCredential = DataUtil.getUserCredential();
        //when (UserCredential)
        UserCredential savedUserCredential = userCredentialRepository.save(userCredential);
        //then
        assertNotNull(savedUserCredential);
        assertNotNull(savedUserCredential.getId());

        //***********UserStaff*************
        //Given
        // getting the UserStaff data from datautil
        UserStaff userStaff = DataUtil.getUserStaff();
        //setting clinic into userStaff
        userStaff.setClinic(savedclinic);
        //setting clinic into UserCredential
        userStaff.setUserCredential(savedUserCredential);

        //when
        UserStaff savedUserStaff = userStaffRepository.save(userStaff);

        //Then
        assertNotNull(savedUserStaff);

        //***********ClinicLocation*************
        // getting the ClinicLocation data from datautil
        ClinicLocation clinicLocation = DataUtil.getClinicLocation();
        //setting clinic into clinicLocation
        clinicLocation.setClinic(savedclinic);
        //setting Country into clinicLocation
        clinicLocation.setCountry(savedCountry);
        //setting City into clinicLocation
        clinicLocation.setCity(savedCity);
        //setting pincode into clinicLocation
        clinicLocation.setPincode(savedpincode);
        //setting Location into clinicLocation
        clinicLocation.setLocation(savedLocation);
        //setting State into clinicLocation
        clinicLocation.setState(savedState);
        //setting UserStaff into clinicLocation
        clinicLocation.setAdministrator(savedUserStaff);

        //When (ClinicLocation)
        ClinicLocation savedclinicLocation = clinicLocationRepository.save(clinicLocation);

        //Then
        assertNotNull(savedclinicLocation);
        assertNotNull(savedclinicLocation.getId());

        //When (ClinicLocation)
        ClinicLocation foundclinicLocation = clinicLocationRepository.findOne(savedclinicLocation.getId());
        //Then
        assertNotNull(foundclinicLocation);
        assertNotNull(foundclinicLocation.getId());

        //when (clinicLocation)
        clinicLocationRepository.delete(savedclinicLocation.getId());
        //then
        boolean isDeleteClinicLocation = clinicLocationRepository.exists(savedclinicLocation.getId());
        assertFalse(isDeleteClinicLocation);

        //When (userStaff)
        userStaffRepository.delete(savedUserStaff.getId());
        //Then
        boolean isDeletedUserStaff = userStaffRepository.exists(savedUserStaff.getId());
        assertFalse(isDeletedUserStaff);

        //when (UserCredential)
        userCredentialRepository.delete(savedUserCredential.getId());
        //Then
        boolean isDeletedUserCredential = userCredentialRepository.exists(savedUserCredential.getId());
        assertFalse(isDeletedUserCredential);

        //when
        clinicRepository.delete(savedclinic.getId());
        //then
        boolean isDeletedClinic = clinicRepository.exists(savedclinic.getId());
        //assertFalse(isDeletedClinic);

        //when (Location)
        locationRepository.delete(savedLocation.getId());
        //then
        boolean isDeletedLocation = locationRepository.exists(savedLocation.getId());
        assertFalse(isDeletedLocation);

        //When (Pincode)
        pincodeRepository.delete(savedpincode.getId());
        //then
        boolean isDeletedPincode = pincodeRepository.exists(savedpincode.getId());
        assertFalse(isDeletedPincode);

        //when (City)
        cityRepository.delete(savedCity.getId());
        //then
        boolean isDeletedCity = cityRepository.exists(savedCity.getId());
        assertFalse(isDeletedCity);

        //when (State)
        stateRepository.delete(savedState.getId());
        //then
        boolean isDeletedState = stateRepository.exists(savedState.getId());
        assertFalse(isDeletedState);

        //when (Country)
        countryRepository.delete(savedCountry.getId());
        //then
        boolean isDeletedCountry = countryRepository.exists(savedCountry.getId());
        assertFalse(isDeletedCountry);

    }
}
