package com.erx.microservice.patientmanagement.service.casemanagement.deletemedicinetreatment;

/*
* created by Latha on 25-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmTreatmentGroupMedicineDetail;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmTreatmentMedicineDetail;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTreatmentGroupMedicineDetailRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTreatmentMedicineDetailRepository;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("deleteMedicineTreatmentService")
public class DeleteMedicineTreatmentServiceImpl implements DeleteMedicineTreatmentService {

    private final Logger log = LoggerFactory.getLogger(DeleteMedicineTreatmentServiceImpl.class);

    @Autowired
    private CmTreatmentMedicineDetailRepository cmTreatmentMedicineDetailRepository;

    @Autowired
    private CmTreatmentGroupMedicineDetailRepository cmTreatmentGroupMedicineDetailRepository;

    @Override
    public DeleteMedicineTreatmentServiceResponse execute(DeleteMedicineTreatmentServiceRequest request) throws ServiceException {
        DeleteMedicineTreatmentServiceResponse response = null;
        List<CmTreatmentGroupMedicineDetail> cmTreatmentGroupMedicineDetails = new ArrayList<>();
        try {
            log.debug("Call to delete medicine treatment detail");
            if(request.getDeleteMedicineTreatmentDTO().getCmTreatmentMedicineDetailId() != null && request.getDeleteMedicineTreatmentDTO().getCmTreatmentMedicineGroupId() == null) {
                cmTreatmentMedicineDetailRepository.delete(request.getDeleteMedicineTreatmentDTO().getCmTreatmentMedicineDetailId());
                cmTreatmentGroupMedicineDetails = cmTreatmentGroupMedicineDetailRepository.findCmTreatmentGroupByMedicineDetail(request.getDeleteMedicineTreatmentDTO().getCmTreatmentMedicineDetailId());
                if(cmTreatmentGroupMedicineDetails != null)
                    cmTreatmentGroupMedicineDetailRepository.delete(cmTreatmentGroupMedicineDetails);
            }
            if(request.getDeleteMedicineTreatmentDTO().getCmTreatmentMedicineGroupId() != null){
                cmTreatmentGroupMedicineDetailRepository.delete(request.getDeleteMedicineTreatmentDTO().getCmTreatmentMedicineGroupId());
            }
            // create response
            response = new DeleteMedicineTreatmentServiceResponse();
            response.setMessage("Deleted the medicine treatment detail successfully");
        } catch (Exception e) {
            response = new DeleteMedicineTreatmentServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to delete medicine treatment case sheet");
            response.setMessage(e.getMessage() + " so,Failed to delete medicine treatment case sheet");
        }
        return response;
    }
}
