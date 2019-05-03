package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapyscheduledetailbytherapyid;

/*
* created by Raushan on 29-10-2018
* */

import com.erx.microservice.patientmanagement.domain.IpAdmission;
import com.erx.microservice.patientmanagement.domain.UserStaff;
import com.erx.microservice.patientmanagement.domain.therapymanagement.*;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.IpAdmissionRepository;
import com.erx.microservice.patientmanagement.repository.UserStaffRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.*;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.*;
import org.hibernate.service.spi.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GetTherapyScheduleDetailByTherapyIdServiceImpl implements GetTherapyScheduleDetailByTherapyIdService {

    private final Logger log = LoggerFactory.getLogger(GetTherapyScheduleDetailByTherapyIdServiceImpl.class);

    @Autowired
    private TherapyMasterRepository therapyMasterRepository;

    @Autowired
    private TherapyRoomDetailsRepository therapyRoomDetailsRepository;

    @Autowired
    private TherapyMasterRoomDetailRepository therapyMasterRoomDetailRepository;

    @Autowired
    private TherapyMasterTherapistDetailRepository therapyMasterTherapistDetailRepository;

    @Autowired
    private UserStaffRepository userStaffRepository;

    @Autowired
    private IpAdmissionRepository ipAdmissionRepository;

    @Override
    public GetTherapyScheduleDetailByTherapyIdServiceResponse execute(GetTherapyScheduleDetailByTherapyIdServiceRequest request) throws ServiceException {
        GetTherapyScheduleDetailByTherapyIdServiceResponse response = null;
        TherapyMaster therapyMaster = null;
        TherapyScheduleDetailDTO therapyScheduleDetailDTO = null;
        List<TherapyMasterRoomDetailGetDTO> therapyMasterRoomDetailGetDTOs = null;
        List<TherapyMasterTherapistDetailGetDTO> therapyMasterTherapistDetailGetDTOs = null;
        try {
            log.debug("Call to retrieve all therapy schedule detail for the clinic and therapy master id" + request.getClinicId(), request.getTherapyMasterId());
            //retrieve the therapy master details
            if (request.getClinicId() != null && request.getTherapyMasterId() != null) {
                therapyMaster = therapyMasterRepository.
                        findTherapyAndTherapistDetailByClinicAndTherapyMasterId(request.getClinicId(), request.getTherapyMasterId());
                if (therapyMaster != null)
                    therapyScheduleDetailDTO = new TherapyScheduleDetailDTO();
                therapyScheduleDetailDTO.setId(therapyMaster.getId());
                therapyScheduleDetailDTO.setDuration(therapyMaster.getDuration());
                therapyScheduleDetailDTO.setNumTherapist(therapyMaster.getNumTherapist());
                //frame therapy master room detail dto
                therapyMasterRoomDetailGetDTOs = frameTherapyMasterRoomDetailGetDTO(therapyMaster);
                if (therapyMasterRoomDetailGetDTOs != null) {
                    therapyScheduleDetailDTO.setTherapyMasterRoomDetailGetDTOs(therapyMasterRoomDetailGetDTOs);
                }
                //frame therapy master therapist detail dto
                therapyMasterTherapistDetailGetDTOs = frameTherapyMasterTherapistDetailGetDTO(therapyMaster);
                if (therapyMasterTherapistDetailGetDTOs != null) {
                    therapyScheduleDetailDTO.setTherapyMasterTherapistDetailGetDTOs(therapyMasterTherapistDetailGetDTOs);
                }
                //setting allocatedBedId and name
                List<Object[]> ipAdmissions= ipAdmissionRepository.findAllocatedBedIdAndNameByPatientId(request.getPatientId());
                if(!ipAdmissions.isEmpty()) {
                    for (Object[] ipAdmission : ipAdmissions) {
                        therapyScheduleDetailDTO.setAllocatedBedId((Long) ipAdmission[0]);
                        therapyScheduleDetailDTO.setAllocatedBedName((String) ipAdmission[1]);
                    }
                }
            }
            response = new GetTherapyScheduleDetailByTherapyIdServiceResponse(therapyScheduleDetailDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved therapy schedule details by Therapy id Successfully");
            log.debug("Retrieved therapy master details by Therapy id Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve therapy schedule details by Therapy id");
            log.error("Failed to retrieve therapy schedule details by Therapy id");
        }
        return response;
    }


    //method to frame TherapyMaster room detail Get DTO
    private List<TherapyMasterRoomDetailGetDTO> frameTherapyMasterRoomDetailGetDTO(TherapyMaster therapyMaster) {
        List<TherapyMasterRoomDetailGetDTO> therapyMasterRoomDetailGetDTOs = new ArrayList<>();
        try {
            log.debug("Call to frame therapy master room detail dto");
            // find therapy master room detail by therapy master
            List<TherapyMasterRoomDetail> therapyMasterRoomDetails = therapyMaster.getTherapyMasterRoomDetails();
            if (therapyMasterRoomDetails != null)
                for (TherapyMasterRoomDetail therapyMasterRoomDetail : therapyMasterRoomDetails) {
                    TherapyMasterRoomDetailGetDTO therapyMasterRoomDetailGetDTO = new TherapyMasterRoomDetailGetDTO();
                    // to find room details
                    if (therapyMasterRoomDetail.getRoomDetailId() != null) {
                        String therapyRoomName = therapyRoomDetailsRepository.
                                findRoomNameByTherapyRoomDetailId(therapyMasterRoomDetail.getRoomDetailId());
                        therapyMasterRoomDetailGetDTO.setRoomDetailId(therapyMasterRoomDetail.getRoomDetailId());
                        therapyMasterRoomDetailGetDTO.setRoomName(therapyRoomName);
                    }
                    therapyMasterRoomDetailGetDTOs.add(therapyMasterRoomDetailGetDTO);
                }
        } catch (Exception e) {
            log.error("Failed to frame therapy master room detail dto" + e.getMessage());
        }
        return therapyMasterRoomDetailGetDTOs;
    }

    //method to frame TherapyMaster therapist detail Get DTO
    private List<TherapyMasterTherapistDetailGetDTO> frameTherapyMasterTherapistDetailGetDTO(TherapyMaster therapyMaster) {
        List<TherapyMasterTherapistDetailGetDTO> therapyMasterTherapistDetailGetDTOs = new ArrayList<>();

        try {
            log.debug("Call to frame therapy medicine type get dto");
            // find therapy master therapist detail by therapy master
            List<TherapyMasterTherapistDetail> therapyMasterTherapistDetails = therapyMaster.getTherapyMasterTherapistDetails();
            if (therapyMasterTherapistDetails != null)
                for (TherapyMasterTherapistDetail therapyMasterTherapistDetail : therapyMasterTherapistDetails) {
                    TherapyMasterTherapistDetailGetDTO therapyMasterTherapistDetailGetDTO = new TherapyMasterTherapistDetailGetDTO();
                    //finding userName
                    if (therapyMasterTherapistDetail.getUserId() != null) {
                        String userName = userStaffRepository.findUserNameById(therapyMasterTherapistDetail.getUserId());
                        therapyMasterTherapistDetailGetDTO.setUserId(therapyMasterTherapistDetail.getUserId());
                        therapyMasterTherapistDetailGetDTO.setUserName(userName);
                    }
                    therapyMasterTherapistDetailGetDTOs.add(therapyMasterTherapistDetailGetDTO);
                }
        } catch (Exception e) {
            log.error("Failed to frame therapy master therapist detail dto" + e.getMessage());
        }
        return therapyMasterTherapistDetailGetDTOs;
    }
}
