package com.erx.microservice.patientmanagement.service.patient.savenonregisteredpatients;
/*
 * created by Manisha on 16-07-2018
 * */
import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.domain.PatientAdditionalDetail;
import com.erx.microservice.patientmanagement.domain.PatientType;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.PatientAdditionalDetailRepository;
import com.erx.microservice.patientmanagement.repository.PatientRepository;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDClinicDTO;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDDTO;
import com.erx.microservice.patientmanagement.service.dto.NonRegisteredPatientDTO;
import com.erx.microservice.patientmanagement.service.mapper.NonRegisteredPatientMapper;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveNonRegisteredPatientServiceImpl implements SaveNonRegisteredPatientService {
    private static Logger log = LoggerFactory.getLogger(SaveNonRegisteredPatientServiceImpl.class);
    @Autowired
    private NonRegisteredPatientMapper nonRegisteredPatientMapper;
    @Autowired
    private ServiceGateway serviceGateway;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientAdditionalDetailRepository patientAdditionalDetailRepository;

    @Override
    public SaveNonRegisteredPatientServiceResponse execute(SaveNonRegisteredPatientServiceRequest request) {
        SaveNonRegisteredPatientServiceResponse response = null;
        try {
            //retrieve request
            NonRegisteredPatientDTO nonRegisteredPatientRequestDTO = request.getNonRegisteredPatientDTO();
            //validate input
            response = validateInput(nonRegisteredPatientRequestDTO);
            if (!response.SUCCESSFUL) {
                log.debug("Invalid Input");
                return response;
            }
            //mapper to convert nonRegisteredPatientDto to patient entity
            Patient patient = nonRegisteredPatientMapper.nonRegisteredPatientDTOTOPatient(nonRegisteredPatientRequestDTO);
            //get patient id external or mrn for patient
            GenerateUniqueIDDTO generateUniqueIDDTO = getGenerateUniqueIDDTO(nonRegisteredPatientRequestDTO);
            String generatedUniqueId = serviceGateway.generateUniqueID(generateUniqueIDDTO);
            if (generatedUniqueId == null)
                throw new Exception("failed to generate unique id for non registered patients");
            patient.setPatientIdExternal(generatedUniqueId);
            //saving non registered patient
            Patient savedPatient = patientRepository.save(patient);
            //saving patient additional details
            PatientAdditionalDetail patientAdditionalDetail = getPatientDetails(nonRegisteredPatientRequestDTO, savedPatient);
            patientAdditionalDetail.setPatientType(patientAdditionalDetail.getPatientType());
            patientAdditionalDetailRepository.save(patientAdditionalDetail);
            //mapper to convert nonRegisteredPatientDto to patient entity
            NonRegisteredPatientDTO nonRegisteredPatientDTO = nonRegisteredPatientMapper.patientToNonRegisteredPatientDTO(savedPatient);
            //create response
            response = new SaveNonRegisteredPatientServiceResponse();
            response.SUCCESSFUL = true;
            response.setPatientId(nonRegisteredPatientDTO.getPatientId());
            response.setMessage("Non registered patients save successfully");
            log.debug("Non registered patients save successfully");
        } catch (Exception e) {
            response = new SaveNonRegisteredPatientServiceResponse();
            response.SUCCESSFUL = true;
            response.setMessage("Failed to save non registered patient due to Exception ==>" + e.getMessage());
            log.debug("Failed to save non registered patient");
        }
        return response;
    }

    private PatientAdditionalDetail getPatientDetails(NonRegisteredPatientDTO nonRegisteredPatientDTO, Patient savedPatient) {
        PatientAdditionalDetail patientAdditionalDetail = new PatientAdditionalDetail();
        patientAdditionalDetail.setPatient(savedPatient);
        PatientType patientType = new PatientType();
        patientType.setId(nonRegisteredPatientDTO.getPatientTypeId());
        patientAdditionalDetail.setPatientType(patientType);
        return patientAdditionalDetail;
    }

    private GenerateUniqueIDDTO getGenerateUniqueIDDTO(NonRegisteredPatientDTO nonRegisteredPatientDTO) {
        GenerateUniqueIDDTO generateUniqueIDDTO = new GenerateUniqueIDDTO();
        generateUniqueIDDTO.setClinicLocationId(nonRegisteredPatientDTO.getClinicLocationId());
        generateUniqueIDDTO.setCurrentColumnName(ErxConstants.PATIENT_ID_EXTERNAL_COLUMN_NAME);
        generateUniqueIDDTO.setCurrentTableName(ErxConstants.PATIENT_CURRENT_TABLE_NAME);
        GenerateUniqueIDClinicDTO generateUniqueIDClinicDTO = new GenerateUniqueIDClinicDTO();
        generateUniqueIDClinicDTO.setId(nonRegisteredPatientDTO.getClinicId());
        generateUniqueIDDTO.setGenerateUniqueIDClinicDTO(generateUniqueIDClinicDTO);
        return generateUniqueIDDTO;
    }

    private SaveNonRegisteredPatientServiceResponse validateInput(NonRegisteredPatientDTO nonRegisteredPatientDTO) {
        SaveNonRegisteredPatientServiceResponse response = new SaveNonRegisteredPatientServiceResponse();
        response.SUCCESSFUL = false;
        String message = "Invalid input.Please provide mandatory fields";
        if (nonRegisteredPatientDTO.getName() == null || nonRegisteredPatientDTO.getName().isEmpty())
            response.setMessage(message);
        else if (nonRegisteredPatientDTO.getMobileNumber() == null || nonRegisteredPatientDTO.getMobileNumber().isEmpty())
            response.setMessage(message);
        else if (nonRegisteredPatientDTO.getGender() == null || nonRegisteredPatientDTO.getGender().isEmpty())
            response.setMessage(message);
        else if (nonRegisteredPatientDTO.getDateOfBirth() == null) response.setMessage(message);
        else {
            response.SUCCESSFUL = true;
            return response;
        }
        return response;
    }
}
