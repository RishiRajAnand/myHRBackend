package com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund;

import com.erx.microservice.patientmanagement.domain.PatientRefundSearchTypes;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund.getpatientrefundbycliniclocation.GetPatientRefundByClinicLocationService;
import com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund.getpatientrefundbymobile.GetPatientRefundByMobile;
import com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund.getpatientrefundbymrn.GetPatientRefundByMRN;
import com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund.getpatientrefundbyname.GetPatientRefundByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/*
* created by Raushan on 14-02-18
* */


@Service
public class PatientRefundSearchFactoryImpl implements PatientRefundSearchFactory {
    @Autowired
    private GetPatientRefundByMobile getPatientRefundByMobile;

    @Autowired
    private GetPatientRefundByMRN getPatientRefundByMRN;

    @Autowired
    private GetPatientRefundByName getPatientRefundByName;

    @Autowired
    private GetPatientRefundByClinicLocationService getPatientRefundByClinicLocationService;


    //use searchType to get respective query builders
    @Override
    public CommonServiceResponse execute(SearchPatientRefundServiceRequest request) throws ServiceException {

        switch (PatientRefundSearchTypes.valueOf(request.getPatientSearchCriteria().getSearchType())) {
            case MRN:
                return getPatientRefundByMRN.execute(request);
            case MOBILE:
                return getPatientRefundByMobile.execute(request);
            case NAME:
                return getPatientRefundByName.execute(request);
            default:
                return getPatientRefundByClinicLocationService.execute(request);
        }

    }

}