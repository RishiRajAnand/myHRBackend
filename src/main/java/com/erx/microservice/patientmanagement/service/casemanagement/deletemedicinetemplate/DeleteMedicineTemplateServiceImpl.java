package com.erx.microservice.patientmanagement.service.casemanagement.deletemedicinetemplate;

/*
* created by Latha on 25-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.*;
import com.erx.microservice.patientmanagement.repository.casemanagement.*;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("deleteMedicineTemplateService")
public class DeleteMedicineTemplateServiceImpl implements DeleteMedicineTemplateService {

    private final Logger log = LoggerFactory.getLogger(DeleteMedicineTemplateServiceImpl.class);

    @Autowired
    private CmTemplateRepository cmTemplateRepository;

    @Autowired
    private CmTemplateMedicineRepository cmTemplateMedicineRepository;

    @Autowired
    private CmTemplateGroupMedicineDetailRepository cmTemplateGroupMedicineDetailRepository;

    @Override
    public DeleteMedicineTemplateServiceResponse execute(DeleteMedicineTemplateServiceRequest request) throws ServiceException {
        DeleteMedicineTemplateServiceResponse response = null;
        CmTemplate cmTemplate = new CmTemplate();
        List<CmTemplateMedicine> cmTemplateMedicines = new ArrayList<>();
        List<CmTemplateGroupMedicineDetail> cmTemplateGroupMedicineDetails = new ArrayList<CmTemplateGroupMedicineDetail>();
        try {
            log.debug("Call to delete medicine template detail");
            //find cm template medicines by id
            cmTemplateMedicines = cmTemplateMedicineRepository.findCmTemplateMedicineByCmTemplate(request.getCmTemplateId());

            for(CmTemplateMedicine cmTemplateMedicine : cmTemplateMedicines){
                //find cm template group medicine detail by id
                cmTemplateGroupMedicineDetails = cmTemplateGroupMedicineDetailRepository.findCmTemplateGroupMedicinesById(cmTemplateMedicine.getId());
                for(CmTemplateGroupMedicineDetail cmTemplateGroupMedicineDetail : cmTemplateGroupMedicineDetails){
                    cmTemplateGroupMedicineDetail.setRecordStatus(0);
                    cmTemplateGroupMedicineDetailRepository.save(cmTemplateGroupMedicineDetail);
                }
                //delete and save cm template medicine
                cmTemplateMedicine.setRecordStatus(0);
                cmTemplateMedicineRepository.save(cmTemplateMedicine);
            }
            cmTemplate = cmTemplateRepository.findOne(request.getCmTemplateId());
            cmTemplate.setRecordStatus(0);
            //delete cm template
            cmTemplateRepository.save(cmTemplate);
            // create response
            response = new DeleteMedicineTemplateServiceResponse();
            response.setMessage("Deleted the medicine template detail successfully");
        } catch (Exception e) {
            response = new DeleteMedicineTemplateServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to delete medicine template");
            response.setMessage(e.getMessage() + " so,Failed to delete medicine template");
        }
        return response;
    }
}
