package com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.gedbeddetailsbywardid;

/*
* created by Brighty on 29-11-2017.
* */

import com.erx.microservice.patientmanagement.domain.BedConfigurationMaster;
import com.erx.microservice.patientmanagement.domain.IpAdmission;
import com.erx.microservice.patientmanagement.repository.BedConfigurationMasterRepository;
import com.erx.microservice.patientmanagement.repository.IpAdmissionRepository;
import com.erx.microservice.patientmanagement.service.dto.BedConfigurationDTO;
import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.GetBedDetailsByWardIdServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.GetBedDetailsByWardIdServiceResponse;
import com.erx.microservice.patientmanagement.service.mapper.BedConfigurationMasterMapper;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getBedDetailsByWardIdService")
public class GetBedDetailsByWardIdServiceImpl implements GetBedDetailsByWardIdService {

    private final Logger log = LoggerFactory.getLogger(GetBedDetailsByWardIdServiceImpl.class);

    @Autowired
    private BedConfigurationMasterRepository bedConfigurationMasterRepository;

    @Autowired
    private BedConfigurationMasterMapper bedConfigurationMasterMapper;

    @Autowired
    private IpAdmissionRepository ipAdmissionRepository;

    @Override
    public GetBedDetailsByWardIdServiceResponse execute(GetBedDetailsByWardIdServiceRequest request) throws ServiceException {

        GetBedDetailsByWardIdServiceResponse response = null;
        List<BedConfigurationDTO> bedConfigurationDTOs = new ArrayList<>();
        List<BedConfigurationMaster> bedConfigurationMasters = null;
        try {
            log.debug("GetBedDetailsByWardIdServiceImpl ==> Call to get bed details for the given ward");
            if (request.getWardId() != null) {
                //retrieve the objects
                bedConfigurationMasters = bedConfigurationMasterRepository.getBedConfigurationByWardAndDaycare(request.getWardId(), request.isDaycare());
                //convert domain to DTO
                for (BedConfigurationMaster bedConfigurationMaster : bedConfigurationMasters) {
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

                    IpAdmission ipAdmission = null;
                    List<IpAdmission> ipAdmissions = ipAdmissionRepository.findActivePatient(bedConfigurationMaster.getId());
                    if (!ipAdmissions.isEmpty()) ipAdmission = ipAdmissions.get(0);
                    if (ipAdmission != null) {
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
                        if (ipAdmission.getIpAdmissionCaseMapping() != null)
                            bedConfigurationDTO.setCmMasterId(ipAdmission.getIpAdmissionCaseMapping().getCmMaster().getId());
                    }

                    //add to list
                    bedConfigurationDTOs.add(bedConfigurationDTO);
                }
            }
            response = new GetBedDetailsByWardIdServiceResponse(bedConfigurationDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved Bed details Successfully");
            log.debug("GetBedDetailsByWardIdServiceImpl ==> Retrieved BedConfigurationMasters Successfully");
        } catch (Exception e) {
            response = new GetBedDetailsByWardIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Bed details");
            log.error("GetBedDetailsByWardIdServiceImpl ==> Failed to retrieve BedConfigurationMasters " + e.getMessage());
        }
        return response;
    }
}
