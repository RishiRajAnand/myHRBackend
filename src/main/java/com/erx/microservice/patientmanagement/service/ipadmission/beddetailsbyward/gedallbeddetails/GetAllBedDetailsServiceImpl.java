package com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.gedallbeddetails;

/*
 * created by Brighty on 09-05-2018.
 * */

import com.erx.microservice.patientmanagement.domain.BedConfigurationMaster;
import com.erx.microservice.patientmanagement.domain.IpAdmission;
import com.erx.microservice.patientmanagement.domain.WardMaster;
import com.erx.microservice.patientmanagement.repository.BedConfigurationMasterRepository;
import com.erx.microservice.patientmanagement.repository.IpAdmissionRepository;
import com.erx.microservice.patientmanagement.repository.WardMasterRepository;
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

@Service("getAllBedDetailsService")
public class GetAllBedDetailsServiceImpl implements GetAllBedDetailsService {

    private final Logger log = LoggerFactory.getLogger(GetAllBedDetailsServiceImpl.class);

    @Autowired
    private BedConfigurationMasterRepository bedConfigurationMasterRepository;

    @Autowired
    private BedConfigurationMasterMapper bedConfigurationMasterMapper;

    @Autowired
    private IpAdmissionRepository ipAdmissionRepository;

    @Autowired
    private WardMasterRepository wardMasterRepository;

    @Override
    public GetBedDetailsByWardIdServiceResponse execute(GetBedDetailsByWardIdServiceRequest request) throws ServiceException {
        GetBedDetailsByWardIdServiceResponse response = null;
        List<BedConfigurationDTO> bedConfigurationDTOs = new ArrayList<>();
        List<WardMaster> wardMasterList = null;
        WardMaster wardMaster = null;
        try {
            log.debug("GetBedDetailsByWardIdServiceImpl ==> Call to get bed details for all the wards");
            //check for mandatory fields
            if (request.getClinicLocationId() != null && request.getType() != null && !request.getType().isEmpty()) {
                if (request.getWardId() == null) { //if wardId is null then fetch all wards by clinicLocationId and status
                    wardMasterList = wardMasterRepository.findByClinicLocationIdAndStatusAndRecordStatus(request.getClinicLocationId(),
                            true, 1);
                    if (!wardMasterList.isEmpty()) { //check list is empty or not
                        for (WardMaster responseWardMaster : wardMasterList)
                            //call setBedDetails method to set bed details
                            setBedDetails(responseWardMaster.getId(), request.isDaycare(), bedConfigurationDTOs);
                    } else //construct response
                        return constructResponse(true, " Bed details not found for the " +
                                "clinicLocation: " + request.getClinicLocationId());
                } else { //if wardId is not null then get wardDetails by clinicLocationId,status and wardId
                    wardMaster = wardMasterRepository.findByIdAndClinicLocationIdAndStatusAndRecordStatus(request.getWardId(),
                            request.getClinicLocationId(), true, 1);
                    if (wardMaster != null) //if not null call setBedDetails method to set bedDetails
                        setBedDetails(wardMaster.getId(), request.isDaycare(), bedConfigurationDTOs);
                    else
                        return constructResponse(true, "Bed details not found for the clinicLocationId " + request.getClinicLocationId()
                                + " and wardId " + request.getWardId());
                }
                response = constructResponse(true, "Bed details retrieved successfully for the " +
                        "clinicLocation:" + request.getClinicLocationId() + "wardId " + request.getWardId());
                response.setBedConfigurationDTOs(bedConfigurationDTOs); //set bedConfigurationDTOs
            } else
                return constructResponse(false, "Invalid input");

        } catch (Exception e) {
            response = constructResponse(false, "Failed to retrieve BedConfigurationMasters " + e.getMessage());
            log.error(response.getMessage());
        }
        return response;
    }

    //method to construct response
    private GetBedDetailsByWardIdServiceResponse constructResponse(boolean responseSuccess, String message) throws ServiceException {

        GetBedDetailsByWardIdServiceResponse response = new GetBedDetailsByWardIdServiceResponse();
        response.SUCCESSFUL = responseSuccess;
        response.setMessage(message);
        log.debug(message);
        return response;
    }

    //method to set bed Details
    private void setBedDetails(Long wardMasterId, boolean daycare, List<BedConfigurationDTO> bedConfigurationDTOs)
            throws ServiceException {

        GetBedDetailsByWardIdServiceResponse response = null;
        List<BedConfigurationMaster> bedConfigurationMasters = null;
        List<WardMaster> wardMasters = null;
        //retrieve the objects
        bedConfigurationMasters = bedConfigurationMasterRepository.getBedConfigurationByWardAndDaycare
                (wardMasterId, daycare);
        //convert domain to DTO
        for (BedConfigurationMaster bedConfigurationMaster : bedConfigurationMasters) {
            BedConfigurationDTO bedConfigurationDTO = new BedConfigurationDTO();
            //convert bedConfigurationMaster to bedConfigurationDTO
            bedConfigurationDTO = bedConfigurationMasterMapper
                    .bedConfigurationMasterToBedConfigurationDTO(bedConfigurationMaster);
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
            bedConfigurationDTOs.add(bedConfigurationDTO); //add to list
        }
    }
}

