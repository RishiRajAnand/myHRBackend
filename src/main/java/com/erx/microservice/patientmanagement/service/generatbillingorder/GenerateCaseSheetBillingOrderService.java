package com.erx.microservice.patientmanagement.service.generatbillingorder;

/*
* created by Latha on 21-09-2018
* */

import com.erx.microservice.patientmanagement.service.dto.casebillingorderdto.CreateBillingOrderInputDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.UpdateCaseOrderDTO;
import org.json.JSONObject;

public interface GenerateCaseSheetBillingOrderService {
    JSONObject generateOrderForCase(CreateBillingOrderInputDTO createBillingOrderInputDTO);

    JSONObject updateOrderForCase(UpdateCaseOrderDTO updateCaseOrderDTO);
}
