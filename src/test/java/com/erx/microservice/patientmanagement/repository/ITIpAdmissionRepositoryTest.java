package com.erx.microservice.patientmanagement.repository;

/**
 * Created by Latha on 29/11/17.
 */

import com.erx.microservice.patientmanagement.PatientManagementApp;
import com.erx.microservice.patientmanagement.service.datautil.DataUtil;
import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.IpAdmissionCaseMappingRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PatientManagementApp.class)
public class ITIpAdmissionRepositoryTest {

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

    @Autowired
    ClinicLocationRepository clinicLocationRepository;

    @Autowired
    PatientCategoryRepository patientCategoryRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UserStaffRepository userStaffRepository;

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @Autowired
    UserDetailRepository userDetailRepository;
    @Autowired
    IpAdmissionRepository ipAdmissionRepository;
    @Autowired
    IpAdmissionCaseMappingRepository ipAdmissionCaseMappingRepository;
    @Autowired
    CmMasterRepository cmMasterRepository;
    @Autowired
    IpAdmissionBedTransferRepository ipAdmissionBedTransferRepository;
    @Autowired
    IpAdmissionBedMovementRepository ipAdmissionBedMovementRepository;
    @Autowired
    private DepartmentMasterRepository departmentMasterRepository;
    @Autowired
    private BedConfigurationMasterRepository bedConfigurationMasterRepository;
    @Autowired
    private RoomConfigurationMasterRepository roomConfigurationMasterRepository;
    @Autowired
    private BedTypeMasterRepository bedTypeMasterRepository;
    @Autowired
    private WardMasterRepository wardMasterRepository;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    //save
    @Test
    public void testIpAdmission() throws Exception {

        //Given
        IpAdmission ipAdmission = new IpAdmission();

        //create clinic
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

        //Given
        Clinic savedclinic = clinicRepository.save(clinic1);

        //Then (Clinic)
        assertNotNull(savedclinic);
        assertNotNull(savedclinic.getId());

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

        //Then
        assertNotNull(savedclinicLocation);
        assertNotNull(savedclinicLocation.getId());

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

        //Then
        assertNotNull(savedPatient);
        assertNotNull(savedPatient.getId());

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

        //***********UserDetail*************
        //Given
        // getting the UserDetail data from datautil
        UserDetail userDetail = DataUtil.getUserDetail();
        //setting clinic into userDetail
        userDetail.setClinic(savedclinic);
        //setting clinic into UserCredential
        userDetail.setUserStaff(savedUserStaff);
        //when
        UserDetail savedUserDetail = userDetailRepository.save(userDetail);

        //Then
        assertNotNull(savedUserDetail);

        //***********BedMaster*************

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
        wardMaster.setClinicLocationId(savedclinicLocation.getId());
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
        bedTypeMaster.setClinicLocationId(savedclinicLocation.getId());
        bedTypeMaster.setRate(10020.90);
        bedTypeMaster.setStatus(true);

        //when
        BedTypeMaster savedBedTypeMaster = bedTypeMasterRepository.save(bedTypeMaster);

        //RoomConfigurationMaster
        //Given
        RoomConfigurationMaster roomConfigurationMaster = new RoomConfigurationMaster();
        roomConfigurationMaster.setRoomCode("RM009");
        roomConfigurationMaster.setRoomNumber("006");
        roomConfigurationMaster.setClinicLocationId(savedclinicLocation.getId());
        roomConfigurationMaster.setBedTypeMaster(savedBedTypeMaster);
        roomConfigurationMaster.setWardMaster(savedWardMaster);

        //when
        RoomConfigurationMaster savedRoomConfigurationMaster =
                roomConfigurationMasterRepository.save(roomConfigurationMaster);

        //*********************BedConfigurationMaster**********************

        //Given
        BedConfigurationMaster bedConfigurationMaster = new BedConfigurationMaster();
        bedConfigurationMaster.setClinicLocationId(savedclinicLocation.getId());
        bedConfigurationMaster.setBedNumber("006");
        bedConfigurationMaster.setAllocatedStatus("availability");
        bedConfigurationMaster.setBedCode("009");
        bedConfigurationMaster.setStatus(true);
        bedConfigurationMaster.setRoomConfigurationMaster(savedRoomConfigurationMaster);
        bedConfigurationMaster.setBedTypeMaster(savedBedTypeMaster);
        bedConfigurationMaster.setWardMaster(savedWardMaster);

        //when
        BedConfigurationMaster savedBedConfigurationMaster =
                bedConfigurationMasterRepository.save(bedConfigurationMaster);

        //Then
        assertNotNull(savedBedConfigurationMaster);
        assertNotNull(savedBedConfigurationMaster.getId());
        assertEquals(savedBedConfigurationMaster.getBedNumber(),
                bedConfigurationMaster.getBedNumber());

        //*************IpAdmission*************
        //Given
        ipAdmission.setClinic(savedclinic);
        ipAdmission.setPatient(savedPatient);
        ipAdmission.setUserDetail(savedUserDetail);
        ipAdmission.setBedMaster(savedBedConfigurationMaster);
        ipAdmission.setIpAdmissionNumber("IP-0321600002");
        ipAdmission.setAdmissionOn(LocalDateTime.now());
        ipAdmission.setIpAdmissionNotes("suggested by doctor");
        ipAdmission.setIpAdmissionStatus("Admitted");
        ipAdmission.setDischargedOn(LocalDateTime.now());

        //when
        IpAdmission savedIpAdmission = ipAdmissionRepository.save(ipAdmission);

        //Then
        assertNotNull(savedIpAdmission);
        assertNotNull(savedIpAdmission.getId());

        //When
        IpAdmission ipAdmission1 = ipAdmissionRepository.findOne
                (savedIpAdmission.getId());

        //Then
        assertNotNull(ipAdmission1);
        assertNotNull(ipAdmission1.getId());

        //**************CmMaster***********
        //Given
        CmMaster cmMaster = new CmMaster();
        cmMaster.setClinicLocation(savedclinicLocation);
        cmMaster.setPatient(savedPatient);
        cmMaster.setCaseCreatedDate(LocalDateTime.now());
        cmMaster.setCaseStatus("TherapyOngoing");
        cmMaster.setClinicCaseNumber("C301672016000005");
        cmMaster.setWellness(true);
        CmMaster savedCmMaster = cmMasterRepository.save(cmMaster);

        //Then
        assertNotNull(savedCmMaster);
        assertNotNull(savedCmMaster.getId());


        //**************IpAdmissionCaseMapping***********

        //Given
        IpAdmissionCaseMapping ipAdmissionCaseMapping = new IpAdmissionCaseMapping();

        ipAdmissionCaseMapping.setCmMaster(savedCmMaster);
        ipAdmissionCaseMapping.setIpAdmission(savedIpAdmission);

        //when
        IpAdmissionCaseMapping savedIpAdmissionCaseMapping =
                ipAdmissionCaseMappingRepository.save(ipAdmissionCaseMapping);

        //Then
        assertNotNull(savedIpAdmissionCaseMapping);
        assertNotNull(savedIpAdmissionCaseMapping.getId());

        //when
        IpAdmissionCaseMapping ipAdmissionCaseMapping1 = ipAdmissionCaseMappingRepository.
                findOne(savedIpAdmissionCaseMapping.getId());

        //Then
        assertNotNull(ipAdmissionCaseMapping1);
        assertNotNull(ipAdmissionCaseMapping1.getId());
        assertNotNull(ipAdmissionCaseMapping1.getIpAdmission());

        //when
        ipAdmissionCaseMappingRepository.delete(savedIpAdmissionCaseMapping.getId());

        //Then
        boolean isDeleted1 = ipAdmissionCaseMappingRepository.
                exists(savedIpAdmissionCaseMapping.getId());
        assertFalse(isDeleted1);

        //when
        cmMasterRepository.delete(savedCmMaster.getId());

        //Then
        boolean isDeleted = cmMasterRepository.
                exists(savedCmMaster.getId());
        assertFalse(isDeleted);

        //**************IpAdmissionBedTransfer***********

        //Given
        IpAdmissionBedTransfer ipAdmissionBedTransfer = new IpAdmissionBedTransfer();
        ipAdmissionBedTransfer.setBedTransferredFrom(savedBedConfigurationMaster);
        ipAdmissionBedTransfer.setBedTransferredTo(savedBedConfigurationMaster);
        ipAdmissionBedTransfer.setIpAdmission(savedIpAdmission);
        ipAdmissionBedTransfer.setUserStaff(savedUserStaff);

        //when
        IpAdmissionBedTransfer savedIpAdmissionBedTransfer =
                ipAdmissionBedTransferRepository.save(ipAdmissionBedTransfer);

        //Then
        assertNotNull(savedIpAdmissionBedTransfer);
        assertNotNull(savedIpAdmissionBedTransfer.getId());

        //when
        IpAdmissionBedTransfer ipAdmissionBedTransfer1 = ipAdmissionBedTransferRepository.
                findOne(savedIpAdmissionBedTransfer.getId());

        //Then
        assertNotNull(ipAdmissionBedTransfer1);
        assertNotNull(ipAdmissionBedTransfer1.getId());
        assertNotNull(ipAdmissionBedTransfer1.getIpAdmission());

        //when
        ipAdmissionBedTransferRepository.delete(savedIpAdmissionBedTransfer.getId());

        //Then
        boolean isDeleted2 = ipAdmissionBedTransferRepository.
                exists(savedIpAdmissionBedTransfer.getId());
        assertFalse(isDeleted2);


        //**************IpAdmissionBedMovement***********

        //Given
        IpAdmissionBedMovement ipAdmissionBedMovement = new IpAdmissionBedMovement();
        ipAdmissionBedMovement.setBedMovedDepartment(savedDepartmentMaster);
        ipAdmissionBedMovement.setBedMovedFrom(savedBedConfigurationMaster);
        ipAdmissionBedMovement.setBedMovedTo(savedBedConfigurationMaster);
        ipAdmissionBedMovement.setBedMovedSubDepartment(savedDepartmentMaster);
        ipAdmissionBedMovement.setIpAdmission(savedIpAdmission);
        ipAdmissionBedMovement.setUserStaff(savedUserStaff);

        //when
        IpAdmissionBedMovement savedIpAdmissionBedMovement =
                ipAdmissionBedMovementRepository.save(ipAdmissionBedMovement);

        //Then
        assertNotNull(savedIpAdmissionBedMovement);
        assertNotNull(savedIpAdmissionBedMovement.getId());

        //when
        IpAdmissionBedMovement ipAdmissionBedMovement1 = ipAdmissionBedMovementRepository.
                findOne(savedIpAdmissionBedMovement.getId());

        //Then
        assertNotNull(ipAdmissionBedMovement1);
        assertNotNull(ipAdmissionBedMovement1.getId());
        assertNotNull(ipAdmissionBedMovement1.getIpAdmission());

        //when
        ipAdmissionBedMovementRepository.delete(savedIpAdmissionBedMovement.getId());

        //Then
        boolean isDeleted3 = ipAdmissionBedMovementRepository.
                exists(savedIpAdmissionBedMovement.getId());
        assertFalse(isDeleted3);


        //**********************DeleteIpAdmission************************************************

        //When
        ipAdmissionRepository.delete(savedIpAdmission.getId());

        //Then
        boolean isDeleted4 = ipAdmissionRepository.exists(savedIpAdmission.getId());
        assertFalse(isDeleted4);

        //when (Bed)
        bedConfigurationMasterRepository.delete(savedBedConfigurationMaster.getId());
        //then
        boolean isDeleteBed = bedConfigurationMasterRepository.exists(savedBedConfigurationMaster.getId());
        assertFalse(isDeleteBed);

        //when (Room)
        roomConfigurationMasterRepository.delete(savedRoomConfigurationMaster.getId());
        //then
        boolean isDeleteRoom = roomConfigurationMasterRepository.exists(savedRoomConfigurationMaster.getId());
        assertFalse(isDeleteRoom);

        //when (WardMaster)
        wardMasterRepository.delete(savedWardMaster.getId());
        //then
        boolean isDeleteWard = wardMasterRepository.exists(savedWardMaster.getId());
        assertFalse(isDeleteWard);

        //when (BedType)
        bedTypeMasterRepository.delete(savedBedTypeMaster.getId());
        //then
        boolean isDeleteBedType = bedTypeMasterRepository.exists(savedBedTypeMaster.getId());
        assertFalse(isDeleteBedType);

        //when (Patient)
        patientRepository.delete(savedPatient.getId());
        //then
        boolean isDeletePatient = patientRepository.exists(savedPatient.getId());
        assertFalse(isDeletePatient);

        //when (Department)
        departmentMasterRepository.delete(savedDepartmentMaster.getId());
        //then
        boolean isDeleteDepartment = departmentMasterRepository.exists(savedDepartmentMaster.getId());
        assertFalse(isDeleteDepartment);

        //when (clinicLocation)
        clinicLocationRepository.delete(savedclinicLocation.getId());
        //then
        boolean isDeleteClinicLocation = clinicLocationRepository.exists(savedclinicLocation.getId());
        assertFalse(isDeleteClinicLocation);

        //When (userDetail)
        userDetailRepository.delete(savedUserDetail.getId());
        //Then
        boolean isDeletedUserDetail = userDetailRepository.exists(savedUserDetail.getId());
        assertFalse(isDeletedUserDetail);

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

        //when (PatientCategory)
        patientCategoryRepository.delete(savedPatientCategory.getId());
        //Then
        boolean isDeletedPatientCategory = patientCategoryRepository.exists(savedPatientCategory.getId());
        assertFalse(isDeletedPatientCategory);

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
