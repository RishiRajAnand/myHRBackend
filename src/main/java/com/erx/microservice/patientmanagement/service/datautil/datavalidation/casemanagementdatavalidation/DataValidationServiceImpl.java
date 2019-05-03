package com.erx.microservice.patientmanagement.service.datautil.datavalidation.casemanagementdatavalidation;

/*
 * created by Latha on 20-08-2018
 * */

import com.erx.microservice.patientmanagement.domain.ErxStatus;
import com.erx.microservice.patientmanagement.domain.Vital;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmGroupMedicineMaster;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplate;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplateMedicine;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyMaster;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplate;
import com.erx.microservice.patientmanagement.repository.VitalRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmGroupMedicineMasterRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTemplateMedicineRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTemplateRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyMasterRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyTemplateRepository;
import com.erx.microservice.patientmanagement.service.casemanagement.savebkdgroupmedicine.SaveBKDGroupMedicineServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.savemedicinetemplate.SaveMedicineTemplateServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.PatientVitalsRequestDTO;
import com.erx.microservice.patientmanagement.service.therapymanagement.savetherapymaster.SaveTherapyMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.savetherapytemplate.SaveTherapyTemplateServiceResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dataValidationService")
public class DataValidationServiceImpl implements DataValidationService{
    private final org.slf4j.Logger log = LoggerFactory.getLogger(DataValidationServiceImpl.class);

    @Autowired
    private VitalRepository vitalRepository;

    @Autowired
    private CmGroupMedicineMasterRepository cmGroupMedicineMasterRepository;

    @Autowired
    private CmTemplateRepository cmTemplateRepository;

    @Autowired
    private TherapyMasterRepository therapyMasterRepository;

    @Autowired
    private TherapyTemplateRepository therapyTemplateRepository;

    // method to validate vitals
    @Override
    public Vital validateVitals(PatientVitalsRequestDTO patientVitalsRequestDTO) {
        Vital vital = new Vital();
        try {
            log.debug("call to validate vitals");
            vital = vitalRepository.findVitalByVitalData(patientVitalsRequestDTO.getPatientId(), patientVitalsRequestDTO.getVitalsDTO().getBmi(),patientVitalsRequestDTO.getVitalsDTO().getBp(),
                    patientVitalsRequestDTO.getVitalsDTO().getHead(),patientVitalsRequestDTO.getVitalsDTO().getHeight(),patientVitalsRequestDTO.getVitalsDTO().getPulse(),
                    patientVitalsRequestDTO.getVitalsDTO().getTemperature(),patientVitalsRequestDTO.getVitalsDTO().getWeight());
        } catch (Exception e) {
            log.error("failed to validate vitals" + e.getMessage());
        }
        return vital;
    }

    //method to validate bkd medicine exist or not
    @Override
    public SaveBKDGroupMedicineServiceResponse validateBkdGroupName(String medicineName, Long clinicId) {
        SaveBKDGroupMedicineServiceResponse response = null;

        // create the response
        response = new SaveBKDGroupMedicineServiceResponse();
        //checking group name  is exists or not
        response.SUCCESSFUL = true;
        if (medicineName != null) {
            CmGroupMedicineMaster cmGroupMedicineMaster = cmGroupMedicineMasterRepository.findByGroupMedicineName(medicineName, clinicId);
            if (cmGroupMedicineMaster != null) {
                response.SUCCESSFUL = false;
                response.setMessage("Group Name already exists ");
                response.setErrorCode(ErxStatus.DUPLICATE_ERROR.getValue());
                response.setErrorMessage(ErxStatus.DUPLICATE_ERROR.getStatusMessage());
            }
        }
        return response;
    }

    //method to validate medicine template exist or not
    @Override
    public SaveMedicineTemplateServiceResponse validateMedicineTemplateName(String name, Long clinicId) {
        SaveMedicineTemplateServiceResponse response = null;
        // create the response
        response = new SaveMedicineTemplateServiceResponse();
        //checking medicine template name  is exists or not
        response.SUCCESSFUL = true;
        if (name != null) {
            CmTemplate cmTemplate = cmTemplateRepository.findCmTemplateByName(name, clinicId);
            if (cmTemplate != null) {
                response.SUCCESSFUL = false;
                response.setMessage("Medicine Template Name already exists ");
                response.setErrorCode(ErxStatus.DUPLICATE_ERROR.getValue());
                response.setErrorMessage(ErxStatus.DUPLICATE_ERROR.getStatusMessage());
            }
        }
        return response;
    }

    //method to validate therapy exist or not
    @Override
    public SaveTherapyMasterServiceResponse validateTherapyMaster(Long serviceCatalogueId, Long clinicId) {
        SaveTherapyMasterServiceResponse response = null;
        // create the response
        response = new SaveTherapyMasterServiceResponse();
        //checking therapy is exists or not
        response.SUCCESSFUL = true;
        if (serviceCatalogueId != null) {
            TherapyMaster therapyMaster = therapyMasterRepository.findByClinicAndService(serviceCatalogueId, clinicId);
            if (therapyMaster != null) {
                response.SUCCESSFUL = false;
                response.setMessage("Therapy already exists ");
                response.setErrorCode(ErxStatus.DUPLICATE_ERROR.getValue());
                response.setErrorMessage(ErxStatus.DUPLICATE_ERROR.getStatusMessage());
            }
        }
        return response;
    }

    //method to validate therapy template exist or not
    @Override
    public SaveTherapyTemplateServiceResponse validateTherapyTemplateName(String name, Long clinicId) {
        SaveTherapyTemplateServiceResponse response = null;
        // create the response
        response = new SaveTherapyTemplateServiceResponse();
        //checking therapy template name  is exists or not
        response.SUCCESSFUL = true;
        if (name != null) {
            TherapyTemplate therapyTemplate = therapyTemplateRepository.findCmTemplateByName(name, clinicId);
            if (therapyTemplate != null) {
                response.SUCCESSFUL = false;
                response.setMessage("Therapy Template Name already exists ");
                response.setErrorCode(ErxStatus.DUPLICATE_ERROR.getValue());
                response.setErrorMessage(ErxStatus.DUPLICATE_ERROR.getStatusMessage());
            }
        }
        return response;
    }
}
