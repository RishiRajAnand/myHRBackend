package com.erx.microservice.patientmanagement.service.wardmaster.deletewardmaster;


import com.erx.microservice.patientmanagement.config.Constants;
import com.erx.microservice.patientmanagement.domain.WardMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.WardMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deleteWardMasterService")
public class DeleteWardMasterServiceImpl implements DeleteWardMasterService {

    private final Logger log = LoggerFactory.getLogger(DeleteWardMasterServiceImpl.class);


    @Autowired
    WardMasterRepository wardMasterRepository;

    DeleteWardMasterServiceResponse response;
    WardMaster wardMaster;


    @Override
    public DeleteWardMasterServiceResponse execute(DeleteWardMasterServiceRequest request) throws ServiceException {

        log.debug("DeleteWardMasterServiceImpl ==> Call to delete Ward Master");


        try {
            if (request.getWardMasterId() != null || request.getWardMasterId() != 0) {

                //find the ward object

                wardMaster = wardMasterRepository.findOne(request.getWardMasterId());

                if (wardMaster != null) {

                    wardMaster.setRecordStatus(0);

                    //save updated WARD master

                    wardMasterRepository.save(wardMaster);
                }


                //create response
                response = createResponse(true, Constants.WARD_MASTER_DELETE_SUCCESS);
                log.debug("DeleteWardMasterServiceImpl ==> SUCCESS ");
                return response;


            }

            //create response
            response = createResponse(true, Constants.WARD_MASTER_DELETE_FAILURE_INVALID_INPUT);
            log.debug("DeleteWardMasterServiceImpl ==> FAILED due to invalid input ");
            return response;


        } catch (Exception e) {

            response = createResponse(true, Constants.WARD_MASTER_DELETE_FAILURE);
            log.debug("DeleteWardMasterServiceImpl ==> FAILED  DUE TO EXCEPTION "+e);

        }

        return response;
    }


    private DeleteWardMasterServiceResponse createResponse(boolean successful, String message) {

        DeleteWardMasterServiceResponse response = new DeleteWardMasterServiceResponse();
        response.SUCCESSFUL = successful;
        response.setMessage(message);
        return response;
    }


}
