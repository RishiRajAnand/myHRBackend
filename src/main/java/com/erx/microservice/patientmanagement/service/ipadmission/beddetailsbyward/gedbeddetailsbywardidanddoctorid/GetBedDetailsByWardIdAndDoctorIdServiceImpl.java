package com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.gedbeddetailsbywardidanddoctorid;

/*
 * created by Brighty on 28-04-2018.
 * */

import com.erx.microservice.patientmanagement.domain.BedConfigurationMaster;
import com.erx.microservice.patientmanagement.domain.IpAdmission;
import com.erx.microservice.patientmanagement.repository.BedConfigurationMasterRepository;
import com.erx.microservice.patientmanagement.repository.IpAdmissionRepository;
import com.erx.microservice.patientmanagement.service.dto.BedConfigurationDTO;
import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.GetBedDetailsByWardIdServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.GetBedDetailsByWardIdServiceResponse;
import com.erx.microservice.patientmanagement.service.mapper.BedConfigurationMasterMapper;
import com.erx.microservice.patientmanagement.util.PatientManagementConstants;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getBedDetailsByWardIdAndDoctorIdService")
public class GetBedDetailsByWardIdAndDoctorIdServiceImpl implements GetBedDetailsByWardIdAndDoctorIdService {

    private final Logger log = LoggerFactory.getLogger(GetBedDetailsByWardIdAndDoctorIdServiceImpl.class);

    @Autowired
    private BedConfigurationMasterRepository bedConfigurationMasterRepository;

    @Autowired
    private BedConfigurationMasterMapper bedConfigurationMasterMapper;

    @Autowired
    private IpAdmissionRepository ipAdmissionRepository;

    @Override
    public GetBedDetailsByWardIdServiceResponse execute(GetBedDetailsByWardIdServiceRequest request) throws ServiceException {

        GetBedDetailsByWardIdServiceResponse response = null;
        List<IpAdmission> ipAdmissionList = null;
        List<BedConfigurationDTO> bedConfigurationDTOs = new ArrayList<>();
        try {
            log.debug("GetBedDetailsByWardIdAndDoctorIdServiceImpl ==> Call to get bed details for the given ward");
            //check for mandatory fields
            if (request.getDoctorId() != null) {
                if (request.getWardId() != null) { //if wardId is not null
                    //retrieve the bed details by wardId,dayCare and doctorId
                    ipAdmissionList = ipAdmissionRepository.getIpAdmissionByWardAndDaycareAndDoctor
                            (request.getWardId(), request.isDaycare(), request.getDoctorId());
                    if (!ipAdmissionList.isEmpty()) { //if ipAdmissionList is not empty
                        bedConfigurationDTOs = setBedDetails(ipAdmissionList, bedConfigurationDTOs); //call method to set bed details
                        //call method to construct response
                        return constructResponse(true, PatientManagementConstants.BED_DETAILS_SUCCESS, bedConfigurationDTOs);
                    } else // when ipAdmissionList is empty construct responsePatientManagementConstants.BED_DETAILS_SUCCESS
                        return constructResponse(true, PatientManagementConstants.BED_DETAILS_NOT_FOUND_WARD + request.getWardId()
                                + " and doctorId " + request.getDoctorId(), bedConfigurationDTOs);
                } else { //when wardId is null    //retrieve the bed details by dayCare and doctorId
                    ipAdmissionList = ipAdmissionRepository.getIpAdmissionByDaycareAndDoctor(request.isDaycare(), request.getDoctorId());
                    if (!ipAdmissionList.isEmpty()) { //if ipAdmissionList is not empty
                        bedConfigurationDTOs = setBedDetails(ipAdmissionList, bedConfigurationDTOs);//call method to set bed details
                        //call method to construct response
                        return constructResponse(true, PatientManagementConstants.BED_DETAILS_SUCCESS, bedConfigurationDTOs);
                    } else // when ipAdmissionList is empty construct response
                        return constructResponse(true, PatientManagementConstants.BED_DETAILS_NOT_FOUND_DOCTOR
                                + request.getDoctorId(), bedConfigurationDTOs);
                }
            } else
                return constructResponse(false, PatientManagementConstants.INVALID_INPUT, bedConfigurationDTOs);

        } catch (Exception e) {
            response = constructResponse(false, PatientManagementConstants.BED_DETAILS_FAILURE
                    + e.getMessage(), bedConfigurationDTOs);
            log.error(response.getMessage());
        }
        return response;
    }

