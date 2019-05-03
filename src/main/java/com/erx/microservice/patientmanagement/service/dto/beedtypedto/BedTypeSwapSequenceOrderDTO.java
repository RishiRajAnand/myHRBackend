package com.erx.microservice.patientmanagement.service.dto.beedtypedto;

/*
 * created by Bahubali on 08-08-2018
 * */
public class BedTypeSwapSequenceOrderDTO {
    private Long fromBedTypeMasterId;
    private Long fromBedTypeSequenceOrderNo;
    private Long toBedTypeMasterId;
    private Long toBedTypeSequenceOrderNo;

    public Long getFromBedTypeMasterId() {
        return fromBedTypeMasterId;
    }

    public void setFromBedTypeMasterId(Long fromBedTypeMasterId) {
        this.fromBedTypeMasterId = fromBedTypeMasterId;
    }

    public Long getFromBedTypeSequenceOrderNo() {
        return fromBedTypeSequenceOrderNo;
    }

    public void setFromBedTypeSequenceOrderNo(Long fromBedTypeSequenceOrderNo) {
        this.fromBedTypeSequenceOrderNo = fromBedTypeSequenceOrderNo;
    }

    public Long getToBedTypeMasterId() {
        return toBedTypeMasterId;
    }

    public void setToBedTypeMasterId(Long toBedTypeMasterId) {
        this.toBedTypeMasterId = toBedTypeMasterId;
    }

    public Long getToBedTypeSequenceOrderNo() {
        return toBedTypeSequenceOrderNo;
    }

    public void setToBedTypeSequenceOrderNo(Long toBedTypeSequenceOrderNo) {
        this.toBedTypeSequenceOrderNo = toBedTypeSequenceOrderNo;
    }
}
