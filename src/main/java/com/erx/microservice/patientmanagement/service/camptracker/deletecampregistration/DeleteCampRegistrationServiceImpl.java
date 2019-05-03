package com.erx.microservice.patientmanagement.service.camptracker.deletecampregistration;

/*
* created by Brighty on 08-12-17
* */

import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.repository.PatientRepository;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deleteCampRegistrationService")
public class DeleteCampRegistrationServiceImpl implements DeleteCampRegistrationService {

    private final Logger log = LoggerFactory.getLogger(DeleteCampRegistrationServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public DeleteCampRegistrationServiceResponse execute(DeleteCampRegistrationServiceRequest request) throws ServiceException {

        DeleteCampRegistrationServiceResponse response = null;
        Patient patient = null;
        Patient savedPatient = null;
        try {
            log.debug("Call to delete Camp Registration");
            if (request.getPatientId() != null) {
                //retrieve patient object
                patient = patientRepository.findOne(request.getPatientId());
                //set recordStatus as 0
                patient.setRecordStatus(0);
                //save patient
                savedPatient = patientRepository.save(patient);

                //create response
                response = new DeleteCampRegistrationServiceResponse();
                response.SUCCESSFUL = true;
                response.setMessage("Deleted patient Successfully");
                log.debug("Deleted patient Successfully");
            }

        } catch (Exception e) {
            response = new DeleteCampRegistrationServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to delete patient Successfully");
            log.error("Failed to delete patient Successfully");
        }
        return response;
    }
}
