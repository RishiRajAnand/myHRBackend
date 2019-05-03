package com.erx.microservice.patientmanagement.service.ipadmission.movetoactualbed;

import com.erx.microservice.patientmanagement.domain.BedConfigurationMaster;
import com.erx.microservice.patientmanagement.domain.IpAdmission;
import com.erx.microservice.patientmanagement.domain.IpAdmissionBedMovement;
import com.erx.microservice.patientmanagement.domain.UserStaff;
import com.erx.microservice.patientmanagement.repository.*;
import com.erx.microservice.patientmanagement.service.dto.BedMovementDTO;
import com.erx.microservice.patientmanagement.service.mapper.IpAdmissionBedMovementMapper;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* created by Latha on 29-11-2017
* */
@Service("moveToActualBedService")

public class MoveToActualBedServiceImpl implements MoveToActualBedService {

    private final Logger log = LoggerFactory.getLogger(MoveToActualBedServiceImpl.class);

    @Autowired
    IpAdmissionBedMovementMapper ipAdmissionBedMovementMapper;

    @Autowired
    IpAdmissionBedMovementRepository ipAdmissionBedMovementRepository;

    @Autowired
    IpAdmissionRepository ipAdmissionRepository;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    BedConfigurationMasterRepository bedConfigurationMasterRepository;

    @Autowired
    UserStaffRepository userStaffRepository;

    @Override
    public MoveToActualBedServiceResponse execute(MoveToActualBedServiceRequest request) throws ServiceException {

        MoveToActualBedServiceResponse response = null;
        BedMovementDTO bedMovementDTO = null;
        BedMovementDTO savedBedMovementDTO = null;
        IpAdmissionBedMovement ipAdmissionBedMovement = new IpAdmissionBedMovement();
        IpAdmissionBedMovement savedIpAdmissionBedMovement = null;
        IpAdmissionBedMovement previousIpAdmissionBedMovement = null;
        IpAdmissionBedMovement savedPreviousIpAdmissionBedMovement = null;
        IpAdmission ipAdmission = null;
        BedConfigurationMaster bedMovedFrom = null;
        BedConfigurationMaster bedMovedTo = null;
        BedConfigurationMaster bedConfigurationMasterFrom = null;
        BedConfigurationMaster bedConfigurationMasterTo = null;
        UserStaff userStaff = null;

        try {
            log.debug("To Move IpAdmission");
            //retrieve the Object from request
            bedMovementDTO = request.getBedMovementDTO();
            if (bedMovementDTO.getId() != null) {
                //retrieve ipAdmissionBedMovement details by passing ID
                previousIpAdmissionBedMovement = ipAdmissionBedMovementRepository.findOne(bedMovementDTO.getId());
                previousIpAdmissionBedMovement.setCurrentBed(false);
                savedPreviousIpAdmissionBedMovement = ipAdmissionBedMovementRepository.save(previousIpAdmissionBedMovement);
            }
            //set ipAdmission to ipAdmissionBedTransfer
            ipAdmission = ipAdmissionRepository.findOne(bedMovementDTO.getIpAdmissionID());
            ipAdmissionBedMovement.setIpAdmission(ipAdmission);
            //set current bed as true
            ipAdmissionBedMovement.setCurrentBed(true);
            //set moved from bed to IpAdmissionBedMovement
            bedMovedFrom = bedConfigurationMasterRepository.findOne(bedMovementDTO.getFromBedMasterID());
            ipAdmissionBedMovement.setBedMovedFrom(bedMovedFrom);
            //allocate status of Moved is changing once the bed is Moved(Moved in bed master)
            bedMovedFrom.setAllocatedStatus("availability");
            bedConfigurationMasterFrom = bedConfigurationMasterRepository.save(bedMovedFrom);
            // set moved to bed in IpAdmissionBedMovement
            bedMovedTo = bedConfigurationMasterRepository.findOne(bedMovementDTO.getToBedMasterID());
            ipAdmissionBedMovement.setBedMovedTo(bedMovedTo);
            //allocate status of occupied is changing once the bed is Moved(moved in bed master)
            bedMovedTo.setAllocatedStatus("occupied");
            bedConfigurationMasterTo = bedConfigurationMasterRepository.save(bedMovedTo);
            //set moved by to IpAdmissionBedMovement
            userStaff = userStaffRepository.findOne(bedMovementDTO.getUserID());
            ipAdmissionBedMovement.setUserStaff(userStaff);
            savedIpAdmissionBedMovement = ipAdmissionBedMovementRepository.save(ipAdmissionBedMovement);
            //convert the saved object into DTO
            savedBedMovementDTO = ipAdmissionBedMovementMapper.ipAdmissionBedMovementToBedMovementDTO(savedIpAdmissionBedMovement);
            savedBedMovementDTO.setOriginalBed(true);

            //create response
            response = new MoveToActualBedServiceResponse(savedBedMovementDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Movement of actual bed from bed movement has done Successfully");
            log.debug("Movement of actual bed from bed movement has done Successfully");
        } catch (Exception e) {
            response = new MoveToActualBedServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to move actual bed for IpAdmission");
            log.error("Failed to move actual bed for IpAdmission");
        }
        return response;
    }
}
