package com.erx.microservice.patientmanagement.service.patientalldetails;

import com.erx.microservice.patientmanagement.domain.PatientAllDetails;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.PatientAllDetailsRepository;
import com.erx.microservice.patientmanagement.repository.PatientRepository;
import com.erx.microservice.patientmanagement.service.mapper.PatientAllDetailsMapper;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/*
 * created by Bahubali on 17-10-2018
 * */
@Service
public class SavePatientAllDetailsServiceImpl implements SavePatientAllDetailsService {

    private final Logger log = LoggerFactory.getLogger(SavePatientAllDetailsServiceImpl.class);

    @Autowired
    private PatientAllDetailsRepository patientAllDetailsRepository;

    @Autowired
    private PatientAllDetailsMapper patientAllDetailsMapper;
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public SavePatientAllDetailsServiceResponse execute(SavePatientAllDetailsServiceRequest request) throws ServiceException {
        PatientAllDetails patientAllDetails = null;
        int age;
        LocalDateTime currentDateTime;
        log.debug("Inside SavePatientAllDetailsServiceImpl to save patient all details");
        SavePatientAllDetailsServiceResponse response = null;
        try {
            //convert dto to entity
            if (request.getPatientAllDetailsDTO().getPatientId() != null) {
                switch (request.getPatientAllDetailsDTO().getContext()) {
                    case ErxConstants.PATIENT_SAVE:
                        currentDateTime = LocalDateTime.now();
                        age = Period.between(request.getPatientAllDetailsDTO().getDateOfBirth(), currentDateTime.toLocalDate()).getYears();

                        patientAllDetails = patientAllDetailsMapper.patientAllDetailsDTOToPatientAllDetails
                                (request.getPatientAllDetailsDTO());
                        patientAllDetails.setPatientAge(age);
                        if (request.getPatientAllDetailsDTO().getPatientRegisteredDate() != null)
                            patientAllDetails.setPatientRegisteredDate(request.getPatientAllDetailsDTO().getPatientRegisteredDate());
                        patientAllDetailsRepository.save(patientAllDetails);
                        response = constructResponse(true, "Patient all details saved successfully");
                        break;

                    case ErxConstants.PATIENT_UPDATE: //update patient details
                        patientAllDetailsRepository.updatePatientDetails(request.getPatientAllDetailsDTO().getPatientId(),
                                request.getPatientAllDetailsDTO().getPatientSalutation(),
                                request.getPatientAllDetailsDTO().getPatientMobileNumber(), request.getPatientAllDetailsDTO().getPatientEmail(),
                                request.getPatientAllDetailsDTO().getPatientContactAddress(), request.getPatientAllDetailsDTO().getPatientTypeId(),
                                request.getPatientAllDetailsDTO().getPatientTypeName());
                        response = constructResponse(true, "Patient all details updated successfully");
                        break;

                    case ErxConstants.PATIENT_MRN_SAVE://update ip details
                        patientAllDetailsRepository.updatePatientMRN(request.getPatientAllDetailsDTO().getPatientId(),
                                request.getPatientAllDetailsDTO().getPatientMRN());
                        response = constructResponse(true, "Patient MRN saved successfully");
                        break;

                    case ErxConstants.PATIENT_APPOINTMENT_SAVE://save appointment details
                        currentDateTime = LocalDateTime.now();
                        age = Period.between(request.getPatientAllDetailsDTO().getDateOfBirth(), currentDateTime.toLocalDate()).getYears();
                        patientAllDetails = patientAllDetailsMapper.patientAllDetailsDTOToPatientAllDetails
                                (request.getPatientAllDetailsDTO());
                        patientAllDetails.setPatientAge(age);
                        //get patient registrationDate and set to patientAllDetails and save
                        Timestamp registrationDate = patientRepository.findRegistrationDateByPatientId
                                (request.getPatientAllDetailsDTO().getPatientId());
                        Date convertedRegistrationDate = new Date(registrationDate.getTime());
                        LocalDateTime convertedRegistrationDateTime = LocalDateTime.from
                                (convertedRegistrationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                        patientAllDetails.setPatientRegisteredDate(convertedRegistrationDateTime);
                        patientAllDetailsRepository.save(patientAllDetails);
                        response = constructResponse(true, "Patient appointment details saved successfully");
                        break;

                    case ErxConstants.PATIENT_IP_SAVE: //save ip details
                        int updateCount = patientAllDetailsRepository.updateIPAdmissionDetails(request.getPatientAllDetailsDTO().getPatientId(),
                                request.getPatientAllDetailsDTO().getIpAdmissionNumber(), request.getPatientAllDetailsDTO().getIpAdmissionId(),
                                request.getPatientAllDetailsDTO().getBedTypeName(), request.getPatientAllDetailsDTO().getBedTypeId(),
                                request.getPatientAllDetailsDTO().getBedNumber(), request.getPatientAllDetailsDTO().getWardName(),
                                request.getPatientAllDetailsDTO().getRoomNumber(), request.getPatientAllDetailsDTO().getPatientVisitTypeName(),
                                request.getPatientAllDetailsDTO().getPatientVisitTypeMasterId(), request.getPatientAllDetailsDTO().isDayCarePatient(),
                                request.getPatientAllDetailsDTO().getIpAdmissionOn());
                        if (updateCount != 0) //check update is done or not
                            response = constructResponse(true, "Patient IP details saved successfully");
                        else
                            response = constructResponse(false, "Patient IP details not saved");
                        break;

                    case ErxConstants.PATIENT_BED_TRANSFER_UPDATE:
                        int countBedTransfer = patientAllDetailsRepository.updateBedTransferDetails(request.getPatientAllDetailsDTO().getPatientId(),
                                request.getPatientAllDetailsDTO().getBedTypeName(), request.getPatientAllDetailsDTO().getBedTypeId(),
                                request.getPatientAllDetailsDTO().getBedNumber(), request.getPatientAllDetailsDTO().getWardName(),
                                request.getPatientAllDetailsDTO().getRoomNumber());
                        if (countBedTransfer != 0) //check update is done or not
                            response = constructResponse(true, "Patient bed transfer details updated successfully");
                        else
                            response = constructResponse(false, "Patient bed transfer details not saved");
                        break;

                    case ErxConstants.PATIENT_CONVERT_NON_REGISTERED_TO_REGISTERED:
                        int countPatientUpdate = patientAllDetailsRepository.updatePatientMRNAndIsRegisteredByPatientId(
                                request.getPatientAllDetailsDTO().getPatientId(), request.getPatientAllDetailsDTO().getPatientMRN(),
                                request.getPatientAllDetailsDTO().isPatientRegistered());
                        if (countPatientUpdate != 0) //check update is done or not
                            response = constructResponse(true, "Patient details updated successfully");
                        else
                            response = constructResponse(false, "Patient details not updated");
                        break;
                }
            } else
                response = constructResponse(false, "Patient Id should not be null");
        } catch (Exception e) {
            response = constructResponse(false, "Failed to save patient all details");
        }
        return response;
    }

    //method to construct response
    private SavePatientAllDetailsServiceResponse constructResponse(boolean isSuccess, String message) throws ServiceException {

        SavePatientAllDetailsServiceResponse response = new SavePatientAllDetailsServiceResponse();
        response.SUCCESSFUL = isSuccess;
        response.setMessage(message);
        if (isSuccess)
            log.debug(message);
        else
            log.error(message);

        return response;
    }
}
