package com.erx.microservice.patientmanagement.service.therapymanagement.deletetherapytemplate;

/*
* created by Latha on 14-09-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplate;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplateGroupMedicineDetail;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplateMedicine;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplate;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplateMapping;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplateMedicine;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplateMedicineType;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTemplateGroupMedicineDetailRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTemplateMedicineRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTemplateRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyTemplateMappingRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyTemplateMedicineRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyTemplateMedicineTypeRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyTemplateRepository;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("deleteTherapyTemplateService")
public class DeleteTherapyTemplateServiceImpl implements DeleteTherapyTemplateService {

    private final Logger log = LoggerFactory.getLogger(DeleteTherapyTemplateServiceImpl.class);

    @Autowired
    private TherapyTemplateRepository therapyTemplateRepository;

    @Autowired
    private TherapyTemplateMappingRepository therapyTemplateMappingRepository;

    @Autowired
    private TherapyTemplateMedicineRepository therapyTemplateMedicineRepository;

    @Autowired
    private TherapyTemplateMedicineTypeRepository therapyTemplateMedicineTypeRepository;


    @Override
    public DeleteTherapyTemplateServiceResponse execute(DeleteTherapyTemplateServiceRequest request) throws ServiceException {
        DeleteTherapyTemplateServiceResponse response = null;
        TherapyTemplate therapyTemplate = new TherapyTemplate();
        List<TherapyTemplateMapping> therapyTemplateMappings = new ArrayList<>();
        List<TherapyTemplateMedicine> therapyTemplateMedicines = new ArrayList<>();
        List<TherapyTemplateMedicineType> therapyTemplateMedicineTypes = new ArrayList<>();

        try {
            log.debug("Call to delete therapy template details");

            //find therapy template mapping by therapy template id
            therapyTemplateMappings = therapyTemplateMappingRepository.findByTherapyTemplateId(request.getTherapyTemplateId());

            if(therapyTemplateMappings != null){
                for(TherapyTemplateMapping therapyTemplateMapping : therapyTemplateMappings){
                    //find therapy template medicine by therapy template mapping
                    therapyTemplateMedicines = therapyTemplateMedicineRepository.findByTherapyTemplateMappingId(therapyTemplateMapping.getId());
                    if(therapyTemplateMedicines != null) {
                        //delete therapy template medicine
                        for (TherapyTemplateMedicine therapyTemplateMedicine : therapyTemplateMedicines) {
                            therapyTemplateMedicine.setRecordStatus(0);
                            therapyTemplateMedicineRepository.save(therapyTemplateMedicine);
                        }
                    }
                    //find therapy template medicine type by therapy template mapping
                    therapyTemplateMedicineTypes = therapyTemplateMedicineTypeRepository.findByTherapyTemplateMappingId(therapyTemplateMapping.getId());
                    if(therapyTemplateMedicineTypes != null){
                        //delete therapy template medicine type
                        for(TherapyTemplateMedicineType therapyTemplateMedicineType : therapyTemplateMedicineTypes){
                            therapyTemplateMedicineType.setRecordStatus(0);
                            therapyTemplateMedicineTypeRepository.save(therapyTemplateMedicineType);
                        }
                    }
                    //delete therapy template mapping
                    therapyTemplateMapping.setRecordStatus(0);
                    therapyTemplateMappingRepository.save(therapyTemplateMapping);
                }
            }

            //find therapy template by id
            therapyTemplate = therapyTemplateRepository.findOne(request.getTherapyTemplateId());
            if(therapyTemplate != null)
                //delete therapy template by id
                therapyTemplate.setRecordStatus(0);
            therapyTemplateRepository.save(therapyTemplate);
            // create response
            response = new DeleteTherapyTemplateServiceResponse();
            response.setMessage("Deleted the Therapy template detail successfully");
        } catch (Exception e) {
            response = new DeleteTherapyTemplateServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to delete therapy template detail");
            response.setMessage(e.getMessage() + " so,Failed to delete therapy template detail");
        }
        return response;
    }
}
