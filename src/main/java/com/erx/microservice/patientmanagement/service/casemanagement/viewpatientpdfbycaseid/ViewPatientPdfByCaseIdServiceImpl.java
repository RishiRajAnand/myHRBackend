package com.erx.microservice.patientmanagement.service.casemanagement.viewpatientpdfbycaseid;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationdetailsbycaseid.GetInvestigationDetailsByCaseIdServiceRequest;
import com.erx.microservice.patientmanagement.service.datautil.casemanagement.getcompletecasedetails.GetCompleteCaseDetailsService;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.*;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.CmTherapyTreatmentResponseDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("viewPatientPdfByCaseIdService")
public class ViewPatientPdfByCaseIdServiceImpl implements ViewPatientPdfByCaseIdService {

    private final Logger log = LoggerFactory.getLogger(ViewPatientPdfByCaseIdServiceImpl.class);

    @Autowired
    private GetCompleteCaseDetailsService getCompleteCaseDetails;


    @Override
    public ViewPatientPdfByCaseIdServiceResponse execute(ViewPatientPdfByCaseIdServiceRequest request) throws ServiceException {
        ViewPatientPdfByCaseIdServiceResponse response = null;
        GetComplaintsDTO getComplaintsDTO = new GetComplaintsDTO();
        List<CmMedicineTreatmentResponseDTO> cmMedicineTreatmentResponseDTOs = new ArrayList<>();
        CmPathyaPathyaDTO cmPathyaPathyaDTO = new CmPathyaPathyaDTO();
        CmInvestigationGetDTO cmInvestigationGetDTO = new CmInvestigationGetDTO();
        List<CmInvestigationDetailsGetDTO> cmInvestigationDetailsGetDTOs = new ArrayList<>();
        GetInvestigationDetailsByCaseIdServiceRequest getInvestigationDetailsByCaseIdServiceRequest = new GetInvestigationDetailsByCaseIdServiceRequest();
        List<CmTherapyTreatmentResponseDTO> cmTherapyTreatmentResponseDTOs = new ArrayList<>();
        List<GetCmPersonalHistoryDTO> getCmPersonalHistoryDTOs = new ArrayList<>();
        List<GetCmExaminationDTO> getCmExaminationDTOs = new ArrayList<>();
        try {
            log.debug("Call to get view patient pdf by case id");
            // retrieve complaints by case id
            getComplaintsDTO = getCompleteCaseDetails.getComplaints(request.getViewPdfRequestDTO().getCaseId());
            //retrieve treatment medicines by case id
            cmMedicineTreatmentResponseDTOs = getCompleteCaseDetails.getPatientTreatmentMedicines(request.getViewPdfRequestDTO().getCaseId());
            // retrieve pathya pathya by case id
            cmPathyaPathyaDTO = getCompleteCaseDetails.getPatientPathyaPathya(request.getViewPdfRequestDTO().getCaseId());
            // retrieve cm investigation by case id
            cmInvestigationGetDTO = getCompleteCaseDetails.getPatientCmInvestigation(request.getViewPdfRequestDTO().getCaseId());
            //forming request object for cm investigation details request to get the details
            getInvestigationDetailsByCaseIdServiceRequest.setCaseId(request.getViewPdfRequestDTO().getCaseId());
            getInvestigationDetailsByCaseIdServiceRequest.setClinicId(request.getViewPdfRequestDTO().getClinicId());
            getInvestigationDetailsByCaseIdServiceRequest.setClinicLocationId(request.getViewPdfRequestDTO().getClinicLocationId());
            getInvestigationDetailsByCaseIdServiceRequest.setPatientId(request.getViewPdfRequestDTO().getPatientId());
            getInvestigationDetailsByCaseIdServiceRequest.setUserId(request.getViewPdfRequestDTO().getUserId());
            // retrieve cm investigation details by case id
            cmInvestigationDetailsGetDTOs = getCompleteCaseDetails.getPatientCmInvestigationDetails(getInvestigationDetailsByCaseIdServiceRequest);
            //retrieve therapy details by case id
            cmTherapyTreatmentResponseDTOs = getCompleteCaseDetails.getPatientTreatmentTherapies(request.getViewPdfRequestDTO().getCaseId());
            //retrieve personal history by case id
            getCmPersonalHistoryDTOs = getCompleteCaseDetails.getPersonalHistory(request.getViewPdfRequestDTO().getCaseId());
            //retrieve examination details by case id
            getCmExaminationDTOs = getCompleteCaseDetails.getExaminationDetails(request.getViewPdfRequestDTO().getCaseId());
            // setting the dto to response
            response = new ViewPatientPdfByCaseIdServiceResponse(getComplaintsDTO,cmMedicineTreatmentResponseDTOs,cmPathyaPathyaDTO,cmInvestigationGetDTO,cmInvestigationDetailsGetDTOs,
                    cmTherapyTreatmentResponseDTOs,getCmPersonalHistoryDTOs,getCmExaminationDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved patient pdf details Successfully");
            log.debug("Retrieved patient pdf details Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve patient pdf details");
            log.error("Failed to retrieve patient pdf Details");
        }
        return response;
    }
}
