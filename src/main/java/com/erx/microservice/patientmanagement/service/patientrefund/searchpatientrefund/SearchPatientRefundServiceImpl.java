package com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund;
/*
* created by Raushan on 13-02-18
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SearchPatientRefundServiceImpl implements SearchPatientRefundService {

    private final Logger log = LoggerFactory.getLogger(SearchPatientRefundServiceImpl.class);

    @Autowired
    PatientRefundSearchFactory patientRefundSearchFactory;

    @Override
    public CommonServiceResponse execute(SearchPatientRefundServiceRequest request) throws ServiceException {
        //Factory pattern to search and get PatientRefund Detail
        return patientRefundSearchFactory.execute(request);
    }

}
