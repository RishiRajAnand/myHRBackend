package com.erx.microservice.patientmanagement.service.ipadmission.bedmovementdepartment;

import com.erx.microservice.patientmanagement.domain.DepartmentMaster;
import com.erx.microservice.patientmanagement.domain.IpAdmission;
import com.erx.microservice.patientmanagement.domain.IpAdmissionBedMovement;
import com.erx.microservice.patientmanagement.domain.UserStaff;
import com.erx.microservice.patientmanagement.repository.DepartmentMasterRepository;
import com.erx.microservice.patientmanagement.repository.IpAdmissionBedMovementRepository;
import com.erx.microservice.patientmanagement.repository.IpAdmissionRepository;
import com.erx.microservice.patientmanagement.repository.UserStaffRepository;
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
@Service("bedMovementDepartmentService")

public class BedMovementDepartmentServiceImpl implements BedMovementDepartmentService {

    private final Logger log = LoggerFactory.getLogger(BedMovementDepartmentServiceImpl.class);

    @Autowired
    IpAdmissionBedMovementMapper ipAdmissionBedMovementMapper;

    @Autowired
    IpAdmissionBedMovementRepository ipAdmissionBedMovementRepository;

    @Autowired
    IpAdmissionRepository ipAdmissionRepository;

    @Autowired
    DepartmentMasterRepository departmentMasterRepository;

    @Autowired
    UserStaffRepository userStaffRepository;

    public BedMovementDepartmentServiceImpl(IpAdmissionBedMovementMapper ipAdmissionBedMovementMapper,
                                            IpAdmissionBedMovementRepository ipAdmissionBedMovementRepository,
                                            IpAdmissionRepository ipAdmissionRepository, DepartmentMasterRepository departmentMasterRepository,
                                            UserStaffRepository userStaffRepository) {
        this.ipAdmissionBedMovementMapper = ipAdmissionBedMovementMapper;
        this.ipAdmissionBedMovementRepository = ipAdmissionBedMovementRepository;
        this.ipAdmissionRepository = ipAdmissionRepository;
        this.departmentMasterRepository = departmentMasterRepository;
        this.userStaffRepository = userStaffRepository;
    }

    // constructor

    @Override
    public BedMovementDepartmentServiceResponse execute(BedMovementDepartmentServiceRequest request) throws ServiceException {

        BedMovementDepartmentServiceResponse response = null;
        BedMovementDTO bedMovementDTO = null;
        BedMovementDTO savedBedMovementDTO = null;
        IpAdmissionBedMovement ipAdmissionBedMovement = null;
        IpAdmission ipAdmission = null;
        DepartmentMaster bedMovedDepartment = null;
        DepartmentMaster bedMovedSubDepartment = null;
        UserStaff userStaff = null;
        IpAdmissionBedMovement savedIpAdmissionBedMovement = null;

        try {
            log.debug("To Move IpAdmission");
            //retrieve the Object from request
            bedMovementDTO = request.getBedMovementDTO();
            if (bedMovementDTO.getIpAdmissionID() != null) {
                //convert DTO to domain
                ipAdmissionBedMovement = ipAdmissionBedMovementMapper.BedMovementDTOToIpAdmissionBedMovement(bedMovementDTO);
                //set ipAdmission to ipAdmissionBedTransfer
                ipAdmission = ipAdmissionRepository.findOne(bedMovementDTO.getIpAdmissionID());
                ipAdmissionBedMovement.setIpAdmission(ipAdmission);
                if (bedMovementDTO.getDepartmentID() != null) {
                    //set department to ipAdmissionBedTransfer
                    bedMovedDepartment = departmentMasterRepository.findOne(bedMovementDTO.getDepartmentID());
                    ipAdmissionBedMovement.setBedMovedDepartment(bedMovedDepartment);
                }
                //set subDepartment to ipAdmissionBedTransfer
                if (bedMovementDTO.getSubDepartmentID() != null) {
                    bedMovedSubDepartment = departmentMasterRepository.findOne(bedMovementDTO.getSubDepartmentID());
                    ipAdmissionBedMovement.setBedMovedSubDepartment(bedMovedSubDepartment);
                }
                userStaff = userStaffRepository.findOne(bedMovementDTO.getUserID());
                ipAdmissionBedMovement.setUserStaff(userStaff);
                savedIpAdmissionBedMovement = ipAdmissionBedMovementRepository.save(ipAdmissionBedMovement);
                //convert the saved object into DTO
                savedBedMovementDTO = ipAdmissionBedMovementMapper.ipAdmissionBedMovementToBedMovementDTO(savedIpAdmissionBedMovement);
            }
            //create response
            response = new BedMovementDepartmentServiceResponse(savedBedMovementDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Movement of bed from department in bed movement of IpAdmission Successfully");
            log.debug("Movement of bed from department in bed movement of IpAdmission Successfully");
        } catch (Exception e) {
            response = new BedMovementDepartmentServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to move department for IpAdmission");
            log.error("Failed to move department for IpAdmission");
        }
        return response;
    }
}
