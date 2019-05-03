package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.PatientManagementApp;
import com.erx.microservice.patientmanagement.service.datautil.DataUtil;
import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by brighty on 22-11-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PatientManagementApp.class)
public class ITIpAdmissionRequestRepositoryTest {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PincodeRepository pincodeRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ClinicLocationRepository clinicLocationRepository;

    @Autowired
    private IpAdmissionRequestRepository ipAdmissionRequestRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private CmMasterRepository cmMasterRepository;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testIpAdmissionRequestRepository() throws Exception {
        //Given
        // getting the Country data from datautil
        Country country = DataUtil.getCountry();

        //save country
        Country savedCountry = countryRepository.save(country);

        // getting the State data from datautil
        State state = DataUtil.getState();
        //setting country into state
        state.setCountry(country);

        //save state
        State savedState = stateRepository.save(state);

        // getting the City data from datautil
        City city = DataUtil.getCity();
        //setting state into city
        city.setState(savedState);
        //save city
        City savedCity = cityRepository.save(city);

        // getting the Pincode data from datautil
        Pincode pincode = DataUtil.getPincode();

        //setting city into pincode
        pincode.setCity(savedCity);
        //save pincode
        Pincode savedPincode = pincodeRepository.save(pincode);

        // getting the Location data from datautil
        Location location = DataUtil.getLocation();
        //setting city into Location
        location.setCity(city);
        //setting pincode into Location
        location.setPincode(pincode);
        //setting state into Location
        location.setState(state);

        //save Location
        Location savedLocation = locationRepository.save(location);

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
        clinic.setPincode(savedPincode);
        //setting Location into Clinic
        clinic.setLocation(savedLocation);

        //save Clinic
        Clinic clinic1 = clinicRepository.save(clinic);
        clinic1.setClinicId(String.format("%03d", clinic1.getId()));

        Clinic savedclinic = clinicRepository.save(clinic1);

        // getting the ClinicLocation data from datautil
        ClinicLocation clinicLocation = DataUtil.getClinicLocation();
        //setting clinic into clinicLocation
        clinicLocation.setClinic(savedclinic);
        //setting Country into clinicLocation
        clinicLocation.setCountry(savedCountry);
        //setting City into clinicLocation
        clinicLocation.setCity(savedCity);
        //setting pincode into clinicLocation
        clinicLocation.setPincode(savedPincode);
        //setting Location into clinicLocation
        clinicLocation.setLocation(savedLocation);
        //setting State into clinicLocation
        clinicLocation.setState(savedState);

        //save ClinicLocation
        ClinicLocation savedClinicLocation = clinicLocationRepository.save(clinicLocation);

        //Patient
        Patient patient = new Patient();
        patient.setPatientName("Manu j");
        patient.setGender("Male");
        patient.setDateOfBirth(LocalDate.now());
        patient.setMobileNumber("1234567890");
        patient.setEmail("dfghj@sdfghj");
        patient.setRegistrationType("wsdrfg");

        //save patient
        Patient savedPatient = patientRepository.save(patient);

        //CmMaster
        CmMaster cmMaster = new CmMaster();
        cmMaster.setClinicLocation(savedClinicLocation);
        cmMaster.setPatient(savedPatient);
        cmMaster.setIpCase(true);
        cmMaster.setCaseCreatedDate(LocalDateTime.now());
        cmMaster.setCaseStatus("active");
        cmMaster.setClinicCaseNumber("CSN000123");
        cmMaster.setWellness(true);

        CmMaster savedCmMaster = cmMasterRepository.save(cmMaster);

        //*************Deposit***************
        //Given
        IpAdmissionRequest ipAdmissionRequest = new IpAdmissionRequest();
        ipAdmissionRequest.setIpAdmissionDate(LocalDateTime.now());
        ipAdmissionRequest.setIpRequestNumber("IPR00012");
        ipAdmissionRequest.setCmMaster(savedCmMaster);

        //when
        IpAdmissionRequest savedIpAdmissionRequest = ipAdmissionRequestRepository.save(ipAdmissionRequest);

        //Then
        assertNotNull(savedIpAdmissionRequest);
        assertNotNull(savedIpAdmissionRequest.getId());
        assertEquals(savedIpAdmissionRequest.getIpRequestNumber(), ipAdmissionRequest.getIpRequestNumber());

        //When
        IpAdmissionRequest foundIpAdmissionRequest = ipAdmissionRequestRepository.findOne(savedIpAdmissionRequest.getId());

        //Then
        assertNotNull(foundIpAdmissionRequest);
        assertNotNull(foundIpAdmissionRequest.getId());
        assertNotNull(foundIpAdmissionRequest.getIpRequestNumber());

        //when
        ipAdmissionRequestRepository.delete(savedIpAdmissionRequest.getId());

        //Then
        boolean isDeleted = ipAdmissionRequestRepository.exists(savedIpAdmissionRequest.getId());
        assertFalse(isDeleted);

        //****************Deposit*****************

        //when (CmMaster)
        cmMasterRepository.delete(savedCmMaster.getId());

        //when(Patient)
        patientRepository.delete(savedPatient.getId());

        //when (clinicLocation)
        clinicLocationRepository.delete(savedClinicLocation.getId());

        //when
        clinicRepository.delete(savedclinic.getId());

        //when (Location)
        locationRepository.delete(savedLocation.getId());

        //When (Pincode)
        pincodeRepository.delete(savedPincode.getId());

        //when (City)
        cityRepository.delete(savedCity.getId());

        //when (State)
        stateRepository.delete(savedState.getId());

        //when (Country)
        countryRepository.delete(savedCountry.getId());
    }
}
