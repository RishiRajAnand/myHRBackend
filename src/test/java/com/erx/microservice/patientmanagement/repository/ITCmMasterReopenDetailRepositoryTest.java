package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.PatientManagementApp;
import com.erx.microservice.patientmanagement.service.datautil.DataUtil;
import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMasterReopenDetail;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterReopenDetailRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by brighty on 19-06-18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PatientManagementApp.class)
public class ITCmMasterReopenDetailRepositoryTest {

    @Autowired
    private CmMasterReopenDetailRepository cmMasterReopenDetailRepository;

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
    private LookupValueRepository lookupValueRepository;

    @Autowired
    private LookupRepository lookupRepository;

    @Autowired
    private PatientCategoryRepository patientCategoryRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitTypeMasterRepository visitTypeMasterRepository;

    @Autowired
    private CmMasterRepository cmMasterRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCmMasterReopenDetailRepository() throws Exception {

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
        Pincode savedpincode = pincodeRepository.save(pincode);

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
        clinic.setPincode(savedpincode);
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
        clinicLocation.setPincode(savedpincode);
        //setting Location into clinicLocation
        clinicLocation.setLocation(savedLocation);
        //setting State into clinicLocation
        clinicLocation.setState(savedState);

        //save ClinicLocation
        ClinicLocation savedclinicLocation = clinicLocationRepository.save(clinicLocation);


        //***********PatientCategory*************
        //Given
        PatientCategory patientCategory = new PatientCategory();
        patientCategory.setClinic(savedclinic);
        patientCategory.setTypeName("BPL");
        patientCategory.setDescription("bpl patient");
        //save PatientCategory
        PatientCategory savedPatientCategory = patientCategoryRepository.save(patientCategory);

        //Then
        assertNotNull(savedPatientCategory);
        assertNotNull(savedPatientCategory.getId());

        //create patient
        // getting the patient data from datautil
        Patient patient = DataUtil.getPatient();
        patient.setClinic(savedclinic);
        patient.setClinicLocation(savedclinicLocation);
        patient.setPatientCategory(savedPatientCategory);
        //save Patient(When)
        Patient savedPatient = patientRepository.save(patient);

        //UserDetail
        UserDetail userDetail = new UserDetail();
        userDetail.setRegistration_No("dfghj2345");
        userDetail.setClinic(savedclinic);
        userDetail.setPrimaryContact(true);
        userDetail.setDoctor(true);

        UserDetail savedUserDetail1 = userDetailRepository.save(userDetail);

        //CmMaster
        CmMaster cmMaster = new CmMaster();
        cmMaster.setClinicLocation(savedclinicLocation);
        cmMaster.setPatient(savedPatient);
        cmMaster.setIpCase(true);
        cmMaster.setCaseCreatedDate(LocalDateTime.now());
        cmMaster.setCaseStatus("Open");
        cmMaster.setClinicCaseNumber("dfghj3456");
        cmMaster.setDutyDoctor(savedUserDetail1);
        cmMaster.setUserDetail(savedUserDetail1);

        CmMaster savedCmMaster = cmMasterRepository.save(cmMaster);

        //************* CmMasterReopenDetail ***************
        //Given
        CmMasterReopenDetail cmMasterReopenDetail = new CmMasterReopenDetail();
        cmMasterReopenDetail.setCmMaster(savedCmMaster);
        cmMasterReopenDetail.setCaseCompletedDate(LocalDateTime.now());
        cmMasterReopenDetail.setStatus("Reopen");

        //when
        CmMasterReopenDetail savedCmMasterReopenDetail = cmMasterReopenDetailRepository.save(cmMasterReopenDetail);

        //Then
        assertNotNull(savedCmMasterReopenDetail);
        assertNotNull(savedCmMasterReopenDetail.getId());

        //When
        CmMasterReopenDetail foundCmMasterReopenDetail = cmMasterReopenDetailRepository.findOne(savedCmMasterReopenDetail.getId());

        //Then
        assertNotNull(foundCmMasterReopenDetail);
        assertNotNull(foundCmMasterReopenDetail.getId());
        assertNotNull(foundCmMasterReopenDetail.getCaseCompletedDate());

        //when
        cmMasterReopenDetailRepository.delete(savedCmMasterReopenDetail.getId());

        //Then
        boolean isDeleted = cmMasterReopenDetailRepository.exists(savedCmMasterReopenDetail.getId());
        assertFalse(isDeleted);

        //**************** /cmMasterReopenDetail *****************

        //when (CmMaster)
        cmMasterRepository.delete(savedCmMaster.getId());

        //when (UserDetail)
        userDetailRepository.delete(savedUserDetail1.getId());

        //when (Patient)
        patientRepository.delete(savedPatient.getId());

        //when (PatientCategory)
        patientCategoryRepository.delete(savedPatientCategory.getId());

        //when (clinicLocation)
        clinicLocationRepository.delete(savedclinicLocation.getId());

        //when
        clinicRepository.delete(savedclinic.getId());

        //when (Location)
        locationRepository.delete(savedLocation.getId());

        //When (Pincode)
        pincodeRepository.delete(savedpincode.getId());

        //when (City)
        cityRepository.delete(savedCity.getId());

        //when (State)
        stateRepository.delete(savedState.getId());

        //when (Country)
        countryRepository.delete(savedCountry.getId());
    }
}
