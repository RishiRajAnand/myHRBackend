package com.erx.microservice.patientmanagement.service.casemanagement.savecasedischarge;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmDischargeRequest;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmDischargeRequestRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterRepository;
import com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigationandtreatmentdetails.SaveInvestigationAndTreatmentDetailsService;
import com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigationandtreatmentdetails.SaveInvestigationAndTreatmentDetailsServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigationandtreatmentdetails.SaveInvestigationAndTreatmentDetailsServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDClinicDTO;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDDTO;
import com.erx.microservice.patientmanagement.service.util.CaseStatus;
import com.erx.microservice.patientmanagement.service.util.PatientConstants;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("saveCaseDischargeService")
public class SaveCaseDischargeServiceImpl implements SaveCaseDischargeService {

    private final Logger log = LoggerFactory.getLogger(SaveCaseDischargeServiceImpl.class);

    @Autowired
    private CmMasterRepository cmMasterRepository;

    @Autowired
    private CmDischargeRequestRepository cmDischargeRequestRepository;

    @Autowired
    private SaveInvestigationAndTreatmentDetailsService saveInvestigationAndTreatmentDetailsService;

    @Autowired
    private ServiceGateway serviceGateway;

    @Override
    public SaveCaseDischargeServiceResponse execute(SaveCaseDischargeServiceRequest request) throws ServiceException {
        SaveCaseDischargeServiceResponse response = null;
        CmMaster cmMaster = new CmMaster();
        CmMaster savedCmMaster = new CmMaster();
        CmDischargeRequest cmDischargeRequest = new CmDischargeRequest();
        SaveInvestigationAndTreatmentDetailsServiceResponse saveInvestigationAndTreatmentDetailsServiceResponse = new SaveInvestigationAndTreatmentDetailsServiceResponse();
        try {
            //validate input
            response = validateInput(request);
            if (!response.SUCCESSFUL) {
                log.debug("SaveCaseDischargeServiceImpl ==> FAILURE : Invalid input While Discharging");
                return response;
            }
            cmMaster = cmMasterRepository.findOne(request.getSaveDischargeDTO().getSaveInvestigationAndTreatmentDTO().getCmMasterId());
            if(cmMaster != null){
                if(request.getSaveDischargeDTO().getAdviceOnDischarge() != null){
                    cmMaster.setAdviceOnDischarge(request.getSaveDischargeDTO().getAdviceOnDischarge().trim());
                }
                cmMaster.setCaseStatus(CaseStatus.CASE_DISCHARGE_PENDING_STATUS);
                // save cm master with advice on discharge for discharge pending status
                savedCmMaster = cmMasterRepository.save(cmMaster);
                //forming the treatment request to generat order and save the cm treatment details
                if(request.getSaveDischargeDTO().getSaveInvestigationAndTreatmentDTO() != null){
                    request.getSaveDischargeDTO().getSaveInvestigationAndTreatmentDTO().setCmMasterDetailId(request.getSaveDischargeDTO().getSaveInvestigationAndTreatmentDTO().getCmMasterDetailId());
                    SaveInvestigationAndTreatmentDetailsServiceRequest saveInvestigationAndTreatmentDetailsServiceRequest = new SaveInvestigationAndTreatmentDetailsServiceRequest(request.getSaveDischargeDTO().getSaveInvestigationAndTreatmentDTO());
                    saveInvestigationAndTreatmentDetailsServiceResponse = saveInvestigationAndTreatmentDetailsService.execute(saveInvestigationAndTreatmentDetailsServiceRequest);
                }
                //set the DischargeRequest object
                if (request.getSaveDischargeDTO().getDischargeRequestId() != null)
                    cmDischargeRequest = cmDischargeRequestRepository.findOne(request.getSaveDischargeDTO().getDischargeRequestId());
                else {
                    cmDischargeRequest = new CmDischargeRequest();
                    cmDischargeRequest.setCreatedBy(request.getSaveDischargeDTO().getSaveInvestigationAndTreatmentDTO().getUserId());
                }
                cmDischargeRequest.setDischargeRequestDate(request.getSaveDischargeDTO().getDischargeRequestDate());
                cmDischargeRequest.setDischargeRequestTime(request.getSaveDischargeDTO().getDischargeRequestTime());
                cmDischargeRequest.setCmMaster(cmMaster);
                cmDischargeRequest.setUpdatedBy(request.getSaveDischargeDTO().getSaveInvestigationAndTreatmentDTO().getUserId());

                //Generate discharge generat number
                //retrieve and set clinicId,CurrentTblName and setCurrentClmName to DTO
                    Long clinicId = cmMaster.getClinicLocation().getClinic().getId();
                    GenerateUniqueIDDTO generateUniqueIDDTO = new GenerateUniqueIDDTO();
                    GenerateUniqueIDClinicDTO generateUniqueIDClinicDTO = new GenerateUniqueIDClinicDTO();
                    generateUniqueIDDTO.setCurrentTableName(ErxConstants.CDR_CURRENT_TABLE_NAME);
                    generateUniqueIDDTO.setCurrentColumnName(ErxConstants.CDR_CURRENT_COLUMN_NAME);
                    generateUniqueIDClinicDTO.setId(clinicId);
                    generateUniqueIDDTO.setGenerateUniqueIDClinicDTO(generateUniqueIDClinicDTO);
                    //call generateUniqueID
                    String generatedUniqueId = serviceGateway.generateUniqueID(generateUniqueIDDTO);
                    //SET THE UNIQUE ID
                    if (generatedUniqueId != null)
                        cmDischargeRequest.setDischargeRequestNumber(generatedUniqueId);

                //save IpAdmissionRequest
                CmDischargeRequest savedCmDischargeRequest = cmDischargeRequestRepository.save(cmDischargeRequest);
            }
            //create response
            response = new SaveCaseDischargeServiceResponse(cmDischargeRequest.getId(),saveInvestigationAndTreatmentDetailsServiceResponse.getBmOrderId(),saveInvestigationAndTreatmentDetailsServiceResponse.getBmOrderNumber(),
                    saveInvestigationAndTreatmentDetailsServiceResponse.getSaveMedicineResponseDTO(),saveInvestigationAndTreatmentDetailsServiceResponse.getSaveTreatmentTherapyResponseDTO());
            response.setMessage("Case sheet discharge medicine and therapy treatment saved successfully with order generation");
            log.debug("Case sheet discharge medicine and therapy treatment saved successfully with order generation");
        } catch (Exception e) {
            response = new SaveCaseDischargeServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to save Case sheet discharge medicine and therapy treatment with order generation");
            response.setMessage(e.getMessage() + " so,Failed to save Case sheet discharge medicine and therapy treatment with order generation");
        }

        return response;
    }

    private SaveCaseDischargeServiceResponse validateInput(SaveCaseDischargeServiceRequest request) throws Exception {
        SaveCaseDischargeServiceResponse response = new SaveCaseDischargeServiceResponse();
        response.SUCCESSFUL = false;
        if (request.getSaveDischargeDTO().getSaveInvestigationAndTreatmentDTO().getUserId() == null)
            response.setMessage(PatientConstants.INVALID_INPUT);
        else if (request.getSaveDischargeDTO().getSaveInvestigationAndTreatmentDTO().getCmMasterId() == null)
            response.setMessage(PatientConstants.INVALID_INPUT);
        else if (request.getSaveDischargeDTO().getDischargeRequestDate() == null)
            response.setMessage(PatientConstants.INVALID_INPUT);
        else if (request.getSaveDischargeDTO().getDischargeRequestTime() == null)
            response.setMessage(PatientConstants.INVALID_INPUT);
        else {
            response.SUCCESSFUL = true;
            return response;
        }
        return response;
    }
}
