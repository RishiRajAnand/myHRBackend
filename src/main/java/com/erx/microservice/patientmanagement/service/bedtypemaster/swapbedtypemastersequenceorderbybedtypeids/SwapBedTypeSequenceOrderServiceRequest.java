package com.erx.microservice.patientmanagement.service.bedtypemaster.swapbedtypemastersequenceorderbybedtypeids;


import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.beedtypedto.BedTypeSwapSequenceOrderDTO;

/*
 * created by Bahubali on 08-08-2018
 * */
public class SwapBedTypeSequenceOrderServiceRequest implements CommonServiceRequest {

    private BedTypeSwapSequenceOrderDTO bedTypeSwapSequenceOrderDTO;

    //constructor
    public SwapBedTypeSequenceOrderServiceRequest(BedTypeSwapSequenceOrderDTO bedTypeSwapSequenceOrderDTO) {
        this.bedTypeSwapSequenceOrderDTO = bedTypeSwapSequenceOrderDTO;
    }

    public BedTypeSwapSequenceOrderDTO getBedTypeSwapSequenceOrderDTO() {
        return bedTypeSwapSequenceOrderDTO;
    }

    //getters and setters
    public void setBedTypeSwapSequenceOrderDTO(BedTypeSwapSequenceOrderDTO bedTypeSwapSequenceOrderDTO) {
        this.bedTypeSwapSequenceOrderDTO = bedTypeSwapSequenceOrderDTO;
    }
}
