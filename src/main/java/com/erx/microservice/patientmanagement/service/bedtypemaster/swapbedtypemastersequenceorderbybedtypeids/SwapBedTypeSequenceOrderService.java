package com.erx.microservice.patientmanagement.service.bedtypemaster.swapbedtypemastersequenceorderbybedtypeids;

import com.erx.microservice.patientmanagement.exception.ServiceException;
/*
 * created by Bahubali on 08-08-2018
 * */

public interface SwapBedTypeSequenceOrderService {

    SwapBedTypeSequenceOrderServiceResponse execute(SwapBedTypeSequenceOrderServiceRequest request) throws ServiceException;
}
