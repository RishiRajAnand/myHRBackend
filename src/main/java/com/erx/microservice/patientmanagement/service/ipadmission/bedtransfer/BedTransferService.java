package com.erx.microservice.patientmanagement.service.ipadmission.bedtransfer;

/*
* created by Latha on 29-11-2017
* */

import org.hibernate.service.spi.ServiceException;

public interface BedTransferService {
    BedTransferServiceResponse execute(BedTransferServiceRequest request) throws ServiceException;
}
