package com.erx.microservice.patientmanagement.service.patienttype.deletepatienttype;


import com.erx.microservice.patientmanagement.config.Constants;
import com.erx.microservice.patientmanagement.domain.PatientType;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.PatientTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deletePatientTypeService")
public class DeletePatientTypeServiceImpl implements  DeletePatientTypeService{

    private final Logger log = LoggerFactory.getLogger(DeletePatientTypeServiceImpl.class);

    @Autowired
    PatientTypeRepository patientTypeRepository;

    DeletePatientTypeServiceResponse response;

    PatientType patientType;

    @Override
    public DeletePatientTypeServiceResponse execute(DeletePatientTypeServiceRequest request) throws ServiceException {

        log.debug("DeletePatientTypeServiceImpl ==> Call to delete patient type Master");


        try {
            if (request.getPatientTypeId() != null || request.getPatientTypeId() != 0) {

                //find the patient type object

                patientType = patientTypeRepository.findOne(request.getPatientTypeId());

                if (patientType != null) {

                    patientType.setRecordStatus(0);

                    //save updated patient type object

                    patientTypeRepository.save(patientType);
                }


                //create response
                response = createResponse(true, Constants.PATIENT_TYPE_MASTER_DELETE_SUCCESS);
                log.debug("DeletePatientTypeServiceImpl ==> SUCCESS ");
                return response;


            }

            //create response
            response = createResponse(true, Constants.PATIENT_TYPE__MASTER_DELETE_FAILURE_INVALID_INPUT);
            log.debug("DeletePatientTypeServiceImpl ==> FAILED due to invalid input ");
            return response;


        } catch (Exception e) {

            response = createResponse(true, Constants.PATIENT_TYPE__MASTER_DELETE_FAILURE);
            log.debug("DeletePatientTypeServiceImpl ==> FAILED  DUE TO EXCEPTION "+e);

        }

        return response;
    }



    private DeletePatientTypeServiceResponse createResponse(boolean successful, String message) {

        response = new DeletePatientTypeServiceResponse();
        response.SUCCESSFUL = successful;
        response.setMessage(message);
        return response;
    }



}
