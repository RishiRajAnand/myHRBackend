package com.erx.microservice.patientmanagement.service.ipadmission.getbedtransferdetailsbyippatient;

/*
* created by Latha on 29-11-2017.
* */


import org.hibernate.service.spi.ServiceException;

public interface GetBedTransferDetailsByIpService {
    GetBedTransferDetailsByIpServiceResponse execute(GetBedTransferDetailsByIpServiceRequest request) throws ServiceException;
}
