package com.erx.microservice.patientmanagement.service.importcsvfile;

/*
 * created by Brighty on 26-03-2018
 * */

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.*;
import com.erx.microservice.patientmanagement.service.dto.casebillingorderdto.CreateBillingOrderInputDTO;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDClinicDTO;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDDTO;
import com.erx.microservice.patientmanagement.service.dto.PackageCatalogueDTO;
import com.erx.microservice.patientmanagement.service.util.PatientConstants;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service("importCampCSVFileService")
public class ImportCampCSVFileServiceImpl implements ImportCampCSVFileService {

    private final static Logger log = LoggerFactory.getLogger(ImportCampCSVFileService.class);

    @Autowired
    private ServiceGateway serviceGateway;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientCampRegistrationRepository patientCampRegistrationRepository;

    @Autowired
    private PatientUhIdentifierRepository patientUhIdentifierRepository;

    @Autowired
    private UhIdentifierRepository uhIdentifierRepository;

    @Autowired
    private AddressInfoRepository addressInfoRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private PincodeRepository pincodeRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private PatientTypeRepository patientTypeRepository;

    @Autowired
    private PatientAdditionalDetailRepository patientAdditionalDetailRepository;

    @Autowired
    private ClinicLocationRepository clinicLocationRepository;


    @Override
    public ImportCampCSVFileServiceResponse execute(ImportCampCSVFileServiceRequest request) throws ServiceException {
        log.debug(" Enters ExportCSVFileServiceImpl ----> execute ");
        ImportCampCSVFileServiceResponse response = null;

        BufferedReader bufferedReader = null;
        String line = "";
        String csvSplitBy = ",";
        MultipartFile multipartFile = null;
        List<String> messages = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        Long clinicLocationId = null;
        Long clinicId = null;
        Long userId = null;
        //File convertedFile = null;
        try {

            multipartFile = request.getMultipartFile();
            clinicLocationId = request.getClinicLocationId();
            userId = request.getUserId();
            clinicId = request.getClinicId();
            InputStream is = multipartFile.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            while ((line = bufferedReader.readLine()) != null) {
                // use comma as separator
                String[] patientDetail = line.split(csvSplitBy);
                Patient patient = null;
                JSONObject PackageCatalogueJSONObject = new JSONObject();
                PackageCatalogueDTO packageCatalogueDTO = null;
                PatientUhIdentifier patientUhIdentifier = new PatientUhIdentifier();
                AddressInfo addressInfo = new AddressInfo();
                //retrieve the mandatory fields
                String mrn = patientDetail[0];
                if (!mrn.equalsIgnoreCase("MRN")) {
                    String patientName = patientDetail[1];
                    //    String lastName = patientDetail[2];
                    String mobileNumber = patientDetail[2];
                    String email = patientDetail[3];
                    String dateOfBirth = patientDetail[4].replaceAll("-", " ");
                    String gender = patientDetail[5];
                    String packageName = patientDetail[15];
                    String createdOn = patientDetail[16].replaceAll("-", " ");
                    ;
                    LocalDate dob = LocalDate.parse(dateOfBirth, formatter);
                    LocalDate createdOnDate = LocalDate.parse(createdOn, formatter);
                    LocalDateTime createdOnDateTime = createdOnDate.atTime(LocalDateTime.now().getHour(),
                            LocalDateTime.now().getMinute(), LocalDateTime.now().getSecond());
                    //validate input
                    response = validateFile(patientDetail);
                    if (response.SUCCESSFUL == false) {
                        String message = response.getMessage() + " for the patient " + patientName;
                        messages.add(message);
                    } else {
                        int length = mrn.length();
                        if (mrn != null && length > 1 && !mrn.equalsIgnoreCase("null")) {
                            patient = patientRepository.getPatientByMRNAndClinic(request.getClinicId(), mrn);
                            if (patient == null) {
                                //retrieve package through serviceGateway
                                String message = PatientConstants.IMPORT_CAMP_CSV_INVALID_MRN + patientName;
                                messages.add(message);
                            } else {
                                //check for package
                                packageCatalogueDTO = getPackageByName(request.getClinicLocationId(), packageName);
                                if (packageCatalogueDTO == null) {
                                    String invalidPackage = PatientConstants.IMPORT_CAMP_CSV_INVALID_PACKAGE + patientName;
                                    messages.add(invalidPackage);
                                } else {
                                    PatientCampRegistration patientCampRegistration = new PatientCampRegistration();
                                    String CRN = generateCRN(request.getClinicId());
                                    patientCampRegistration.setCampRegistrationNumber(CRN);
                                    patientCampRegistration.setPatient(patient);
                                    patientCampRegistration.setPackageCatalogueId(packageCatalogueDTO.getId());
                                    patientCampRegistration.setCreatedOn(createdOnDateTime);
                                    patientCampRegistration.setUpdatedOn(createdOnDateTime);

                                    //save PatientCampRegistration
                                    patientCampRegistrationRepository.save(patientCampRegistration);
                                }
                            }
                        } else {

                            PatientCampRegistration patientCampRegistration = null;

                            packageCatalogueDTO = getPackageByName(request.getClinicLocationId(), packageName);
                            if (packageCatalogueDTO == null) {
                                String invalidPackage = PatientConstants.IMPORT_CAMP_CSV_INVALID_PACKAGE + patientName;
                                messages.add(invalidPackage);
                            } else {

                                List<Patient> patients;
                                patients = patientRepository.checkPatientDuplicacy(patientName,
                                        dob, gender, mobileNumber);
                                if (patients != null && !patients.isEmpty()) {
                                    patient = patients.get(0);
                                    List<PatientCampRegistration> patientCampRegistrations;
                                    patientCampRegistrations = patientCampRegistrationRepository
                                            .findByPatientAndPackage(patient.getId(), packageCatalogueDTO.getId());
                                    if (patientCampRegistrations != null && !patientCampRegistrations.isEmpty())
                                        patientCampRegistration = patientCampRegistrations.get(0);
                                } else {
                                    patient = new Patient();
                                    patient.setPatientName(patientName);
                                    patient.setMobileNumber(mobileNumber);
                                    patient.setEmail(email);
                                    patient.setDateOfBirth(dob);
                                    patient.setGender(gender);
                                    patient.setCreatedOn(createdOnDateTime);
                                    patient.setPatientRegisteredDate(createdOnDateTime);
                                    if (patientDetail[8] != null) {
                                        String language = patientDetail[7];
                                        patient.setLanguage(language);
                                    }
                                    Clinic clinic = clinicRepository.findOne(request.getClinicId());
                                    patient.setClinic(clinic);
                                    ClinicLocation clinicLocation = clinicLocationRepository.findOne
                                            (request.getClinicLocationId());
                                    if (clinicLocation != null)
                                        patient.setClinicLocation(clinicLocation);


                                    //save patient
                                    patient = patientRepository.save(patient);
                                    //saving PatientType
                                    if (clinicLocationId != null) {
                                        saveDefaultPatientType(patient, clinicLocationId);
                                    }
                                    if (patientDetail[9] != null) {
                                        String externalId = patientDetail[8];
                                    }
                                    if (patientDetail[10] != null) {
                                        String address = patientDetail[9];
                                        addressInfo.setContactAddress(address);
                                    }
                                    if (patientDetail[11] != null) {
                                        String city = patientDetail[10];
                                        City city1 = cityRepository.findByCityName(city);
                                        addressInfo.setCity(city1);
                                    }
                                    if (patientDetail[12] != null) {
                                        String state = patientDetail[11];
                                        State state1 = stateRepository.findByName(state);
                                        addressInfo.setState(state1);
                                    }
                                    if (patientDetail[13] != null) {
                                        String postalCode = patientDetail[12];
                                        Pincode pincode = pincodeRepository.findByCode(postalCode);
                                        addressInfo.setPincode(pincode);
                                    }
                                    if (patientDetail[14] != null) {
                                        String country = patientDetail[13];
                                        Country country1 = countryRepository.findByName(country);
                                        addressInfo.setCountry(country1);
                                    }
                                    addressInfo.setPatient(patient);
                                    addressInfo.setCreatedOn(createdOnDateTime);
                                    addressInfo.setUpdatedOn(createdOnDateTime);

                                    addressInfo = addressInfoRepository.save(addressInfo);

                                    if (patientDetail[6] != null) {
                                        String universalIdentificationType = patientDetail[5];
                                        if (patientDetail[7] != null) {
                                            String universalIdentificationNumber = patientDetail[6];
                                            UhIdentifier uhIdentifier = uhIdentifierRepository.
                                                    findByIdentificationType(universalIdentificationType);
                                            if (uhIdentifier == null) {
                                                uhIdentifier = uhIdentifierRepository.
                                                        findByIdentificationType("Others");
                                            }
                                            patientUhIdentifier.setCreatedOn(createdOnDateTime);
                                            patientUhIdentifier.setUpdatedOn(createdOnDateTime);
                                            patientUhIdentifier.setUhIdentifier(uhIdentifier);
                                            patientUhIdentifier.setPatient(patient);
                                            patientUhIdentifier.setIdentifier_number(universalIdentificationNumber);

                                            patientUhIdentifier = patientUhIdentifierRepository.save(patientUhIdentifier);
                                        }
                                    }
                                }

                                if (patientCampRegistration == null) {
                                    //save package
                                    patientCampRegistration = new PatientCampRegistration();
                                    String CRN = generateCRN(request.getClinicId());
                                    patientCampRegistration.setCampRegistrationNumber(CRN);
                                    patientCampRegistration.setPatient(patient);
                                    patientCampRegistration.setPackageCatalogueId(packageCatalogueDTO.getId());
                                    patientCampRegistration.setCreatedOn(createdOnDateTime);
                                    patientCampRegistration.setUpdatedOn(createdOnDateTime);

                                    //save PatientCampRegistration
                                    patientCampRegistrationRepository.save(patientCampRegistration);
                                    generateCampOrder(clinicId, clinicLocationId, patient.getId(), packageCatalogueDTO.getId(), userId);
                                }

                            }
                        }

                    }
                }
            }

            //Create response
            response = new ImportCampCSVFileServiceResponse(messages);
            response.SUCCESSFUL = true;
            response.setMessage("Import done successfully");
            log.debug("ImportCSVFileServiceImpl ==> SUCCESS");
        } catch (Exception e) {
            //Create response
            response = new ImportCampCSVFileServiceResponse();
            log.error("ImportCSVFileServiceImpl ==> execute " + e.getMessage());
            response.setMessage("Error on Importing patient Detail.");
            response.SUCCESSFUL = false;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    private PackageCatalogueDTO getPackageByName(Long clinicLocationId, String packageName) throws Exception {
        PackageCatalogueDTO packageCatalogueDTO = null;
        JSONObject PackageCatalogueJSONObject = new JSONObject();
        //retrieve the package by name
        try {
            JSONObject jsonObject = serviceGateway.getPackageCatalogueByName(clinicLocationId, packageName);
            if (jsonObject != null) {
                PackageCatalogueJSONObject = new JSONObject(jsonObject.getString("packageCatalogueDTO"));
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            packageCatalogueDTO = objectMapper.readValue(PackageCatalogueJSONObject.toString(),
                    PackageCatalogueDTO.class);
        } catch (Exception e) {
            log.debug("ImportCSVFileServiceImpl ==> getPackageByName : No package found : " + e.getMessage());
        }
        return packageCatalogueDTO;
    }

    public String generateCRN(Long clinicId) throws Exception {
        //generate  CRN ID
        GenerateUniqueIDDTO generateUniqueIDDTO = new GenerateUniqueIDDTO();
        GenerateUniqueIDClinicDTO generateUniqueIDClinicDTO = new GenerateUniqueIDClinicDTO();
        generateUniqueIDDTO.setCurrentTableName(ErxConstants.CRN_CURRENT_TABLE_NAME);
        generateUniqueIDDTO.setCurrentColumnName(ErxConstants.CRN_CURRENT_COLUMN_NAME);
        generateUniqueIDClinicDTO.setId(clinicId);
        generateUniqueIDDTO.setGenerateUniqueIDClinicDTO(generateUniqueIDClinicDTO);
        //call generateUniqueID()
        String generatedCRN = serviceGateway.generateUniqueID(generateUniqueIDDTO);
        return generatedCRN;
    }

    public ImportCampCSVFileServiceResponse validateFile(String[] patientDetail) throws Exception {

        ImportCampCSVFileServiceResponse response = new ImportCampCSVFileServiceResponse();
        response.SUCCESSFUL = false;

        String packageName = patientDetail[15];
        String firstName = patientDetail[1];
        String mobileNumber = patientDetail[2];
        String dateOfBirth = patientDetail[4];
        String gender = patientDetail[5];
        String language = patientDetail[8];
        String createdOn = patientDetail[16];
        if (packageName == null) {
            response.setMessage(PatientConstants.IMPORT_CAMP_CSV_INVALID_INPUT);
        } else if (firstName == null) {
            response.setMessage(PatientConstants.IMPORT_CAMP_CSV_INVALID_INPUT);
        } else if (mobileNumber == null) {
            if (mobileNumber.matches("[0-9]+"))
                response.setMessage(PatientConstants.IMPORT_CAMP_CSV_INVALID_INPUT);
        } else if (dateOfBirth == null) {
            response.setMessage(PatientConstants.IMPORT_CAMP_CSV_INVALID_INPUT);
        } else if (gender == null) {
            response.setMessage(PatientConstants.IMPORT_CAMP_CSV_INVALID_INPUT);
        } else if (language == null) {
            response.setMessage(PatientConstants.IMPORT_CAMP_CSV_INVALID_INPUT);
        } else if (createdOn == null) {
            response.setMessage(PatientConstants.IMPORT_CAMP_CSV_INVALID_INPUT);
        } else {
            response.SUCCESSFUL = true;
            return response;
        }
        return response;
    }

    public void saveDefaultPatientType(Patient patient, Long clinicLocationId) {
        String patientTypeName = PatientConstants.DEFAULT_PATIENT_TYPE;
        PatientType patientType = null;
        patientType = patientTypeRepository.findPatientTypeByPatient(patientTypeName, clinicLocationId);
        if (patientType != null) {
            PatientAdditionalDetail patientAdditionalDetail = new PatientAdditionalDetail();
            patientAdditionalDetail.setPatient(patient);
            patientAdditionalDetail.setPatientType(patientType);
            patientAdditionalDetailRepository.save(patientAdditionalDetail);
        }

    }

    //to generate Camp Order and bill
    public void generateCampOrder(Long clinicId, Long clinicLocationId, Long patientId, Long packageId, Long userId) {
        CreateBillingOrderInputDTO createBillingOrderInputDTO = new CreateBillingOrderInputDTO();
        createBillingOrderInputDTO.setClinicId(clinicId);
        createBillingOrderInputDTO.setClinicLocationId(clinicLocationId);
        createBillingOrderInputDTO.setPatientId(patientId);
        createBillingOrderInputDTO.setType("CAMP");
        createBillingOrderInputDTO.setId(packageId);
        createBillingOrderInputDTO.setOrderBy(userId);
        serviceGateway.generateCampOrder(createBillingOrderInputDTO);

    }
}
