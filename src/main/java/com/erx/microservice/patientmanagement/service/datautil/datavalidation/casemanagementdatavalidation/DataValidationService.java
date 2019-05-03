package com.erx.microservice.patientmanagement.service.datautil.datavalidation.casemanagementdatavalidation;

/*
 * created by Latha on 20-08-2018
 * */

import com.erx.microservice.patientmanagement.domain.Vital;
import com.erx.microservice.patientmanagement.service.casemanagement.savebkdgroupmedicine.SaveBKDGroupMedicineServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.savemedicinetemplate.SaveMedicineTemplateServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.PatientVitalsRequestDTO;
import com.erx.microservice.patientmanagement.service.therapymanagement.savetherapymaster.SaveTherapyMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.savetherapytemplate.SaveTherapyTemplateServiceResponse;

public interface DataValidationService {
    Vital validateVitals(PatientVitalsRequestDTO patientVitalsRequestDTO);

    SaveBKDGroupMedicineServiceResponse validateBkdGroupName(String medicineName, Long clinicId);

    SaveMedicineTemplateServiceResponse validateMedicineTemplateName(String name, Long clinicId);

    SaveTherapyMasterServiceResponse validateTherapyMaster(Long serviceCatalogueId, Long clinicId);

    SaveTherapyTemplateServiceResponse validateTherapyTemplateName(String name, Long clinicId);
}
