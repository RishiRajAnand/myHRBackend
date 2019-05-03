/*
* created by latha on 01-10-2018
* */
package com.erx.microservice.patientmanagement.service.casemanagement.getitembyorderid;

import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.BillingOrderDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.BillingOrderServicePackageProductDTO;
import org.hibernate.service.spi.ServiceException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("getItemByOrderIdService")
public class GetItemByOrderIdServiceImpl implements GetItemByOrderIdService {

    private final static Logger log = LoggerFactory.getLogger(GetItemByOrderIdServiceImpl.class);

    @Autowired
    private ServiceGateway serviceGateway;

    @Override
    public GetItemByOrderIdServiceResponse execute(GetItemByOrderIdServiceRequest request) throws ServiceException {
        GetItemByOrderIdServiceResponse response = null;
        BillingOrderDTO billingOrderDTO = new BillingOrderDTO();
        List<BillingOrderServicePackageProductDTO> billingOrderServicePackageProductDTOs = new ArrayList<>();
        try {
            log.debug("GetItemByOrderIdServiceImpl -----> Call to get item by order number");
            JSONObject jsonServiceObject = serviceGateway.getItemByOrderId(request.getOrderId());
            if (jsonServiceObject != null) {
                JSONObject serviceObjectJSONObject = jsonServiceObject.getJSONObject("billingOrderDTO");
                JSONArray jsonArray = new JSONArray(jsonServiceObject.getString("billingOrderServicePackageProductDTOs"));
                if(serviceObjectJSONObject != null){
                    billingOrderDTO.setId(serviceObjectJSONObject.getLong("id"));
                    billingOrderDTO.setOrderId(serviceObjectJSONObject.getString("orderId"));
                    billingOrderDTO.setOrderName(serviceObjectJSONObject.getString("orderName"));
                }
                if(jsonArray != null){
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objects = jsonArray.getJSONObject(i);
                        BillingOrderServicePackageProductDTO billingOrderServicePackageProductDTO = new BillingOrderServicePackageProductDTO();
                        billingOrderServicePackageProductDTO.setQuantity(objects.getInt("quantity"));
                        billingOrderServicePackageProductDTO.setCode(objects.getString("code"));
                        billingOrderServicePackageProductDTO.setType(objects.getString("type"));
                        billingOrderServicePackageProductDTO.setName(objects.getString("name"));
                        billingOrderServicePackageProductDTO.setServicePackageProductId(objects.getLong("servicePackageProductId"));
                        billingOrderServicePackageProductDTO.setId(objects.getLong("id"));
                        billingOrderServicePackageProductDTO.setStatus(objects.getString("status"));
                        billingOrderServicePackageProductDTOs.add(billingOrderServicePackageProductDTO);
                    }
                }
            }
            //create response
            response = new GetItemByOrderIdServiceResponse(billingOrderDTO, billingOrderServicePackageProductDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved Order Status by Order Id Successfully for given orderId =" + request.getOrderId());
            log.debug("Retrieved Order Status by Order Id Successfully for given orderId =" + request.getOrderId());
        } catch (JSONException e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Order Status for given orderId");
            log.error("Failed to retrieve Order Status for given orderId");
        }
        return response;
    }
}
