/*
* created by latha on 01-10-2018
* */
package com.erx.microservice.patientmanagement.service.casemanagement.getitembyorderid;

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.BillingOrderDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.BillingOrderServicePackageProductDTO;

import java.util.List;


public class GetItemByOrderIdServiceResponse extends CommonServiceResponse {

    private BillingOrderDTO billingOrderDTO;
    private List<BillingOrderServicePackageProductDTO> billingOrderServicePackageProductDTOs;

    //Constructor


    public GetItemByOrderIdServiceResponse(BillingOrderDTO billingOrderDTO, List<BillingOrderServicePackageProductDTO> billingOrderServicePackageProductDTOs) {
        this.billingOrderDTO = billingOrderDTO;
        this.billingOrderServicePackageProductDTOs = billingOrderServicePackageProductDTOs;
    }

    public GetItemByOrderIdServiceResponse() {
    }

    //getter and setter

    public BillingOrderDTO getBillingOrderDTO() {
        return billingOrderDTO;
    }

    public void setBillingOrderDTO(BillingOrderDTO billingOrderDTO) {
        this.billingOrderDTO = billingOrderDTO;
    }

    public List<BillingOrderServicePackageProductDTO> getBillingOrderServicePackageProductDTOs() {
        return billingOrderServicePackageProductDTOs;
    }

    public void setBillingOrderServicePackageProductDTOs(List<BillingOrderServicePackageProductDTO> billingOrderServicePackageProductDTOs) {
        this.billingOrderServicePackageProductDTOs = billingOrderServicePackageProductDTOs;
    }
}