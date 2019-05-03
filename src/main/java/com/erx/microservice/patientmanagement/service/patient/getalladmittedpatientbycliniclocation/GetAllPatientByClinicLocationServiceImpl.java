package com.erx.microservice.patientmanagement.service.patient.getalladmittedpatientbycliniclocation;

/*
* created by Brighty on 30-05-2018
* */

import com.erx.microservice.patientmanagement.domain.IpAdmission;
import com.erx.microservice.patientmanagement.domain.IpAdmissionBedTransfer;
import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.IpAdmissionBedTransferRepository;
import com.erx.microservice.patientmanagement.repository.IpAdmissionRepository;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionBedTransferDetailsDTO;
import com.erx.microservice.patientmanagement.service.dto.PatientDetailDTO;
import com.erx.microservice.patientmanagement.service.util.PatientConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getAllAdmittedPatientByClinicLocationService")
public class GetAllPatientByClinicLocationServiceImpl implements GetAllAdmittedPatientByClinicLocationService {

    private static Logger log = LoggerFactory.getLogger(GetAllPatientByClinicLocationServiceImpl.class);

    @Autowired
    private IpAdmissionRepository ipAdmissionRepository;

    @Autowired
    private IpAdmissionBedTransferRepository ipAdmissionBedTransferRepository;

    @Override
    public GetAllAdmittedPatientByClinicLocationServiceResponse execute(GetAllAdmittedPatientByClinicLocationServiceRequest request)
            throws ServiceException {
        GetAllAdmittedPatientByClinicLocationServiceResponse response;
        log.debug("Call to get All patients admitted in clinicLocation " + request.getClinicLocationId());
        List<Object[]> patients;
        List<PatientDetailDTO> patientDetailDTOs = new ArrayList<>();
        try {
            //retrieve all the Patients by clinicLocation who are not discharged
            patients = ipAdmissionRepository.findByClinicLocation(request.getClinicLocationId());
            for (Object[] ipAdmissionDetail : patients) {
                Patient patient = (Patient) ipAdmissionDetail[0];
                IpAdmission ipAdmission = null;
                PatientDetailDTO patientDetailDTO = new PatientDetailDTO();
                patientDetailDTO.setPatientId(patient.getId());
                patientDetailDTO.setClinicId(patient.getClinic().getId());
                if (patient.getClinicLocation() != null)
                    patientDetailDTO.setClinicLocationId(patient.getClinicLocation().getId());
                if (patient.getPatientAdditionalDetail() != null) {
                    if (patient.getPatientAdditionalDetail().getPatientType() != null) {
                        patientDetailDTO.setPatientType(patient.getPatientAdditionalDetail().getPatientType().getPatientTypeName());
                        patientDetailDTO.setPatientTypeId(patient.getPatientAdditionalDetail().getPatientType().getId());
                    }
                }
                if (ipAdmissionDetail[1] != null) {
                    ipAdmission = (IpAdmission) ipAdmissionDetail[1];
                    patientDetailDTO.setiPAdmissionId(ipAdmission.getId());
                    patientDetailDTO.setBedId(ipAdmission.getBedMaster().getId());
                    patientDetailDTO.setDateOfAdmission(ipAdmission.getAdmissionOn());
                    if (ipAdmission.getIpAdmissionNumber() != null)
                        patientDetailDTO.setVisitId(ipAdmission.getIpAdmissionNumber());
                    if (ipAdmission.getDayCareAdmissionNumber() != null)
                        patientDetailDTO.setVisitId(ipAdmission.getDayCareAdmissionNumber());
                    //patientDetailDTO.setVisitType();
                    patientDetailDTO.setVisitTypeId(ipAdmission.getVisitTypeMasterId());
                    patientDetailDTO.setBedTypeId(ipAdmission.getBedMaster().getBedTypeMaster().getId());
                    patientDetailDTO.setDischargedOn(ipAdmission.getDischargedOn());
                    //patientDetailDTO.setDepartmentId();

                    //retrieve IpAdmissionBedTransfer Details
                    List<IpAdmissionBedTransfer> ipAdmissionBedTransfers = ipAdmissionBedTransferRepository
                            .getBedTransferDetails(ipAdmission.getId());

                    //set IpAdmissionBedTransferDTO
                    if (!ipAdmissionBedTransfers.isEmpty()) {

                        List<IpAdmissionBedTransferDetailsDTO> ipAdmissionBedTransferDTOs =
                                setIpAdmissionBedTransferDTO(ipAdmissionBedTransfers);

                        patientDetailDTO.setIpAdmissionBedTransferDetailsDTOs(ipAdmissionBedTransferDTOs);
                    }
                }
                //add to List
                patientDetailDTOs.add(patientDetailDTO);
            }
            //create response
            response = createResponse(patientDetailDTOs, true, PatientConstants.GET_ALL_PATIENT_BY_CLINIC_LOCATION_SUCCESS);
            log.debug("GetAllPatientIPDetailsByPatientIdServiceImpl ==> SUCCESS");
        } catch (Exception e) {
            response = createResponse(patientDetailDTOs, false,
                    PatientConstants.GET_ALL_PATIENT_BY_CLINIC_LOCATION_FAILURE + " " + e.getMessage());
            log.error("GetAllPatientIPDetailsByPatientIdServiceImpl ==> FAILURE : " + e.getMessage());
        }
        return response;
    }

