package com.erx.microservice.patientmanagement.service.patient.getpatientipdetailsbypatientid;

/*
 * created by Brighty on 30-05-2018
 * */

import com.erx.microservice.patientmanagement.domain.IpAdmission;
import com.erx.microservice.patientmanagement.domain.IpAdmissionBedTransfer;
import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.domain.UserDetail;
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

@Service("getPatientIPDetailsByPatientIdService")
public class GetPatientIPDetailsByPatientIdServiceImpl implements GetPatientIPDetailsByPatientIdService {

    private static Logger log = LoggerFactory.getLogger(GetPatientIPDetailsByPatientIdServiceImpl.class);

    @Autowired
    private IpAdmissionRepository ipAdmissionRepository;

    @Autowired
    private IpAdmissionBedTransferRepository ipAdmissionBedTransferRepository;

    @Override
    public GetPatientIPDetailsByPatientIdServiceResponse execute(GetPatientIPDetailsByPatientIdServiceRequest request)
            throws ServiceException {
        GetPatientIPDetailsByPatientIdServiceResponse response;
        log.debug("Call to get All patients admitted in clinicLocation " + request.getPatientId());
        List<PatientDetailDTO> patientDetailDTOs = new ArrayList<>();
        UserDetail userDetail = null;
        try {
            //retrieve all the Patients by clinicLocation who are not discharged
            IpAdmission ipAdmission = ipAdmissionRepository.findByPatientId(request.getPatientId());
            //for (Object[] ipAdmissionDetail : patients) {
            //Object patientDetails = ipAdmissionDetail.get(0);
            PatientDetailDTO patientDetailDTO = null;
            if (ipAdmission != null) {
                Patient patient = ipAdmission.getPatient();
                patientDetailDTO = new PatientDetailDTO();
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
                //ipAdmission = ipAdmissionDetail;
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
                //set doctor
                if (ipAdmission.getUserDetail() != null)
                    userDetail = ipAdmission.getUserDetail();
                if (userDetail.isDoctor() == true)
                    patientDetailDTO.setDoctor(userDetail.getUserStaff().getFirstName());
            }

            //create response
            response = createResponse(patientDetailDTO, true, PatientConstants.GET_PATIENT_IP_DETAILS_BY_PATIENT_ID_SUCCESS);
            log.debug("GetAllPatientIPDetailsByPatientIdServiceImpl ==> SUCCESS");
        } catch (Exception e) {
            response = createResponse(null, false,
                    PatientConstants.GET_PATIENT_IP_DETAILS_BY_PATIENT_ID_FAILURE + " " + e.getMessage());
            log.error("GetAllPatientIPDetailsByPatientIdServiceImpl ==> FAILURE : " + e.getMessage());
        }
        return response;
    }

    private GetPatientIPDetailsByPatientIdServiceResponse createResponse(PatientDetailDTO patientDetailDTO,
                                                                         boolean successful, String message) {
        GetPatientIPDetailsByPatientIdServiceResponse response = new GetPatientIPDetailsByPatientIdServiceResponse();
        response.setPatientDetailDTO(patientDetailDTO);
        response.setMessage(message);
        response.SUCCESSFUL = successful;
        return response;
    }

    // method to set BedTransfer details to DTO
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
