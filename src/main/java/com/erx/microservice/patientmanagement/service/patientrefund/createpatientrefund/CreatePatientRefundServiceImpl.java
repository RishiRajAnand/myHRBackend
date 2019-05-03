package com.erx.microservice.patientmanagement.service.patientrefund.createpatientrefund;

/*
* created by Brighty on 27-11-17
* */

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.*;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDClinicDTO;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDDTO;
import com.erx.microservice.patientmanagement.service.dto.PatientRefundDTO;
import com.erx.microservice.patientmanagement.service.dto.PatientRefundDetailDTO;
import com.erx.microservice.patientmanagement.service.mapper.PatientRefundMapper;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Service("createRefundService")
public class CreatePatientRefundServiceImpl implements CreatePatientRefundService {

    private final Logger log = LoggerFactory.getLogger(CreatePatientRefundServiceImpl.class);

    @Autowired
    private PatientRefundRepository patientRefundRepository;

    @Autowired
    private PatientRefundMapper patientRefundMapper;

    @Autowired
    private LookupValueRepository lookupValueRepository;

    /*@Autowired
    private DepositRepository depositRepository;*/

    @Autowired
    private ClinicLocationRepository clinicLocationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    @Autowired
    private VisitTypeMasterRepository visitTypeMasterRepository;

    @Autowired
    private UserStaffRepository userStaffRepository;


    @Override
    public CreatePatientRefundServiceResponse execute(CreatePatientRefundServiceRequest request) throws ServiceException {

        CreatePatientRefundServiceResponse response = null;
        PatientRefund refund = null;
        PatientRefund savedRefund = null;
        PatientRefundDTO patientRefundDTO = null;
        PatientRefundDetailDTO savedPatientRefundDetailDTO = null;
        ClinicLocation clinicLocation = null;
        VisitTypeMaster accountName = null;
        Patient patient = null;
        LookupValue refundType = null;
        UserStaff userStaff = null;
        double totalFromAccountAmount = 0;
        try {
            log.debug("Call to save Refund");
            //retrieve the object from request
            patientRefundDTO = request.getPatientRefundDTO();
            //convert DTO to domain
            refund = patientRefundMapper.patientRefundDTOToPatientRefund(patientRefundDTO);
            //set clinicLocation
            clinicLocation = clinicLocationRepository.findOne(patientRefundDTO.getClinicLocationId());
            refund.setClinicLocation(clinicLocation);
            //set patient
            patient = patientRepository.findOne(patientRefundDTO.getPatientId());
            refund.setPatient(patient);
            //set RefundType
            refundType = lookupValueRepository.findOne(patientRefundDTO.getRefundTypeId());
            refund.setRefundType(refundType);
            //set the user
            if (refund.getId() == null) {
                refund.setCreatedBy(patientRefundDTO.getUserId());
            }
            refund.setUpdatedBy(patientRefundDTO.getUserId());
            userStaff = userStaffRepository.findOne(patientRefundDTO.getUserId());
            //set accountName
            if (patientRefundDTO.getDepositAccountId() != 0) {
                accountName = visitTypeMasterRepository.findOne(patientRefundDTO.getDepositAccountId());
                refund.setAccountName(accountName);
            }

            //retrieve and set clinicId,CurrentTblName and setCurrentClmName to DTO
            Long clinicId = clinicLocation.getClinic().getId();
            GenerateUniqueIDDTO generateUniqueIDDTO = new GenerateUniqueIDDTO();
            GenerateUniqueIDClinicDTO generateUniqueIDClinicDTO = new GenerateUniqueIDClinicDTO();
            generateUniqueIDDTO.setCurrentTableName(ErxConstants.RM_CURRENT_TABLE_NAME);
            generateUniqueIDDTO.setCurrentColumnName(ErxConstants.RM_CURRENT_COLUMN_NAME);
            generateUniqueIDClinicDTO.setId(clinicId);
            generateUniqueIDDTO.setGenerateUniqueIDClinicDTO(generateUniqueIDClinicDTO);
            //call generateUniqueID
            String generatedDepartmentId = serviceGateway.generateUniqueID(generateUniqueIDDTO);
            //SET THE UNIQUE ID
            if (generatedDepartmentId != null)
                refund.setRefundNumber(generatedDepartmentId);
            refund.setRefundDate(LocalDateTime.now());

            //save the object
            savedRefund = patientRefundRepository.save(refund);

            //*********** ACCOUNT MANAGEMENT *****************
            //calling to save utilizePatientDepositValue
            serviceGateway.utilizePatientDepositValue( patient.getId(),accountName.getId(),patientRefundDTO.getRefundableAmount());
            //*********** ACCOUNT MANAGEMENT *****************

            //convert domain to DTO
            savedPatientRefundDetailDTO = patientRefundMapper.patientRefundToPatientRefundDetailDTO(savedRefund);
            //set the values
            savedPatientRefundDetailDTO.setClinicLocationId(clinicLocation.getId());
            savedPatientRefundDetailDTO.setPatientId(patient.getId());
            savedPatientRefundDetailDTO.setPatientName(patient.getPatientName());
            savedPatientRefundDetailDTO.setMRN(patient.getPatientIdExternal());
            LocalDateTime now = LocalDateTime.now();
            int age = Period.between(patient.getDateOfBirth()/*.toLocalDate()*/, now.toLocalDate()).getYears();
            savedPatientRefundDetailDTO.setAge(age);
            savedPatientRefundDetailDTO.setGender(patient.getGender());
            savedPatientRefundDetailDTO.setMobileNumber(patient.getMobileNumber());
            savedPatientRefundDetailDTO.setRefundTypeId(refundType.getId());
            savedPatientRefundDetailDTO.setRefundTypeName(refundType.getName());
            savedPatientRefundDetailDTO.setUserId(userStaff.getId());
            savedPatientRefundDetailDTO.setUserName(userStaff.getFirstName() + " " + userStaff.getLastName());
            if (patientRefundDTO.getDepositAccountId() != 0) {
                savedPatientRefundDetailDTO.setDepositAccountId(accountName.getId());
                savedPatientRefundDetailDTO.setDepositAccountName(accountName.getVisitType());
            }

            //create response
            response = new CreatePatientRefundServiceResponse(savedPatientRefundDetailDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Saved Refund Successfully with patientRefund number " + savedPatientRefundDetailDTO.getRefundNumber());
            log.info("Saved Refund Successfully");

        } catch (Exception e) {
            response = new CreatePatientRefundServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to Save Refund");
            log.error("Failed to Save Refund");
        }
        return response;
    }
}