    private GetAllAdmittedPatientByClinicLocationServiceResponse createResponse(List<PatientDetailDTO> patientDetailDTOs,
                                                                                boolean successful, String message) {
        GetAllAdmittedPatientByClinicLocationServiceResponse response = new GetAllAdmittedPatientByClinicLocationServiceResponse();
        response.setPatientDetailDTOs(patientDetailDTOs);
        response.setMessage(message);
        response.SUCCESSFUL = successful;
        return response;
    }

    private List<IpAdmissionBedTransferDetailsDTO> setIpAdmissionBedTransferDTO(List<IpAdmissionBedTransfer> ipAdmissionBedTransfers)
            throws Exception {

        List<IpAdmissionBedTransferDetailsDTO> ipAdmissionBedTransferDTOs = new ArrayList<>();
        for (IpAdmissionBedTransfer ipAdmissionBedTransfer : ipAdmissionBedTransfers) {
            IpAdmissionBedTransferDetailsDTO ipAdmissionBedTransferDTO = new IpAdmissionBedTransferDetailsDTO();
            ipAdmissionBedTransferDTO.setPatientId(ipAdmissionBedTransfer.getIpAdmission().getPatient().getId());
            ipAdmissionBedTransferDTO.setIpAdmissionID(ipAdmissionBedTransfer.getIpAdmission().getId());
            ipAdmissionBedTransferDTO.setDateOfAdmission(ipAdmissionBedTransfer.getIpAdmission().getAdmissionOn());
            ipAdmissionBedTransferDTO.setFromBedID(ipAdmissionBedTransfer.getBedTransferredFrom().getId());
            ipAdmissionBedTransferDTO.setFromBed(ipAdmissionBedTransfer.getBedTransferredFrom().getBedNumber());
            ipAdmissionBedTransferDTO.setToBedID(ipAdmissionBedTransfer.getBedTransferredTo().getId());
            ipAdmissionBedTransferDTO.setToBed(ipAdmissionBedTransfer.getBedTransferredTo().getBedNumber());
            ipAdmissionBedTransferDTO.setIpAdmissionNumber(ipAdmissionBedTransfer.getIpAdmission().getIpAdmissionNumber());
            ipAdmissionBedTransferDTO.setTransferDate(ipAdmissionBedTransfer.getCreatedOn());
            ipAdmissionBedTransferDTO.setTransferredBy(String.valueOf(ipAdmissionBedTransfer.getCreatedBy()));
            //ipAdmissionBedTransferDTO.setUserDepartmentId();

            ipAdmissionBedTransferDTOs.add(ipAdmissionBedTransferDTO);
        }
        return ipAdmissionBedTransferDTOs;
    }
}
