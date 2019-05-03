package com.erx.microservice.patientmanagement.repository;



import com.erx.microservice.patientmanagement.PatientManagementApp;
import com.erx.microservice.patientmanagement.domain.Lookup;
import com.erx.microservice.patientmanagement.domain.LookupValue;
import com.erx.microservice.patientmanagement.domain.ReferralPatientMaster;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PatientManagementApp.class)
public class ITReferralPatientMasterRepositoryTest {
    @Autowired
    private ReferralPatientMasterRepository referralPatientMasterRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private LookupValueRepository lookupValueRepository;
    @Autowired
    private LookupRepository lookupRepository;
    @Autowired
    private ClinicLocationRepository clinicLocationRepository;
    @Autowired
    private UserStaffRepository userStaffRepository;
    @Autowired
    private UserCredentialRepository userCredentialRepository;
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
    public void testReferralPatientMasterRepository() {

        //***********LookupValue*************
        //Given
        Lookup lookUp=new Lookup();
        lookUp.setDescription("description");
        lookUp.setName("ReferralPatientLookTest");

        //When
        Lookup savedLookup=lookupRepository.save(lookUp);
        //***********LookupValue*************
        //Given
        LookupValue lookupValue = new LookupValue();
        lookupValue.setName("ReferralPatientLookValueTest");
        lookupValue.setRecordStatus(1);
        lookupValue.setUpdatedBy(1L);
        lookupValue.setLookupId(savedLookup);

        //When
        LookupValue saveLookUpVals = lookupValueRepository.save(lookupValue);

      //***********ReferralPatientMaster*************
        //Given
        ReferralPatientMaster referralPatientMaster=new ReferralPatientMaster();
        referralPatientMaster.setClinicLocationId(30L);
        referralPatientMaster.setDiscount(true);
        referralPatientMaster.setPhoneNumber("9812345678");
        //referralPatientMaster.setPincode(savedpincode);
        //referralPatientMaster.setUserDetail(savedUserDetail);
        referralPatientMaster.setReferralName("raushan");
        referralPatientMaster.setReferralId("RM002");
        referralPatientMaster.setStatus("Active");
        referralPatientMaster.setRefferalAddress("BTM");
        referralPatientMaster.setReferralRate(5);
        //referralPatientMaster.setReferralExternalLookupValue(saveLookUpVals);
        referralPatientMaster.setReferralRateLookupValue(saveLookUpVals);
        referralPatientMaster.setReferralTypeLookupValue(saveLookUpVals);

        //When
        ReferralPatientMaster savedReferralPatientMaster=referralPatientMasterRepository.save(referralPatientMaster);
        assertNotNull(savedReferralPatientMaster);
        assertNotNull(savedReferralPatientMaster.getId());
        //then
        ReferralPatientMaster foundReferralPatientMaster=referralPatientMasterRepository.findOne(savedReferralPatientMaster.getId());
        assertNotNull(foundReferralPatientMaster);
        assertNotNull(foundReferralPatientMaster.getId());
        //delete
        //when (ReferralPatientMaster)
        referralPatientMasterRepository.delete(savedReferralPatientMaster.getId());
        //then
        boolean isDeleteReferralPatientMaster = referralPatientMasterRepository.exists(savedReferralPatientMaster.getId());
        assertFalse(isDeleteReferralPatientMaster);

        //when (lookupValue)
        lookupValueRepository.delete(saveLookUpVals.getId());
        //Then
        boolean isDeletedLookupValue = lookupValueRepository.exists(saveLookUpVals.getId());
        assertFalse(isDeletedLookupValue);

        //when (LookUp)
        lookupRepository.delete(savedLookup.getId());
        //Then
        boolean isDeletedLookup = lookupRepository.exists(savedLookup.getId());
        assertFalse(isDeletedLookup);

    }
}
