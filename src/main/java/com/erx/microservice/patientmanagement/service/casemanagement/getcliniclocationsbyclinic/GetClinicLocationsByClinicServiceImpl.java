package com.erx.microservice.patientmanagement.service.casemanagement.getcliniclocationsbyclinic;

import com.erx.microservice.patientmanagement.domain.Clinic;
import com.erx.microservice.patientmanagement.domain.ClinicLocation;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.ClinicLocationRepository;
import com.erx.microservice.patientmanagement.repository.ClinicRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterDetailsRepository;
import com.erx.microservice.patientmanagement.service.dto.ClinicLocationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * created by Latha on 10-10-2018
 * */

@Service("getClinicLocationsByClinicService")
public class GetClinicLocationsByClinicServiceImpl implements GetClinicLocationsByClinicService {

    private final Logger log = LoggerFactory.getLogger(GetClinicLocationsByClinicServiceImpl.class);

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private ClinicLocationRepository clinicLocationRepository;

    @Override
    public GetClinicLocationsByClinicServiceResponse execute(GetClinicLocationsByClinicServiceRequest request) throws ServiceException {

        GetClinicLocationsByClinicServiceResponse response = null;
        Clinic clinic = new Clinic();
        List<ClinicLocation> clinicLocations = new ArrayList<>();
        List<ClinicLocationDTO> clinicLocationDTOs = new ArrayList<>();

        try {
            //find clinic by clinic id
            clinic = clinicRepository.findOne(request.getClinicId());

            //find clinic locations by clinic
            clinicLocations = clinicLocationRepository.findAllClinicLocationByClinic(clinic);
            for(ClinicLocation clinicLocation : clinicLocations){
                ClinicLocationDTO clinicLocationDTO = new ClinicLocationDTO();
                clinicLocationDTO.setId(clinicLocation.getId());
                clinicLocationDTO.setClinicLocationName(clinicLocation.getLocation().getName());
                clinicLocationDTOs.add(clinicLocationDTO);
            }
            // setting the dto to response
            response = new GetClinicLocationsByClinicServiceResponse(clinicLocationDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved clinic location details Successfully");
            log.debug("Retrieved clinic location details Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve clinic location details");
            log.error("Failed to retrieve clinic location details");
        }
        return response;
    }
}