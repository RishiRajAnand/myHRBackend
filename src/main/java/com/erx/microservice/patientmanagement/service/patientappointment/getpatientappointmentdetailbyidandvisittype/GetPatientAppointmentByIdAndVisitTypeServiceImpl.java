package com.erx.microservice.patientmanagement.service.patientappointment.getpatientappointmentdetailbyidandvisittype;

/*
 * created by raushan on 30-05-2018.
 * */

import com.erx.microservice.patientmanagement.repository.PatientAppointmentRepository;
import com.erx.microservice.patientmanagement.service.dto.PatientAppointmentDTO;
import com.erx.microservice.patientmanagement.service.util.PatientConstants;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPatientAppointmentByIdAndVisitTypeServiceImpl implements GetPatientAppointmentByIdAndVisitTypeService {

    private final Logger log = LoggerFactory.getLogger(GetPatientAppointmentByIdAndVisitTypeServiceImpl.class);

    @Autowired
    PatientAppointmentRepository patientAppointmentRepository;

    @Override
    public GetPatientAppointmentByIdAndVisitTypeServiceResponse execute(GetPatientAppointmentByIdAndVisitTypeServiceRequest request) throws ServiceException {
        GetPatientAppointmentByIdAndVisitTypeServiceResponse response = null;
        PatientAppointmentDTO patientAppointmentDTO = new PatientAppointmentDTO();
        Long patientAppointmentID = null;
        String visitType = null;
        Object[] convertedPatientAppointmentObject = null;
        Object patientAppointmentObject = null;
        try {
            log.debug("Call to  patient appointment detail  for given patientAppointment Id and visitType");
            // retrieving  request
            patientAppointmentID = request.getPatientAppointmentID();
            visitType = request.getVisitType();
            if (patientAppointmentID != null) {
                 //updated by Bahubali on 19-11-2018
                patientAppointmentObject = patientAppointmentRepository.findByIdAndVisitType(patientAppointmentID, visitType);
                if (patientAppointmentObject != null) {
                    convertedPatientAppointmentObject = (Object[]) patientAppointmentObject;
                    //if firstName and lastName is not null then set it to dto
                    if (convertedPatientAppointmentObject[1] != null && convertedPatientAppointmentObject[2] != null)
                        patientAppointmentDTO.setDoctorName(convertedPatientAppointmentObject[1] + " " + convertedPatientAppointmentObject[2]);
                    patientAppointmentDTO.setVisitId((String) convertedPatientAppointmentObject[0]);//generatedPatId
                }//update end(Bahubali)
            }
            response = new GetPatientAppointmentByIdAndVisitTypeServiceResponse(patientAppointmentDTO);
            response.SUCCESSFUL = true;
            response.setMessage(PatientConstants.GET_PATIENT_APPOINTMENT_SUCCESS);
            log.debug("Retrieved patient appointment detail Successfully for given id and visitType");
        } catch (Exception e) {
            response = new GetPatientAppointmentByIdAndVisitTypeServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage(PatientConstants.GET_PATIENT_APPOINTMENT_FAILURE);
            log.error("Failed to retrieve patient appointment detail for given id and visitType,GetPatientAppointmentByIdAndVisitTypeServiceImpl-->execute-->" + e.getMessage());
        }
        return response;
    }
}
