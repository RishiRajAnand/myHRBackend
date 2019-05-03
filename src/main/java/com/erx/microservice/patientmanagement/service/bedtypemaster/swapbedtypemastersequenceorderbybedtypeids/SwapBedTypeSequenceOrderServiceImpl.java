package com.erx.microservice.patientmanagement.service.bedtypemaster.swapbedtypemastersequenceorderbybedtypeids;


import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.BedTypeMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.beedtypedto.BedTypeSwapSequenceOrderDTO;
import com.erx.microservice.patientmanagement.web.rest.util.BedTypeMasterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * created by Bahubali on 08-08-2018
 * */
@Service
public class SwapBedTypeSequenceOrderServiceImpl implements SwapBedTypeSequenceOrderService {

    private final static Logger log = LoggerFactory.getLogger(SwapBedTypeSequenceOrderServiceImpl.class);

    private final BedTypeMasterRepository bedTypeMasterRepository;

    public SwapBedTypeSequenceOrderServiceImpl(BedTypeMasterRepository bedTypeMasterRepository) {
        this.bedTypeMasterRepository = bedTypeMasterRepository;
    }

    @Override
    public SwapBedTypeSequenceOrderServiceResponse execute(SwapBedTypeSequenceOrderServiceRequest request) throws ServiceException {
        SwapBedTypeSequenceOrderServiceResponse response = null;
        log.debug(BedTypeMasterConstants.BED_TYPE_SWAP_SERVICE_MESSAGE);
        BedTypeSwapSequenceOrderDTO bedTypeSwapSequenceOrderDTO = null;
        int countBedTypeMaster;
        List<Long> bedTypeMasterIds = null;
        try {
            //retrieve request
            bedTypeSwapSequenceOrderDTO = request.getBedTypeSwapSequenceOrderDTO();
            //check for mandatory fields
            if (bedTypeSwapSequenceOrderDTO.getFromBedTypeMasterId() != null && bedTypeSwapSequenceOrderDTO.getFromBedTypeSequenceOrderNo() != null
                    && bedTypeSwapSequenceOrderDTO.getToBedTypeMasterId() != null && bedTypeSwapSequenceOrderDTO.getToBedTypeSequenceOrderNo() != null) {
                bedTypeMasterIds = new ArrayList<>();
                //add bedTypeMasterIds to list
                bedTypeMasterIds.add(request.getBedTypeSwapSequenceOrderDTO().getFromBedTypeMasterId());
                bedTypeMasterIds.add(request.getBedTypeSwapSequenceOrderDTO().getToBedTypeMasterId());
                //check given ids exists or not
                countBedTypeMaster = bedTypeMasterRepository.countByIdInAndRecordStatus(bedTypeMasterIds, 1);
                if (countBedTypeMaster == 2) { //if exists(both the ids)
                    //swap toBedTypeMasterId sequenceOrderNo with fromSequenceOrderNo
                    bedTypeMasterRepository.swapSequenceOrderByBedTypeId
                            (bedTypeSwapSequenceOrderDTO.getToBedTypeMasterId(), bedTypeSwapSequenceOrderDTO.getFromBedTypeSequenceOrderNo());
                    //swap fromBedTypeMasterId sequenceOrderNo with toSequenceOrderNo
                    bedTypeMasterRepository.swapSequenceOrderByBedTypeId(bedTypeSwapSequenceOrderDTO.getFromBedTypeMasterId(),
                            bedTypeSwapSequenceOrderDTO.getToBedTypeSequenceOrderNo());
                    return constructResponse(true, BedTypeMasterConstants.BED_TYPE_SEQUENCE_ORDER_SWAP_SUCCESS);
                } else //when bedTypeMasterIds not found
                    return constructResponse(false, BedTypeMasterConstants.BED_TYPE_MASTER_NOT_FOUND);

            } else  //call method to construct response for invalid response
                return constructResponse(true, BedTypeMasterConstants.INVALID_INPUT);
        } catch (Exception e) {
            return constructResponse(false, BedTypeMasterConstants.BED_TYPE_SEQUENCE_ORDER_SWAP_FAILURE);
        }
    }

    //method to construct response
    private SwapBedTypeSequenceOrderServiceResponse constructResponse(boolean isSuccess, String message)
            throws ServiceException {

        SwapBedTypeSequenceOrderServiceResponse response = new SwapBedTypeSequenceOrderServiceResponse();
        log.debug(message);
        response.SUCCESSFUL = isSuccess;
        response.setMessage(message);
        if (isSuccess == false) {
            response.setErrorMessage(message);
            log.error(message);
        }
        return response;
    }
}
