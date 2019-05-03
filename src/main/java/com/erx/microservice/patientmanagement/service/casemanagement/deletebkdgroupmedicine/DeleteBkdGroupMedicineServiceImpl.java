package com.erx.microservice.patientmanagement.service.casemanagement.deletebkdgroupmedicine;

/*
* created by Latha on 02-09-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmGroupMedicineMaster;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMedicineGroup;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplate;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplateMedicine;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmGroupMedicineMasterRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMedicineGroupRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTemplateMedicineRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTemplateRepository;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("deleteBkdGroupMedicineService")
public class DeleteBkdGroupMedicineServiceImpl implements DeleteBkdGroupMedicineService {

    private final Logger log = LoggerFactory.getLogger(DeleteBkdGroupMedicineServiceImpl.class);

    @Autowired
    private CmGroupMedicineMasterRepository cmGroupMedicineMasterRepository;

    @Autowired
    private CmMedicineGroupRepository cmMedicineGroupRepository;

    @Override
    public DeleteBkdGroupMedicineServiceResponse execute(DeleteBkdGroupMedicineServiceRequest request) throws ServiceException {

        DeleteBkdGroupMedicineServiceResponse response = null;
        CmGroupMedicineMaster cmGroupMedicineMaster = new CmGroupMedicineMaster();
        List<CmMedicineGroup> cmMedicineGroups = new ArrayList<CmMedicineGroup>();
        try {
            log.debug("DeleteBkdGroupMedicineServiceImpl ==> Call to delete bkd group medicine master");

            //find cm group medicine master by id
            cmGroupMedicineMaster = cmGroupMedicineMasterRepository.findOne(request.getCmGroupMedicineMasterId());

            //find cm medicine group by cm group medicine master id
            if(cmGroupMedicineMaster != null)
            cmMedicineGroups = cmMedicineGroupRepository.findCmMedicineByGroupMedicineId(cmGroupMedicineMaster.getId());
            if(cmMedicineGroups != null)
                for(CmMedicineGroup cmMedicineGroup : cmMedicineGroups){
                    cmMedicineGroup.setRecordStatus(0);
                    cmMedicineGroupRepository.save(cmMedicineGroup);
                }
            // deleting cm group medicine
            cmGroupMedicineMaster.setRecordStatus(0);
            cmGroupMedicineMasterRepository.save(cmGroupMedicineMaster);
            //create response
            response = createResponse(true, "DeleteBkdGroupMedicineServiceImpl ==> SUCCESS ");
            log.debug("DeleteBkdGroupMedicineServiceImpl ==> SUCCESS ");
        } catch (Exception e) {
            response = createResponse(true, "DeleteBkdGroupMedicineServiceImpl ==> FAILED");
            log.debug("DeleteBkdGroupMedicineServiceImpl ==> FAILED  DUE TO EXCEPTION "+e);
        }
        return response;
    }

    private DeleteBkdGroupMedicineServiceResponse createResponse(boolean successful, String message) {
        DeleteBkdGroupMedicineServiceResponse response = new DeleteBkdGroupMedicineServiceResponse();
        response.SUCCESSFUL = successful;
        response.setMessage(message);
        return response;
    }
}
