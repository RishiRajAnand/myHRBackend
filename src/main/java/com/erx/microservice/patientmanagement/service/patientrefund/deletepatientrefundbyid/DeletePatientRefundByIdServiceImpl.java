package com.erx.microservice.patientmanagement.service.patientrefund.deletepatientrefundbyid;

/*
* created by Brighty on 27-11-17
* */

import com.erx.microservice.patientmanagement.domain.PatientRefund;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.PatientRefundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deleteRefundByIdService")
public class DeletePatientRefundByIdServiceImpl implements DeletePatientRefundByIdService {

    private final Logger log = LoggerFactory.getLogger(DeletePatientRefundByIdServiceImpl.class);

    @Autowired
    private PatientRefundRepository patientRefundRepository;

    @Override
    public DeletePatientRefundByIdServiceResponse execute(DeletePatientRefundByIdServiceRequest request) throws ServiceException {

        DeletePatientRefundByIdServiceResponse response = null;
        PatientRefund refund = null;
        PatientRefund deletedRefund = null;
        try {
            log.debug("Call to delete Refund");
            if (request.getRefundId() != 0) {
                //retrieve object
                refund = patientRefundRepository.findOne(request.getRefundId());
                //set recordStatus as 0
                refund.setRecordStatus(0);
                //update object
                deletedRefund = patientRefundRepository.save(refund);
            }
            response = new DeletePatientRefundByIdServiceResponse();
            response.SUCCESSFUL = true;
            response.setMessage("Deleted Refund Successfully");
            log.debug("Deleted Refund Successfully");
        } catch (Exception e) {
            response = new DeletePatientRefundByIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to delete Refund");
            log.error("Failed to delete Refund");
        }
        return response;
    }
}
