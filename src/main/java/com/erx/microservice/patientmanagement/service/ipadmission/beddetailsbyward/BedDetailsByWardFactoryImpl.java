package com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward;

import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.GetBedDetailsByWardIdServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.GetBedDetailsByWardIdServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.gedallbeddetails.GetAllBedDetailsService;
import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.gedbeddetailsbywardid.GetBedDetailsByWardIdService;
import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.gedbeddetailsbywardidanddoctorid.GetBedDetailsByWardIdAndDoctorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* created by Brighty on 28-04-2018
* */

@Service("bedDetailsByWardFactory")
public class BedDetailsByWardFactoryImpl implements BedDetailsByWardFactory {

    @Autowired
    private GetBedDetailsByWardIdService getBedDetailsByWardIdService;

    @Autowired
    private GetBedDetailsByWardIdAndDoctorIdService getBedDetailsByWardIdAndDoctorIdService;

    @Autowired
    private GetAllBedDetailsService getAllBedDetailsService;

    @Override
    public GetBedDetailsByWardIdServiceResponse getBedDetailsByWard(GetBedDetailsByWardIdServiceRequest request) {

        switch (request.getType()) {
            case "GENERAL":
                return getBedDetailsByWardIdService.execute(request);
            case "DOCTORBEDS":
                return getBedDetailsByWardIdAndDoctorIdService.execute(request);
            case "ALL":
                return getAllBedDetailsService.execute(request);

            default:
                return getBedDetailsByWardIdService.execute(request);
        }

    }

}
