package com.erx.microservice.patientmanagement.service.patient.convertnonregisteredtoregisteredpatient;
/*
created By Manisha on 08-10-2018
 */

import com.erx.microservice.patientmanagement.domain.Clinic;
import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.domain.casemanagement.ClinicPreference;
import com.erx.microservice.patientmanagement.repository.ClinicRepository;
import com.erx.microservice.patientmanagement.repository.PatientRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.ClinicPreferenceRepository;
import com.erx.microservice.patientmanagement.service.dto.patientalldetailsdto.PatientAllDetailsDTO;
import com.erx.microservice.patientmanagement.service.patientalldetails.SavePatientAllDetailsService;
import com.erx.microservice.patientmanagement.service.patientalldetails.SavePatientAllDetailsServiceRequest;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;

@Service
public class ConvertNonRegisteredToRegisterPatientServiceImpl implements ConvertNonRegisteredToRegisterPatientService {
    private final Logger log = LoggerFactory.getLogger(ConvertNonRegisteredToRegisterPatientServiceImpl.class);
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private ClinicPreferenceRepository clinicPreferenceRepository;
    @Autowired
    private SavePatientAllDetailsService savePatientAllDetailsService;

    @Override
    public ConvertNonRegisteredToRegisterPatientServiceResponse execute(ConvertNonRegisteredToRegisterPatientServiceRequest request) {
        ConvertNonRegisteredToRegisterPatientServiceResponse response = null;
        Long patientId = null;
        Long clinicId = null;
        Patient patient = null;
        Clinic clinic = null;
        String mrn = null;
        try {
            log.debug("ConvertNonRegisteredToRegisterPatientServiceImpl-->Call to converet non registered patient to register patient..");
            //retrieve from request
            patientId = request.getPatientId();
            clinicId=request.getClinicId();
            //validate input
            response = validateInput(patientId, clinicId);
            if (!response.SUCCESSFUL)
                return response;
            patient = patientRepository.findPatientById(patientId);
            if (patient == null)
                throw new Exception("patient with id doesn't exist :" + patientId);
            clinic = clinicRepository.findOne(clinicId);
            if (clinic == null)
                throw new Exception("clinic with id doesn't exist :" + clinicId);
            if (patient.isRegistered() == false)
                //call function to generate mrn
                mrn = generateMrn(patient, clinic);
            //create response
            response = new ConvertNonRegisteredToRegisterPatientServiceResponse();
            response.SUCCESSFUL = true;
            response.setMessage("Non registered patient converted successfully to registered patient with mrn :" + mrn);
            log.debug("Non registered patient converted successfully to registered patient with mrn :" + mrn);
        } catch (Exception e) {
            response = new ConvertNonRegisteredToRegisterPatientServiceResponse();
            response.SUCCESSFUL = false;
            response.setErrorMessage("Failed To convert non register to register patient :" + e.getMessage());
            log.error("Failed To converet non register to register patient :" + e.getMessage());
        }
        return response;
    }

    //fuction to generate mrn
    private String generateMrn(Patient patient, Clinic clinic) throws SerialException {
        ClinicPreference clinicPreference = null;
        int lastmrd;
        String erternalId = null;
        String patientId = null;
        clinicPreference = clinicPreferenceRepository.findByClinicId(clinic.getId());
        lastmrd = Integer.parseInt(clinicPreference.getLast_mrd());
        lastmrd++;
        int length = clinicPreference.getLast_mrd().length();
        erternalId = clinicPreference.getMrd_format();
        String lastmrdstr = (String.format("%0" + length + "d", lastmrd));
        patientId = String.format("%1$05d-%2$s", patient.getId(), clinic.getId());
        patient.setPatientId(patientId);
        patient.setPatientIdExternal(erternalId.replaceFirst("[1]{5}", lastmrdstr));
        patient.setRegistered(true);
        Patient patientAfterUpdate = patientRepository.saveAndFlush(patient);
        //call method to update mrn and is registered field
        updateMRNAndIsRegisteredInPatientAllDetails(patientAfterUpdate.getId(),patientAfterUpdate.getPatientIdExternal(),
                patientAfterUpdate.isRegistered());//added by Bahubali on 13-11-2018
        //updating the mrd number in clinic preference
        clinicPreference.setLast_mrd(String.valueOf(lastmrdstr));
        clinicPreferenceRepository.saveAndFlush(clinicPreference);

        return patientAfterUpdate.getPatientIdExternal();
    }

    //validate mandatory input
    private ConvertNonRegisteredToRegisterPatientServiceResponse validateInput(Long patientId, Long clinicId) {
        ConvertNonRegisteredToRegisterPatientServiceResponse response = new ConvertNonRegisteredToRegisterPatientServiceResponse();
        response.SUCCESSFUL = false;
        String message = "Invalid Input :Please provide mandotory fields";
        if (patientId == null || patientId == 0)
            response.setMessage(message);
        if (clinicId == null || clinicId == 0)
            response.setMessage(message);
        else
            response.SUCCESSFUL = true;
        return response;
    }

    //added by Bahubali on 13-11-2018
    //method to update mrn and isRegistered field in patient_all_details table
    private void updateMRNAndIsRegisteredInPatientAllDetails(Long patientId, String MRN, boolean isRegistered) throws SerialException {

        PatientAllDetailsDTO patientAllDetailsDTO=new PatientAllDetailsDTO();
        patientAllDetailsDTO.setPatientId(patientId);
        patientAllDetailsDTO.setPatientMRN(MRN);
        patientAllDetailsDTO.setPatientRegistered(isRegistered);
        patientAllDetailsDTO.setContext(ErxConstants.PATIENT_CONVERT_NON_REGISTERED_TO_REGISTERED);
        SavePatientAllDetailsServiceRequest savePatientAllDetailsServiceRequest=new SavePatientAllDetailsServiceRequest
                (patientAllDetailsDTO);
        savePatientAllDetailsService.execute(savePatientAllDetailsServiceRequest);
    }
}
