package com.erx.microservice.patientmanagement.service.referralpatientmaster.deletereferralpatientmaster;


import com.erx.microservice.patientmanagement.domain.ReferralPatientMaster;
import com.erx.microservice.patientmanagement.repository.ReferralPatientMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/*
* created by Raushan on 20-11-2017
* */

@Service("deleteReferralPatientMasterByIdServiceImpl")
public class DeleteReferralPatientMasterByIdServiceImpl implements DeleteReferralPatientMasterByIdService {

    private final Logger log = LoggerFactory.getLogger(DeleteReferralPatientMasterByIdServiceImpl.class);

    @Autowired
    private ReferralPatientMasterRepository referralPatientMasterRepository;

    @Override
    public DeleteReferralPatientMasterByIdServiceResponse execute(DeleteReferralPatientMasterByIdServiceRequest request) throws Exception {

        DeleteReferralPatientMasterByIdServiceResponse response = null;
        Long referralId = null;
        ReferralPatientMaster foundReferralPatientMaster = null;
        ReferralPatientMaster savedReferralPatientMaster = null;

        try {
            referralId = request.getReferralId();
            foundReferralPatientMaster = referralPatientMasterRepository.findOne(referralId);
            foundReferralPatientMaster.setRecordStatus(0);
            savedReferralPatientMaster = referralPatientMasterRepository.save(foundReferralPatientMaster);
            //create response
            response = new DeleteReferralPatientMasterByIdServiceResponse();
            response.SUCCESSFUL = true;
            response.setMessage("ReferralPatientMaster is  deleted successfully for " + referralId + " referralId");
            log.debug("ReferralPatientMaster is  deleted successfully for  " + referralId + "  referralId");

        } catch (Exception e) {
            //create response
            response = new DeleteReferralPatientMasterByIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("ReferralPatientMaster is not deleted successfully for given referralId");
            log.error("ReferralPatientMaster is not deleted successfully for given referralId");
        }
        return response;
    }


}
