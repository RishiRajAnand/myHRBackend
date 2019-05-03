package com.erx.microservice.patientmanagement.repository;

/*
* created by Brighty on 16-11-2017
* */


import com.erx.microservice.patientmanagement.PatientManagementApp;
import com.erx.microservice.patientmanagement.service.datautil.DataUtil;
import com.erx.microservice.patientmanagement.domain.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PatientManagementApp.class)
public class ITRoomConfigurationRepositoryTest {

    @Autowired
    private RoomConfigurationMasterRepository roomConfigurationMasterRepository;

    @Autowired
    private BedTypeMasterRepository bedTypeMasterRepository;

    @Autowired
    private WardMasterRepository wardMasterRepository;

    @Autowired
    private DepartmentMasterRepository departmentMasterRepository;

    @Autowired
    private ClinicLocationRepository clinicLocationRepository;

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

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRoomConfigurationRepository() throws Exception {

        //***********Country*************
        // getting the Country data from datautil
        Country country = DataUtil.getCountry();

        //save country
        Country savedCountry = countryRepository.save(country);

        //***********State*************
        // getting the State data from datautil
        State state = DataUtil.getState();
        //setting country into state
        state.setCountry(country);

        //save state
        State savedState = stateRepository.save(state);

        //***********City*************
        // getting the City data from datautil
        City city = DataUtil.getCity();
        //setting state into city
        city.setState(savedState);
        //save city
        City savedCity = cityRepository.save(city);

        //***********Pincode*************
        // getting the Pincode data from datautil
        Pincode pincode = DataUtil.getPincode();

        //setting city into pincode
        pincode.setCity(savedCity);
        //save pincode
        Pincode savedpincode = pincodeRepository.save(pincode);

        //***********Location*************
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

        //save ClinicLocation
        ClinicLocation savedclinicLocation = clinicLocationRepository.save(clinicLocation);

        //DepartmentMaster
        DepartmentMaster departmentMaster = new DepartmentMaster();
        departmentMaster.setClinicLocation(savedclinicLocation);
        departmentMaster.setDepartmentName("Psychology");
        departmentMaster.setDepartmentId("DEP743");
        departmentMaster.setHaveBedType(false);
        departmentMaster.setStatus(false);
        departmentMaster.setSubDepartment(false);

        //when
        DepartmentMaster savedDepartmentMaster = departmentMasterRepository.save(departmentMaster);

        //WardMaster
        //Given
        WardMaster wardMaster = new WardMaster();
        wardMaster.setWardCode("WM767");
        wardMaster.setWardName("Single");
        wardMaster.setClinicLocationId(30L);
        wardMaster.setDepartment(savedDepartmentMaster);
        wardMaster.setIndentDepartment(savedDepartmentMaster);
        wardMaster.setIpRequestDepartment(savedDepartmentMaster);
        wardMaster.setStatus(true);

        //when
        WardMaster savedWardMaster = wardMasterRepository.save(wardMaster);

        //BedTypeMaster
        //Given
        BedTypeMaster bedTypeMaster = new BedTypeMaster();
        bedTypeMaster.setBedTypeCode("BTM767");
        bedTypeMaster.setBedTypeName("Single");
        bedTypeMaster.setClinicLocationId(30L);
        bedTypeMaster.setRate(10020.90);
        bedTypeMaster.setStatus(true);

        //when
        BedTypeMaster savedBedTypeMaster = bedTypeMasterRepository.save(bedTypeMaster);

        //*********************RoomConfigurationMaster**********************

        //Given
        RoomConfigurationMaster roomConfigurationMaster = new RoomConfigurationMaster();
        roomConfigurationMaster.setRoomCode("RM009");
        roomConfigurationMaster.setRoomNumber("006");
        roomConfigurationMaster.setClinicLocationId(30L);
        roomConfigurationMaster.setBedTypeMaster(savedBedTypeMaster);
        roomConfigurationMaster.setWardMaster(savedWardMaster);

        //when
        RoomConfigurationMaster savedRoomConfigurationMaster =
                roomConfigurationMasterRepository.save(roomConfigurationMaster);

        //Then
        assertNotNull(savedRoomConfigurationMaster);
        assertNotNull(savedRoomConfigurationMaster.getId());
        assertEquals(savedRoomConfigurationMaster.getRoomNumber(),
                roomConfigurationMaster.getRoomNumber());

        //Given
        RoomConfigurationMaster roomConfigurationMaster1 = roomConfigurationMasterRepository
                .findOne(savedRoomConfigurationMaster.getId());

        //Then
        Assert.assertNotNull(roomConfigurationMaster1);
        Assert.assertNotNull(roomConfigurationMaster1.getId());
        Assert.assertNotNull(roomConfigurationMaster1.getRoomNumber());

        //When
        roomConfigurationMasterRepository.delete(savedRoomConfigurationMaster.getId());

        //Then
        boolean isDeleted = roomConfigurationMasterRepository.exists(savedRoomConfigurationMaster.getId());
        assertFalse(isDeleted);

        //*********************RoomConfigurationMaster**********************

        //When (WardMaster)
        wardMasterRepository.delete(savedWardMaster.getId());

        //When (DepartmentMaster)
        departmentMasterRepository.delete(savedDepartmentMaster.getId());

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
