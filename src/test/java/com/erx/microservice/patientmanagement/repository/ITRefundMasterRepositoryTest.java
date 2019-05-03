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

import static org.junit.Assert.*;

/**
 * Created by brighty on 22-11-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PatientManagementApp.class)
public class ITRefundMasterRepositoryTest {

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
    private LookupValueRepository lookupValueRepository;

    @Autowired
    private LookupRepository lookupRepository;

    @Autowired
    private PatientRefundRepository refundMasterRepository;

    @Autowired
    private VisitTypeMasterRepository visitTypeMasterRepository;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testDeposiMasterRepository() throws Exception {
        //Given
        DepartmentMaster departmentMaster = new DepartmentMaster();

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

        //LookUpValue
        LookupValue lookupValue = new LookupValue();
        lookupValue.setName("wqewq");
        lookupValue.setRecordStatus(1);
        lookupValue.setUpdatedBy(1L);
        Lookup lookUp = new Lookup();
        lookUp.setDescription("wqerwq");
        lookUp.setName("yyy");
        Lookup savedLookup = lookupRepository.save(lookUp);
        lookupValue.setLookupId(savedLookup);
        //save lookUpValue
        LookupValue savedLookUpValue = lookupValueRepository.save(lookupValue);

        //create visitTypeMaster
        VisitTypeMaster visitTypeMaster = new VisitTypeMaster();
        visitTypeMaster.setClinicLocationId(12l);
        visitTypeMaster.setVisitType("Daycare");

        VisitTypeMaster savedVisitTypeMaster = visitTypeMasterRepository.save(visitTypeMaster);

        //*************Deposit***************
        //Given
        PatientRefund refundMaster = new PatientRefund();
        refundMaster.setClinicLocation(savedclinicLocation);
        refundMaster.setAccountName(savedVisitTypeMaster);
        refundMaster.setRefundNumber("1234");
        refundMaster.setRefundType(savedLookUpValue);
        refundMaster.setRefundableAmount(30000);

        //when
        PatientRefund savedRefundMaster = refundMasterRepository.save(refundMaster);

        //Then
        assertNotNull(savedRefundMaster);
        assertNotNull(savedRefundMaster.getId());
        assertEquals(savedRefundMaster.getRefundNumber(), refundMaster.getRefundNumber());

        //When
        PatientRefund foundRefundMaster = refundMasterRepository.findOne(savedRefundMaster.getId());

        //Then
        assertNotNull(foundRefundMaster);
        assertNotNull(foundRefundMaster.getId());
        assertNotNull(foundRefundMaster.getRefundNumber());

        //when
        refundMasterRepository.delete(savedRefundMaster.getId());

        //Then
        boolean isDeleted = refundMasterRepository.exists(savedRefundMaster.getId());
        assertFalse(isDeleted);

        //****************Deposit*****************

        //when (VisitTypeMaster)
        visitTypeMasterRepository.delete(savedVisitTypeMaster.getId());

        //when(LookUpValue)
        lookupValueRepository.delete(savedLookUpValue.getId());

        //when(LookUp)
        lookupRepository.delete(savedLookup.getId());

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
