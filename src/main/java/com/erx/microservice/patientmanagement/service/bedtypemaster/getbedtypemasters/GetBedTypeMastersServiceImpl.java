package com.erx.microservice.patientmanagement.service.bedtypemaster.getbedtypemasters;

/*
* created by Brighty on 16-11-2017
* */


import com.erx.microservice.patientmanagement.domain.BedTypeMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.BedTypeMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.beedtypedto.BedTypeMasterByClinicLocationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getBedTypeMastersService")
public class GetBedTypeMastersServiceImpl implements GetBedTypeMastersService {

    private final Logger log = LoggerFactory.getLogger(GetBedTypeMastersServiceImpl.class);

    @Autowired
    private BedTypeMasterRepository bedTypeMasterRepository;

    public GetBedTypeMastersServiceImpl(BedTypeMasterRepository bedTypeMasterRepository) {
        this.bedTypeMasterRepository = bedTypeMasterRepository;
    }

    //constructor

    @Override
    public GetBedTypeMastersServiceResponse execute(GetBedTypeMastersServiceRequest request) throws ServiceException {

        log.debug("call to get all the BedTypeMasters for the given clinicLocation");
        GetBedTypeMastersServiceResponse response = null;
        List<BedTypeMaster> bedTypeMasters = null;
        List<BedTypeMasterByClinicLocationDTO> bedTypeMasterByClinicLocationDTOS = new ArrayList<>();

        try {
            if (request.getClinicLocationId() != 0) {
                //retrieve the list of BedTypeMaster
                bedTypeMasters = bedTypeMasterRepository.getBedTypeMastersByClinicLocation(request.getClinicLocationId());
                //set the values in BedTypeMasterByClinicLocationDTO list
                for (BedTypeMaster bedTypeMaster : bedTypeMasters) {
                    //create BedTypeMasterByClinicLocationDTO object
                    BedTypeMasterByClinicLocationDTO bedTypeMasterByClinicLocationDTO = new BedTypeMasterByClinicLocationDTO();
                    //set the values
                    bedTypeMasterByClinicLocationDTO.setId(bedTypeMaster.getId());
                    bedTypeMasterByClinicLocationDTO.setBedTypeName(bedTypeMaster.getBedTypeName());

                    //add to the list
                    bedTypeMasterByClinicLocationDTOS.add(bedTypeMasterByClinicLocationDTO);
                }
            }
            //create response
            response = new GetBedTypeMastersServiceResponse(bedTypeMasterByClinicLocationDTOS);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved all the BedTypeMasters for the given clinicLocation");
            log.debug("Retrieved all the BedTypeMasters for the given clinicLocation");
        } catch (Exception e) {
            //create response
            response = new GetBedTypeMastersServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Not Retrieved all the BedTypeMasters for the given clinicLocation");
            log.error("Not retrieved all the BedTypeMasters for the given clinicLocation");
        }
        return response;
    }
}
