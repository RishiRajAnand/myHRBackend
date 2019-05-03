package com.erx.microservice.patientmanagement.service.bedtypemaster.deletebedtypemaster;


import com.erx.microservice.patientmanagement.config.Constants;
import com.erx.microservice.patientmanagement.domain.BedTypeMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.BedTypeMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deleteBedTypeMasterService")
public class DeleteBedTypeMasterServiceImpl implements DeleteBedTypeMasterService {

    private final Logger log = LoggerFactory.getLogger(DeleteBedTypeMasterServiceImpl.class);

    @Autowired
    BedTypeMasterRepository bedTypeMasterRepository;

    DeleteBedTypeMasterServiceResponse response;
    BedTypeMaster bedTypeMaster;


    @Override
    public DeleteBedTypeMasterServiceResponse execute(DeleteBedTypeMasterServiceRequest request) throws ServiceException {

        log.debug("DeleteBedTypeMasterServiceImpl ==> Call to delete BedTypeMaster");


        try {
            if (request.getBedTypeId() != null || request.getBedTypeId() != 0) {

                //find the bed type object

                bedTypeMaster = bedTypeMasterRepository.findOne(request.getBedTypeId());

                if (bedTypeMaster != null) {

                    bedTypeMaster.setRecordStatus(0);

                    //save updated bed type object

                    bedTypeMasterRepository.save(bedTypeMaster);
                }


                //create response
                response = createResponse(true, Constants.BED_TYPE_MASTER_DELETE_SUCCESS);
                log.debug("DeleteBedTypeMasterServiceImpl ==> SUCCESS ");
                return response;


            }

            //create response
            response = createResponse(true, Constants.BED_TYPE_MASTER_DELETE_FAILURE_INVALID_INPUT);
            log.debug("DeleteBedTypeMasterServiceImpl ==> FAILED due to invalid input ");
            return response;


        } catch (Exception e) {

            response = createResponse(true, Constants.BED_TYPE_MASTER_DELETE_FAILURE);
            log.debug("DeleteBedTypeMasterServiceImpl ==> FAILED  DUE TO EXCEPTION "+e);

        }


        return response;
    }


    private DeleteBedTypeMasterServiceResponse createResponse(boolean successful, String message) {
        DeleteBedTypeMasterServiceResponse response = new DeleteBedTypeMasterServiceResponse();
        response.SUCCESSFUL = successful;
        response.setMessage(message);
        return response;
    }


}