    //method to set bed details
    private List<BedConfigurationDTO> setBedDetails(List<IpAdmission> ipAdmissions, List<BedConfigurationDTO> bedConfigurationDTOs)
            throws ServiceException {
        //iterate list
        for (IpAdmission ipAdmission : ipAdmissions) {
            BedConfigurationMaster bedConfigurationMaster = ipAdmission.getBedMaster();
            BedConfigurationDTO bedConfigurationDTO = new BedConfigurationDTO();
            //convert bedConfigurationMaster to bedConfigurationDTO
            bedConfigurationDTO = bedConfigurationMasterMapper.bedConfigurationMasterToBedConfigurationDTO(bedConfigurationMaster);
            //set the values
            bedConfigurationDTO.setBedTypeMasterId(bedConfigurationMaster.getBedTypeMaster().getId());
            bedConfigurationDTO.setBedTypeName(bedConfigurationMaster.getBedTypeMaster().getBedTypeName());
            bedConfigurationDTO.setWardMasterId(bedConfigurationMaster.getWardMaster().getId());
            bedConfigurationDTO.setWardName(bedConfigurationMaster.getWardMaster().getWardName());
            bedConfigurationDTO.setRoomConfigurationMasterId(bedConfigurationMaster.getRoomConfigurationMaster().getId());
            bedConfigurationDTO.setRoomNumber(bedConfigurationMaster.getRoomConfigurationMaster().getRoomNumber());
            if (ipAdmission.getIpAdmissionCaseMapping() != null)
                bedConfigurationDTO.setCmMasterId(ipAdmission.getIpAdmissionCaseMapping().getCmMaster().getId());

            if (ipAdmission.getPatient() != null) { //set patient details
                bedConfigurationDTO.setPatientId(ipAdmission.getPatient().getId());
                bedConfigurationDTO.setPatientMrn(ipAdmission.getPatient().getPatientIdExternal());
                bedConfigurationDTO.setPatientName(ipAdmission.getPatient().getPatientName());
            }
            if (ipAdmission.getUserDetail() != null) { //set user details
                if (ipAdmission.getUserDetail().getUserStaff() != null) {
                    bedConfigurationDTO.setDoctorName(ipAdmission.getUserDetail().getUserStaff().getFirstName() + " " +
                            ipAdmission.getUserDetail().getUserStaff().getLastName());
                }
            }
            if (!ipAdmission.isDayCare())
                bedConfigurationDTO.setIpAdmissionNumber(ipAdmission.getIpAdmissionNumber());
            else
                bedConfigurationDTO.setDaycareNumber(ipAdmission.getDayCareAdmissionNumber());
            bedConfigurationDTOs.add(bedConfigurationDTO);   //add to list
        }
        return bedConfigurationDTOs;
    }

    //method to construct response
    private GetBedDetailsByWardIdServiceResponse constructResponse(boolean isSuccess, String message,
                                                                   List<BedConfigurationDTO> bedConfigurationDTOs) throws ServiceException {
        GetBedDetailsByWardIdServiceResponse response = null;
        if (isSuccess) //if isSuccess true then set bedConfigurationDTOs
            response = new GetBedDetailsByWardIdServiceResponse(bedConfigurationDTOs);
        else
            response = new GetBedDetailsByWardIdServiceResponse();
        response.SUCCESSFUL = isSuccess;
        response.setMessage(message);
        log.debug("GetBedDetailsByWardIdAndDoctorIdServiceImpl ==>" + message);
        return response;
    }
}


