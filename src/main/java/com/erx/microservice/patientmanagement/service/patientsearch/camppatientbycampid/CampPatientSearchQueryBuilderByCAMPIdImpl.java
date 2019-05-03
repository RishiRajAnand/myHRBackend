package com.erx.microservice.patientmanagement.service.patientsearch.camppatientbycampid;

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.service.dto.CampRegistrationDTO;
import com.erx.microservice.patientmanagement.service.dto.PackageCatalogueDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static com.erx.microservice.patientmanagement.service.patientsearch.PatientSearchUtil.CAMP_PATIENT_DETAIL_BASE_QUERY;

/**
 * Created by Raushan on 16/02/18.
 */

@Service("campPatientSearchQueryBuilderByCAMPId")
public class CampPatientSearchQueryBuilderByCAMPIdImpl implements CampPatientSearchQueryBuilderByCAMPId {

    private final Logger log = LoggerFactory.getLogger(CampPatientSearchQueryBuilderByCAMPIdImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ServiceGateway serviceGateway;

    @Override
    public List<CampRegistrationDTO> getCampPatientSearchResults(PatientSearchCriteria patientSearchCriteria) {

        log.debug("Call to search patient by " + patientSearchCriteria.getSearchType() + " for the value " + patientSearchCriteria.getSearchValue());
        Patient patient = null;
        PatientCampRegistration patientCampRegistration = null;
        PackageCatalogue packageCatalogue = null;
        AddressInfo addressInfo = null;
        List<CampRegistrationDTO> campRegistrationDTOs = new ArrayList<>();
        String whereClause = new String();
        try {
            if (patientSearchCriteria.getClinicId() != null & patientSearchCriteria.getSearchType() != null & patientSearchCriteria.getSearchValue() != null) {
                //findOut the package with the search value
                PackageCatalogueDTO packageCatalogueDTO = getPackageByName(patientSearchCriteria.getClinicLocationId(),
                        patientSearchCriteria.getSearchValue());
                if (packageCatalogueDTO != null && packageCatalogueDTO.getId() != null) {
                    whereClause = " where p.recordStatus = 1 and p.clinic.id = " + patientSearchCriteria.getClinicId() +
                            " and pcr.packageCatalogueId = '" + packageCatalogueDTO.getId() + "' ORDER BY pcr.createdOn DESC";
                    List<Object[]> campPatientResults = entityManager.createQuery(CAMP_PATIENT_DETAIL_BASE_QUERY + " " + whereClause).getResultList();
                    for (Object[] resultsDetail : campPatientResults) {
                        //create PatientDTO object
                        CampRegistrationDTO campRegistrationDTO = new CampRegistrationDTO();
                        patient = (Patient) resultsDetail[0];
                        //set patient details
                        campRegistrationDTO.setPatientId(patient.getId());
                        campRegistrationDTO.setClinicId(patient.getClinic().getId());
                        campRegistrationDTO.setFullName(patient.getPatientName());
                        LocalDateTime now = LocalDateTime.now();
                        int age = Period.between(patient.getDateOfBirth(), now.toLocalDate()).getYears();
                        campRegistrationDTO.setAge(age);
                        campRegistrationDTO.setGender(patient.getGender());
                        campRegistrationDTO.setMobileNumber(patient.getMobileNumber());
                        campRegistrationDTO.setMrn(patient.getPatientIdExternal());
                        campRegistrationDTO.setPatientRegisteredDate(patient.getPatientRegisteredDate());
                        if (patient.getPatientSalutation() != null)
                            campRegistrationDTO.setPatientSalutation(patient.getPatientSalutation().getName());

                        if (resultsDetail[1] != null) {
                            addressInfo = (AddressInfo) resultsDetail[1];

                            if (addressInfo.getCity() != null) {
                                campRegistrationDTO.setCity(addressInfo.getCity().getName());
                            }
                        }
                        if (resultsDetail[2] != null) {
                            patientCampRegistration = (PatientCampRegistration) resultsDetail[2];
                            campRegistrationDTO.setCampRegistrationNumber(patientCampRegistration.getCampRegistrationNumber());
                            campRegistrationDTO.setCampName(packageCatalogueDTO.getPackageName());
                            campRegistrationDTO.setCaseId(patientCampRegistration.getCaseId());
                        }
                        /*if (resultsDetail[3] != null) {
                            packageCatalogue = (PackageCatalogue) resultsDetail[3];
                            campRegistrationDTO.setCampName(packageCatalogue.getPackageName());
                        }*/
                        //add to list
                        campRegistrationDTOs.add(campRegistrationDTO);
                    }
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return campRegistrationDTOs;
    }

    private PackageCatalogueDTO getPackageByName(Long clinicLocationId, String packageName) throws Exception {
        PackageCatalogueDTO packageCatalogueDTO = null;
        JSONObject PackageCatalogueJSONObject = new JSONObject();
        //retrieve PackageCatalogueDTO
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
            e.printStackTrace();
        }
        return packageCatalogueDTO;
    }
}
