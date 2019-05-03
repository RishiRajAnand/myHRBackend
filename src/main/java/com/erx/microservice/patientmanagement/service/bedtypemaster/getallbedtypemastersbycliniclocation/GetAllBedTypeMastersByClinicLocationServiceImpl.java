package com.erx.microservice.patientmanagement.service.bedtypemaster.getallbedtypemastersbycliniclocation;

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

import java.util.ArrayList;
import java.util.List;

@Service("getAllBedTypeMastersByClinicLocationService")
public class GetAllBedTypeMastersByClinicLocationServiceImpl implements GetAllBedTypeMastersByClinicLocationService {

    private final Logger log = LoggerFactory.getLogger(GetAllBedTypeMastersByClinicLocationServiceImpl.class);

    @Autowired
    private BedTypeMasterRepository bedTypeMasterRepository;

    @Autowired
    private BedTypeMasterMapper bedTypeMasterMapper;

    public GetAllBedTypeMastersByClinicLocationServiceImpl(BedTypeMasterRepository bedTypeMasterRepository,
                                                           BedTypeMasterMapper bedTypeMasterMapper) {
        this.bedTypeMasterRepository = bedTypeMasterRepository;
        this.bedTypeMasterMapper = bedTypeMasterMapper;
    }

    //constructor

    @Override
    public GetAllBedTypeMastersByClinicLocationServiceResponse execute(GetAllBedTypeMastersByClinicLocationServiceRequest request) throws ServiceException {

        GetAllBedTypeMastersByClinicLocationServiceResponse response = null;
        List<BedTypeMaster> bedTypeMasters = null;
        List<BedTypeMasterDTO> bedTypeMasterDTOs = new ArrayList<>();
        try {
            log.debug("call to retrieve all the BedTypeMasters for the given clinicLocation");
            if (request.getClinicLocationId() != 0) {
                //retrieve BedTypeMasters
                if (request.getIsActive().isPresent())
                    bedTypeMasters = bedTypeMasterRepository.findByClinicLocationIdAndStatusAndRecordStatusOrderBySequenceOrderNoAsc
                            (request.getClinicLocationId(), request.getIsActive().get(), 1);
                else
                    bedTypeMasters = bedTypeMasterRepository.findByClinicLocationIdAndRecordStatusOrderBySequenceOrderNoAsc
                            (request.getClinicLocationId(), 1);

                //convert domain to DTO
                for (BedTypeMaster bedTypeMaster : bedTypeMasters) {
                    BedTypeMasterDTO bedTypeMasterDTO = bedTypeMasterMapper.bedTypeMasterToBedTypeMasterDTO(bedTypeMaster);
                    //add to list
                    bedTypeMasterDTOs.add(bedTypeMasterDTO);
                }
            }
            //create response
            response = new GetAllBedTypeMastersByClinicLocationServiceResponse(bedTypeMasterDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved all BedTypeMasters for the given ClinicLocation");
            log.debug("Retrieved all BedTypeMasters for the given ClinicLocation");
        } catch (Exception e) {
            //create response
            response = new GetAllBedTypeMastersByClinicLocationServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Not retrieved all BedTypeMasters for the given ClinicLocation");
            log.error("Not retrieved all BedTypeMasters for the given ClinicLocation");
        }
        return response;
    }
}
