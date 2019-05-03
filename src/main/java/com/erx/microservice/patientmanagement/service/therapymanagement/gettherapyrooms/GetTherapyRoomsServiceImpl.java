package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapyrooms;

/*
* created by Latha on 04-09-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyRoomDetails;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyRoomDetailsRepository;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.TherapyRoomsDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("getTherapyRoomsService")
public class GetTherapyRoomsServiceImpl implements GetTherapyRoomsService {

    private final Logger log = LoggerFactory.getLogger(GetTherapyRoomsServiceImpl.class);

    @Autowired
    private TherapyRoomDetailsRepository therapyRoomDetailsRepository;

    @Override
    public GetTherapyRoomsServiceResponse execute(GetTherapyRoomsServiceRequest request) throws ServiceException {

        GetTherapyRoomsServiceResponse response = null;
        List<TherapyRoomDetails> therapyRoomDetails = null;
        List<TherapyRoomsDTO> therapyRoomsDTOs = new ArrayList<>();
        try {
            log.debug("call to get therapy rooms by clinicLocationId" + request.getClinicLocationId());
            therapyRoomDetails = therapyRoomDetailsRepository.findRoomByClinicLocationId(request.getClinicLocationId());
            if(therapyRoomDetails != null)
                for(TherapyRoomDetails therapyRoomDetail : therapyRoomDetails){
                    TherapyRoomsDTO therapyRoomsDTO = new TherapyRoomsDTO();
                    therapyRoomsDTO.setTherapyRoomDetailId(therapyRoomDetail.getId());
                    if(therapyRoomDetail.getTherapyRoomMaster() != null)
                    therapyRoomsDTO.setTherapyRoomName(therapyRoomDetail.getTherapyRoomMaster().getName());
                    therapyRoomsDTOs.add(therapyRoomsDTO);
                }
            // setting the dto to response
            response = new GetTherapyRoomsServiceResponse(therapyRoomsDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved therapy room details Successfully");
            log.debug("Retrieved therapy room details Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve therapy room details");
            log.error("Failed to retrieve therapy room details");
        }
        return response;
    }
}
