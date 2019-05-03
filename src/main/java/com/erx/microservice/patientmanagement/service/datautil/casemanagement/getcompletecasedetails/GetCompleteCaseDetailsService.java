package com.erx.microservice.patientmanagement.service.datautil.casemanagement.getcompletecasedetails;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationdetailsbycaseid.GetInvestigationDetailsByCaseIdServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.*;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.CmTherapyTreatmentResponseDTO;

import java.util.List;

public interface GetCompleteCaseDetailsService {

    GetComplaintsDTO getComplaints(Long caseId);

    List<CmMedicineTreatmentResponseDTO> getTreatmentMedicines(Long caseId);

    List<CmTherapyTreatmentResponseDTO> getTreatmentTherapies(Long caseId);

    CmInvestigationGetDTO getCmInvestigation(Long caseId);

    List<CmInvestigationDetailsGetDTO> getCmInvestigationDetails(GetInvestigationDetailsByCaseIdServiceRequest request);

    CmPathyaPathyaDTO getPathyaPathya(Long caseId);

    List<GetCmPersonalHistoryDTO> getPersonalHistory(Long caseId);

    List<GetCmExaminationDTO> getExaminationDetails(Long caseId);

    List<CmMedicineTreatmentResponseDTO> getPatientTreatmentMedicines(Long caseId);

    CmPathyaPathyaDTO getPatientPathyaPathya(Long caseId);

    CmInvestigationGetDTO getPatientCmInvestigation(Long caseId);

    List<CmInvestigationDetailsGetDTO> getPatientCmInvestigationDetails(GetInvestigationDetailsByCaseIdServiceRequest getInvestigationDetailsByCaseIdServiceRequest);

    List<CmTherapyTreatmentResponseDTO> getPatientTreatmentTherapies(Long caseId);
}
