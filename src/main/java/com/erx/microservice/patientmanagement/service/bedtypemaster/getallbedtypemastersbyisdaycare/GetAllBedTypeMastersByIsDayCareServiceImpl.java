package com.erx.microservice.patientmanagement.service.bedtypemaster.getallbedtypemastersbyisdaycare;
/*.. created by pavani...*/
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

@Service
public class GetAllBedTypeMastersByIsDayCareServiceImpl implements GetAllBedTypeMastersByIsDayCareService {

    private final Logger log = LoggerFactory.getLogger(GetAllBedTypeMastersByIsDayCareServiceImpl.class);

    @Autowired
    private BedTypeMasterRepository bedTypeMasterRepository;

    public GetAllBedTypeMastersByIsDayCareServiceImpl(BedTypeMasterRepository bedTypeMasterRepository) {
        this.bedTypeMasterRepository = bedTypeMasterRepository;
    }

    @Override
    public GetAllBedTypeMastersByIsDayCareServiceResponse execute(GetAllBedTypeMastersByIsDayCareServiceRequest request) throws ServiceException {
        log.debug("call to get all the BedTypeMastersByIsDayCare for the given clinicLocation");
        GetAllBedTypeMastersByIsDayCareServiceResponse response = null;
        List<BedTypeMaster> bedTypeMasters = null;
        List<BedTypeMasterByClinicLocationDTO> bedTypeMasterByClinicLocationDTOS = new ArrayList<>();

        try {
            if (request.getClinicLocationId() != null)  {
                //retrieve the list of BedTypeMasterByClinicLocationAndIsDaycare
                bedTypeMasters =  bedTypeMasterRepository.getBedTypeMastersByClinicLocationAndIsDaycare(request.getClinicLocationId(),request.isDayCare());
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
            response = new GetAllBedTypeMastersByIsDayCareServiceResponse(bedTypeMasterByClinicLocationDTOS);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved all the BedTypeMastersByIsDayCare for the given clinicLocation");
            log.debug("Retrieved all the BedTypeMastersByIsDayCare for the given clinicLocation");
        } catch (Exception e) {
            //create response
            response = new GetAllBedTypeMastersByIsDayCareServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Not Retrieved all the BedTypeMastersByIsDayCare for the given clinicLocation");
            log.error("Not retrieved all the BedTypeMastersByIsDayCare for the given clinicLocation");
        }
        return response;
    }
}

