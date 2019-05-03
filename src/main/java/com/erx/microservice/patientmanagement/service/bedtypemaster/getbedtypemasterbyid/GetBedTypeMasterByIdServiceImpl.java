package com.erx.microservice.patientmanagement.service.bedtypemaster.getbedtypemasterbyid;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.domain.BedTypeMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.BedTypeMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.beedtypedto.BedTypeMasterDTO;
import com.erx.microservice.patientmanagement.service.mapper.BedTypeMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("getBedTypeMasterByIdService")
public class GetBedTypeMasterByIdServiceImpl implements GetBedTypeMasterByIdService {

    private final Logger log = LoggerFactory.getLogger(GetBedTypeMasterByIdServiceImpl.class);

    @Autowired
    private BedTypeMasterRepository bedTypeMasterRepository;

    @Autowired
    private BedTypeMasterMapper bedTypeMasterMapper;

    public GetBedTypeMasterByIdServiceImpl(BedTypeMasterRepository bedTypeMasterRepository,
                                           BedTypeMasterMapper bedTypeMasterMapper) {
        this.bedTypeMasterRepository = bedTypeMasterRepository;
        this.bedTypeMasterMapper = bedTypeMasterMapper;
    }

    //constructor

    @Override
    public GetBedTypeMasterByIdServiceResponse execute(GetBedTypeMasterByIdServiceRequest request) throws ServiceException {

        GetBedTypeMasterByIdServiceResponse response = null;
        BedTypeMaster bedTypeMaster = null;
        BedTypeMasterDTO bedTypeMasterDTO = null;
        try {
            log.debug("call to retrieve BedTypeMaster with id : " + request.getBedTypeMasterId());
            if (request.getBedTypeMasterId() != 0) {
                //retrieve the BedTypeMaster
                bedTypeMaster = bedTypeMasterRepository.findOne(request.getBedTypeMasterId());
                //convert domain to DTO
                bedTypeMasterDTO = bedTypeMasterMapper.bedTypeMasterToBedTypeMasterDTO(bedTypeMaster);
                //set clinicLocation in bedTypeMasterDTO
                //bedTypeMasterDTO.setClinicLocationId(bedTypeMaster.getClinicLocation().getId());
            }
            //create response
            response = new GetBedTypeMasterByIdServiceResponse(bedTypeMasterDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved BedTypeMaster successfully");
            log.debug("Retrieved BedTypeMaster successfully");
        } catch (Exception e) {
            //create response
            response = new GetBedTypeMasterByIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Not retrieved BedTypeMaster successfully");
            log.error("Not retrieved BedTypeMaster successfully");
        }
        return response;
    }
}
