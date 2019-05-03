package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.deletebedconfigmaster;


import com.erx.microservice.patientmanagement.config.Constants;
import com.erx.microservice.patientmanagement.domain.BedConfigurationMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.BedConfigurationMasterRepository;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deleteBedConfigMasterService")
public class DeleteBedConfigMasterServiceImpl implements DeleteBedConfigMasterService {

    private final Logger log = LoggerFactory.getLogger(DeleteBedConfigMasterServiceImpl.class);

    @Autowired
    BedConfigurationMasterRepository bedConfigurationMasterRepository;

    DeleteBedConfigMasterServiceResponse response;

    BedConfigurationMaster bedConfigurationMaster;

    @Override
    public DeleteBedConfigMasterServiceResponse execute(DeleteBedConfigMasterServiceRequest request) throws ServiceException {

        log.debug("DeleteBedConfigMasterServiceImpl ==> Call to delete BedConfigMaster");


        try {
            if (request.getBedConfigMasterId() != null || request.getBedConfigMasterId() != 0) {

                //find the bed config object

                bedConfigurationMaster = bedConfigurationMasterRepository.findOne(request.getBedConfigMasterId());

                if (bedConfigurationMaster != null) {

                    bedConfigurationMaster.setRecordStatus(0);

                    //save updated bed config

                    bedConfigurationMasterRepository.save(bedConfigurationMaster);
                }


                //create response
                response = createResponse(true, Constants.BED_CONFIG_MASTER_DELETE_SUCCESS);
                log.debug("DeleteBedConfigMasterServiceImpl ==> SUCCESS ");
                return response;


            }

            //create response
            response = createResponse(true, Constants.BED_CONFIG_MASTER_DELETE_FAILURE_INVALID_INPUT);
            log.debug("DeleteBedConfigMasterServiceImpl ==> FAILED due to invalid input ");
            return response;


        } catch (Exception e) {

            response = createResponse(true, Constants.BED_CONFIG_MASTER_DELETE_FAILURE);
            log.error("DeleteBedConfigMasterServiceImpl ==> FAILED  DUE TO EXCEPTION " + e);

        }


        return response;
    }


    private DeleteBedConfigMasterServiceResponse createResponse(boolean successful, String message) {

        response = new DeleteBedConfigMasterServiceResponse();
        response.SUCCESSFUL = successful;
        response.setMessage(message);
        return response;
    }


}
