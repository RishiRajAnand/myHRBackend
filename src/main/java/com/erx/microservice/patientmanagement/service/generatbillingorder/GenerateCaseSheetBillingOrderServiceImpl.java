package com.erx.microservice.patientmanagement.service.generatbillingorder;

/*
* created by Latha on 21-09-2018
* */

import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.service.dto.casebillingorderdto.CreateBillingOrderInputDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.UpdateCaseOrderDTO;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("generateCaseSheetBillingOrderService")
public class GenerateCaseSheetBillingOrderServiceImpl implements GenerateCaseSheetBillingOrderService{

    private final Logger log = LoggerFactory.getLogger(GenerateCaseSheetBillingOrderServiceImpl.class);

    @Autowired
    private ServiceGateway serviceGateway;

    // method is to generate order for case
    @Override
    public JSONObject generateOrderForCase(CreateBillingOrderInputDTO createBillingOrderInputDTO) {
        JSONObject jsonObject = null;
        try {
            log.debug("GenerateCaseSheetBillingOrderServiceImpl ----> Call to generate order for case");
            jsonObject = serviceGateway.generateCaseOrder(createBillingOrderInputDTO);
        } catch (Exception e) {
            log.debug("GenerateCaseSheetBillingOrderServiceImpl ----> Failed to generate order for case" + e.getMessage());
        }
        return jsonObject;
    }

    //method to update order for case
    @Override
    public JSONObject updateOrderForCase(UpdateCaseOrderDTO updateCaseOrderDTO) {
        JSONObject jsonObject = null;
        try {
            log.debug("GenerateCaseSheetBillingOrderServiceImpl ----> Call to update order for case");
            jsonObject = serviceGateway.updateCaseOrder(updateCaseOrderDTO);
        } catch (Exception e) {
            log.debug("GenerateCaseSheetBillingOrderServiceImpl ----> Failed to update order for case" + e.getMessage());
        }
        return jsonObject;
    }
}
