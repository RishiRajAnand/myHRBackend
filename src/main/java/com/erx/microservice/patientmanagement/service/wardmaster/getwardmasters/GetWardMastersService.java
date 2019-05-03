package com.erx.microservice.patientmanagement.service.wardmaster.getwardmasters;

/*
* created by Brighty on 16-11-2017
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetWardMastersService {

    GetWardMastersServiceResponse execute(GetWardMastersServiceRequest request) throws ServiceException;
}