/*
    @Override
    public GetBedDetailsByWardIdServiceResponse execute(GetBedDetailsByWardIdServiceRequest request) throws ServiceException {

        GetBedDetailsByWardIdServiceResponse response = null;
        List<BedConfigurationDTO> bedConfigurationDTOs = new ArrayList<>();
        List<IpAdmission> ipAdmissions = null;
        try {
            log.debug("GetBedDetailsByWardIdAndDoctorIdServiceImpl ==> Call to get bed details for the given ward");
            if (request.getWardId() != null && request.getDoctorId() != null) {
                //retrieve the objects
                ipAdmissions = ipAdmissionRepository.getIpAdmissionByWardAndDaycareAndDoctor
                        (request.getWardId(), request.isDaycare(), request.getDoctorId());
                //convert domain to DTO
                for (IpAdmission ipAdmission : ipAdmissions) {
                    BedConfigurationMaster bedConfigurationMaster = ipAdmission.getBedMaster();
                    BedConfigurationDTO bedConfigurationDTO = new BedConfigurationDTO();
                    //convert bedConfigurationMaster to bedConfigurationDTO
                    bedConfigurationDTO = bedConfigurationMasterMapper.bedConfigurationMasterToBedConfigurationDTO(bedConfigurationMaster);
                    //set the values
                    bedConfigurationDTO.setBedTypeMasterId(bedConfigurationMaster.getBedTypeMaster().getId());
                    bedConfigurationDTO.setBedTypeName(bedConfigurationMaster.getBedTypeMaster().getBedTypeName());
                    bedConfigurationDTO.setWardMasterId(bedConfigurationMaster.getWardMaster().getId());
                    bedConfigurationDTO.setWardName(bedConfigurationMaster.getWardMaster().getWardName());
                    bedConfigurationDTO.setRoomConfigurationMasterId(bedConfigurationMaster.getRoomConfigurationMaster().getId());
                    bedConfigurationDTO.setRoomNumber(bedConfigurationMaster.getRoomConfigurationMaster().getRoomNumber());
                    if (ipAdmission.getIpAdmissionCaseMapping() != null)
                        bedConfigurationDTO.setCmMasterId(ipAdmission.getIpAdmissionCaseMapping().getCmMaster().getId());

                    if (ipAdmission.getPatient() != null) {
                        bedConfigurationDTO.setPatientId(ipAdmission.getPatient().getId());
                        bedConfigurationDTO.setPatientMrn(ipAdmission.getPatient().getPatientIdExternal());
                        bedConfigurationDTO.setPatientName(ipAdmission.getPatient().getPatientName());
                    }
                    if (ipAdmission.getUserDetail() != null) {
                        if (ipAdmission.getUserDetail().getUserStaff() != null) {
                            bedConfigurationDTO.setDoctorName(ipAdmission.getUserDetail().getUserStaff().getFirstName() + " " +
                                    ipAdmission.getUserDetail().getUserStaff().getLastName());
                        }
                    }
                    if (!ipAdmission.isDayCare())
                        bedConfigurationDTO.setIpAdmissionNumber(ipAdmission.getIpAdmissionNumber());
                    else
                        bedConfigurationDTO.setDaycareNumber(ipAdmission.getDayCareAdmissionNumber());

                    //add to list
                    bedConfigurationDTOs.add(bedConfigurationDTO);
                }
                response = new GetBedDetailsByWardIdServiceResponse(bedConfigurationDTOs);
                response.SUCCESSFUL = true;
                response.setMessage("Retrieved Bed details Successfully");
                log.debug("GetBedDetailsByWardIdAndDoctorIdServiceImpl ==> Retrieved BedConfigurationMasters Successfully");
                return response;
            }
            response = new GetBedDetailsByWardIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Invalid input");
            log.debug("GetBedDetailsByWardIdAndDoctorIdServiceImpl ==> Invalid input");
        } catch (Exception e) {
            response = new GetBedDetailsByWardIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Bed details");
            log.error("GetBedDetailsByWardIdAndDoctorIdServiceImpl ==> Failed to retrieve BedConfigurationMasters " + e.getMessage());
        }
        return response;
    }
}
*/
