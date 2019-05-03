package com.erx.microservice.patientmanagement.service.patientsearch;

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.CampRegistrationDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.PatientCaseCountSearchDTO;
import com.erx.microservice.patientmanagement.service.dto.PatientSearchDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by mkpatil on 28/12/17.
 */
public class PatientSearchServiceResponse extends CommonServiceResponse {

    List<PatientSearchDTO> patientSearchDTOList;
    List<CampRegistrationDTO> campRegistrationDTOList;
    // added for case sheet count for a patient
    Page<PatientCaseCountSearchDTO> patientCaseCountSearchDTOList;

    public PatientSearchServiceResponse(List<PatientSearchDTO> patientSearchDTOList, List<CampRegistrationDTO> campRegistrationDTOList,
                                        Page<PatientCaseCountSearchDTO> patientCaseCountSearchDTOList) {
        this.patientSearchDTOList = patientSearchDTOList;
        this.campRegistrationDTOList = campRegistrationDTOList;
        this.patientCaseCountSearchDTOList = patientCaseCountSearchDTOList;
    }

    public PatientSearchServiceResponse() {
    }

    public List<PatientSearchDTO> getPatientSearchDTOList() {
        return patientSearchDTOList;
    }

    public void setPatientSearchDTOList(List<PatientSearchDTO> patientSearchDTOList) {
        this.patientSearchDTOList = patientSearchDTOList;
    }

    public List<CampRegistrationDTO> getCampRegistrationDTOList() {
        return campRegistrationDTOList;
    }

    public void setCampRegistrationDTOList(List<CampRegistrationDTO> campRegistrationDTOList) {
        this.campRegistrationDTOList = campRegistrationDTOList;
    }

    public Page<PatientCaseCountSearchDTO> getPatientCaseCountSearchDTOList() {
        return patientCaseCountSearchDTOList;
    }

    public void setPatientCaseCountSearchDTOList(Page<PatientCaseCountSearchDTO> patientCaseCountSearchDTOList) {
        this.patientCaseCountSearchDTOList = patientCaseCountSearchDTOList;
    }
}
