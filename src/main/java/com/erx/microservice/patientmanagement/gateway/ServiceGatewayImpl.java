package com.erx.microservice.patientmanagement.gateway;

import com.erx.microservice.patientmanagement.config.ErxProperties;
import com.erx.microservice.patientmanagement.config.RestTemplateConfig;
import com.erx.microservice.patientmanagement.domain.SourceFrequencyWeb;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.service.dto.*;
import com.erx.microservice.patientmanagement.service.dto.casebillingorderdto.CreateBillingOrderInputDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.UpdateCaseOrderDTO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Latha on 30/11/17.
 */

@Service
public class ServiceGatewayImpl implements ServiceGateway {

    private final Logger log = LoggerFactory.getLogger(ServiceGatewayImpl.class);

    @Autowired
    private RestTemplateConfig restTemplateConfig;

    @Autowired
    private ErxProperties erxProperties;

    @Override
    public String generateUniqueID(GenerateUniqueIDDTO generateUniqueIDDTO) throws Exception {
        ResponseEntity<String> responseBody = null;
        JSONObject jsonObject = null;
        String generatedUniqueID = null;
        try {
            String utilityServiceUrl = erxProperties.getMicroservicesReferences().getUtilityService() + "/api/getUniqueId";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");

            //Constructing generateUniqueID request
            HttpEntity<GenerateUniqueIDDTO> generateUniqueIDRequest = new HttpEntity<>(generateUniqueIDDTO, httpHeaders);
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().postForEntity(utilityServiceUrl, generateUniqueIDRequest, String.class);
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>uniqueId not generated: " + e.getMessage());
            throw new Exception("failed to generate unique id for non registered patients");
        }
        try {
            //extarcting responseBody
            jsonObject = new JSONObject(responseBody.getBody());
            //extarcting  generatedId
            generatedUniqueID = jsonObject.getString("generatedId");
        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>cannot retrieve uniqueId: " + e.getMessage());
        }
        return generatedUniqueID;
    }

    @Override
    public List<VisitTypeMasterDTO> getVisitTypeMaster(Long clinicLocationId) {
        ResponseEntity<String> responseBody = null;
        ResponseEntity<String> responseBodyExtract = null;
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        String visitTypeMasterId = null;
        String visitTypeMasterType = null;
        List<VisitTypeMasterDTO> visitTypeMasterDTOs = new ArrayList<>();
        log.debug("Call to getVisitTypeMasterByClinicLocationId");
        try {
            String utilityServiceUrl = erxProperties.getMicroservicesReferences().getBillingService()
                    + "/api/getAllVisitTypeMasters?clinicLocationId=" + clinicLocationId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(utilityServiceUrl, String.class);
            log.debug("getVisitTypeMasterByClinicLocationId");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>getVisitTypeMasterByClinicLocationId failed: " + e.getMessage());
        }
        try {
            //extarcting responseBody
            jsonObject = new JSONObject(responseBody.getBody().toString());
            jsonArray = new JSONArray(jsonObject.getString("visitTypeMasterDTOs"));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject objects = jsonArray.getJSONObject(i);
                VisitTypeMasterDTO visitTypeMasterDTO = new VisitTypeMasterDTO();
                visitTypeMasterId = objects.getString("id");
                visitTypeMasterType = objects.getString("visitType");
                visitTypeMasterDTO.setId(Long.valueOf(visitTypeMasterId));
                visitTypeMasterDTO.setVisitType(visitTypeMasterType);
                visitTypeMasterDTOs.add(visitTypeMasterDTO);
            }
        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>cannot retrieve visit type master Value: " + e.getMessage());
        }

        return visitTypeMasterDTOs;
    }

    //to get invoice details
    @Override
    public JSONObject getInvoiceDetails(Long patientId) {
        ResponseEntity<String> responseBody = null;
        String invoiceAmount = null;
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        log.debug("Call to getInvoiceDetailsByPatientId");
        try {
            String billingServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() + "/api/getInvoiceByPatientId";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //Constructing visitTypeMaster request
            HttpEntity<Long> getInvoiceDetails = new HttpEntity<>(patientId, httpHeaders);
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().postForEntity(billingServiceUrl, getInvoiceDetails, String.class);
            log.debug("getInvoiceDetailsByPatientId");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>getInvoiceDetailsByPatientId failed: " + e.getMessage());
        }
        try {
            //extracting responseBody
            jsonObject = new JSONObject(responseBody.getBody());

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>cannot retrieve Value: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject getBillingInvoiceMaster(Long patientId) {
        ResponseEntity<String> responseBody = null;
        ResponseEntity<String> responseBodyExtract = null;
        JSONObject jsonObject = null;
        log.debug("Call to getAllBillingInvoiceMasterByPatientId");
        try {
            String utilityServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/getAllInvoiceMasterByPatientId";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //Constructing visitTypeMaster request
            HttpEntity<Long> getVisitTypeMasterValueRequest = new HttpEntity<>(patientId, httpHeaders);
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().postForEntity(utilityServiceUrl,
                    getVisitTypeMasterValueRequest, String.class);
            log.debug("getAllBillingInvoiceMasterByPatientId");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>getVisitTypeMasterByClinicLocationId failed: " + e.getMessage());
        }
        try {
            //extracting responseBody
            jsonObject = new JSONObject(responseBody.getBody());

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>cannot retrieve visit type master Value: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject saveBillingInvoiceMaster(BillNumberRequestDTO billNumberRequestDTO) {
        ResponseEntity<String> responseBody = null;
        ResponseEntity<String> responseBodyExtract = null;
        JSONObject jsonObject = null;
        log.debug("Call to saveBillingInvoiceMaster");
        try {
            String utilityServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() + "/api/saveBillNumber";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //Constructing billNumber request
            HttpEntity<BillNumberRequestDTO> saveBillNumberRequest = new HttpEntity<>(billNumberRequestDTO, httpHeaders);
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().postForEntity(utilityServiceUrl, saveBillNumberRequest, String.class);
            log.debug("saveBillingInvoiceMaster");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>saveBillingInvoiceMaster failed: " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            }

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>saveBillingInvoiceMaster retrieve failed: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject getPackageCatalogueByName(Long clinicLocationId, String packageName) {
        ResponseEntity<String> responseBody = null;
        ResponseEntity<String> responseBodyExtract = null;
        JSONObject jsonObject = null;
        log.debug("Call to getPackageCatalogueByName");
        try {
            String utilityServiceUrl = erxProperties.getMicroservicesReferences().getBillingService()
                    + "/api/getPackageByName?clinicLocationId="
                    + clinicLocationId + "&packageName=" + packageName;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(utilityServiceUrl, String.class);
            log.debug("getPackageCatalogueByName ==> SUCCESS");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>getPackageCatalogueByName failed: " + e.getMessage());
        }
        try {
            //extracting responseBody
            jsonObject = new JSONObject(responseBody.getBody());

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>getPackageCatalogueByName retrieve failed: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject getPackageCatalogueById(Long packageCatalogueId) {
        ResponseEntity<String> responseBody = null;
        ResponseEntity<String> responseBodyExtract = null;
        JSONObject jsonObject = null;
        log.debug("Call to getPackageCatalogueById");
        try {
            String utilityServiceUrl = erxProperties.getMicroservicesReferences().getBillingService()
                    + "/api/getPackageCatalogueById?packageCatalogueId=" + packageCatalogueId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(utilityServiceUrl, String.class);
            log.debug("getPackageCatalogueById ==> SUCCESS");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>getPackageCatalogueById failed: " + e.getMessage());
        }
        try {
            //extracting responseBody
            jsonObject = new JSONObject(responseBody.getBody());

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>getPackageCatalogueByName retrieve failed: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject generateCampOrder(CreateBillingOrderInputDTO createBillingOrderInputDTO) {
        ResponseEntity<String> responseBody = null;
        ResponseEntity<String> responseBodyExtract = null;
        JSONObject jsonObject = null;
        log.debug("Call to  generate Camp Order ");
        try {
            String utilityServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/createBillingOrderWithContext";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //Constructing billNumber request
            HttpEntity<CreateBillingOrderInputDTO> createBillingOrderInputDTORequest = new HttpEntity<>(createBillingOrderInputDTO, httpHeaders);
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().postForEntity(utilityServiceUrl, createBillingOrderInputDTORequest, String.class);
            log.debug("generateCampOrder");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>In generating Camp Order  failed : " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            }

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>In generating Camp Order  failed: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject generateCaseOrder(CreateBillingOrderInputDTO createBillingOrderInputDTO) {
        ResponseEntity<String> responseBody = null;
        ResponseEntity<String> responseBodyExtract = null;
        JSONObject jsonObject = null;
        log.debug("Call to  generate Case Order ");
        try {
            String billingServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/createBillingOrderWithContext";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //Constructing billNumber request
            HttpEntity<CreateBillingOrderInputDTO> createBillingOrderInputDTORequest = new HttpEntity<>(createBillingOrderInputDTO, httpHeaders);
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().postForEntity(billingServiceUrl, createBillingOrderInputDTORequest, String.class);
            log.debug("generateCaseOrder");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>In generating Case Order  failed : " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            }

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>In generating Case Order  failed: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject setScheduler(SourceFrequencyWeb sourceFrequencyWeb) {
        ResponseEntity<String> responseBody = null;
        JSONObject jsonObject = null;
        log.debug("Call to  setScheduler");
        try {
            String utilityServiceUrl = erxProperties.getMicroservicesReferences().getUtilityService() +
                    "/api/scheduler/setScheduler";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //Constructing billNumber request
            HttpEntity<SourceFrequencyWeb> setSchedulerRequest = new HttpEntity<>(sourceFrequencyWeb, httpHeaders);
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().postForEntity(utilityServiceUrl,
                    setSchedulerRequest, String.class);
            log.debug("setScheduler");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==> setScheduler  failed : " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            }

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>setScheduler failed: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject getServiceCatalogueRecurringRule(Long clinicLocationId) {
        ResponseEntity<String> responseBody = null;
        JSONObject jsonObject = null;
        log.debug("Call to  setScheduler");
        try {
            String utilityServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/onloadServiceCatalogueRecurringRule?clinicLocationId=" + clinicLocationId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(utilityServiceUrl, String.class);
            log.debug("getServiceCatalogueRecurringRule");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==> get ServiceCatalogueRecurringRule  failed : " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            }

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>getServiceCatalogueRecurringRule  failed: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject getServiceCatalogueById(Long serviceCatalogueId) {
        ResponseEntity<String> responseBody = null;
        ResponseEntity<String> responseBodyExtract = null;
        JSONObject jsonObject = null;
        log.debug("Call to getServiceCatalogueById");
        try {
            String utilityServiceUrl = erxProperties.getMicroservicesReferences().getBillingService()
                    + "/api/getServiceCatalogueById/" + serviceCatalogueId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(utilityServiceUrl, String.class);
            log.debug("getServiceCatalogueById ==> SUCCESS");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>getServiceCatalogueById failed: " + e.getMessage());
        }
        try {
            if (responseBody != null)
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>getServiceCatalogueById retrieve failed: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject generateSlotOrderByAdmissionTime(GenerateOrderByAdmissionTimeDTO generateOrderByAdmissionTimeDTO) {
        ResponseEntity<String> responseBody = null;
        JSONObject jsonObject = null;
        log.debug("Call to generateSlotOrderByAdmissionTime");
        try {
            String utilityServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/schedulerManagement/generateSlotOrderByAdmissionTime";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //Constructing billNumber request
            HttpEntity<GenerateOrderByAdmissionTimeDTO> setSchedulerRequest = new
                    HttpEntity<>(generateOrderByAdmissionTimeDTO, httpHeaders);
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().postForEntity(utilityServiceUrl,
                    setSchedulerRequest, String.class);
            log.debug("generateSlotOrderByAdmissionTime");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==> Error==> generateSlotOrderByAdmissionTime  failed : " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            }

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==> Error==> generateSlotOrderByAdmissionTime failed: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject generateCutOffOrderByAdmissionTime(GenerateOrderByAdmissionTimeDTO generateOrderByAdmissionTimeDTO) {
        ResponseEntity<String> responseBody = null;
        JSONObject jsonObject = null;
        log.debug("Call to generateCutOffOrderByAdmissionTime");
        try {
            String utilityServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/schedulerManagement/generateCutOffOrderByAdmissionTime";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //Constructing billNumber request
            HttpEntity<GenerateOrderByAdmissionTimeDTO> setSchedulerRequest = new
                    HttpEntity<>(generateOrderByAdmissionTimeDTO, httpHeaders);
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().postForEntity(utilityServiceUrl,
                    setSchedulerRequest, String.class);
            log.debug("generateCutOffOrderByAdmissionTime");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==> Error==> generateCutOffOrderByAdmissionTime  failed : " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            }

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==> Error==> generateCutOffOrderByAdmissionTime failed: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public boolean cancelPatientAppointmentOrderAndDepositAmount(Long patientAppointmentId) {
        ResponseEntity<String> responseBody = null;
        log.debug("Call to cancelPatientAppointmentOrderAndDepositAmount");
        boolean status = false;
        try {
            String billingServiceUrl = erxProperties.getMicroservicesReferences().getBillingService()
                    + "/api/cancelPatientAppointmentOrderAndDepositAmount?patientAppointmentId=" + patientAppointmentId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(billingServiceUrl, String.class);
            if (responseBody != null) {
                status = true;
            }
            log.debug("cancelPatientAppointmentOrderAndDepositAmount ==> SUCCESS");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>cancelPatientAppointmentOrderAndDepositAmount failed: " + e.getMessage());
            status = false;
        }
        return status;
    }

    //update utilized deposit amount
    @Override
    public JSONObject utilizePatientDepositValue(Long patientId, Long patientDepositId, double utilizedAmount) throws ServiceException {

        ResponseEntity<String> responseBody = null;
        log.debug("Call utilizePatientDepositValue Service");
        JSONObject jsonObject = null;
        try {
            String patientManagementServiceServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/utilizeDepositValueByPatientIdAndAccountNameId?patientId=" + patientId + "&accountNameId=" + patientDepositId
                    + "&utilizedAmount=" + utilizedAmount;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(patientManagementServiceServiceUrl, String.class);
            log.debug("genericServiceRequest");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>utilizePatientDepositValue failed: " + e.getMessage());
        }
        try {
            //extracting responseBody
            jsonObject = new JSONObject(responseBody.getBody().toString());
        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>utilizePatientDepositValue-->Error==>" + e.getMessage());
        }
        return jsonObject;
    }


    @Override
    public JSONObject mapNewPatientTypeWithAllServices(Long patientTypeId) {
        ResponseEntity<String> responseBody = null;
        JSONObject jsonObject = null;
        String utilityServiceUrl = null;
        try {
            utilityServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/serviceCatalogue/generatePatientTypeForService?patientTypeId=" + patientTypeId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(utilityServiceUrl, String.class);
            log.debug("mapNewPatientTypeWithAllServices .");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>mapNewPatientTypeWithAllServices failed: " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            }
        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>mapNewPatientTypeWithAllServices failed: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject mapNewPatientTypeWithAllMedicineTypes(Long patientTypeId) {
        ResponseEntity<String> responseBody = null;
        JSONObject jsonObject = null;
        String utilityServiceUrl = null;
        try {
            utilityServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/generatePatientTypeForMedicineTypes?patientTypeId=" + patientTypeId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(utilityServiceUrl, String.class);
            log.debug("mapNewPatientTypeWithAllMedicineTypes .");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>mapNewPatientTypeWithAllMedicineTypes failed: " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            }
        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>mapNewPatientTypeWithAllMedicineTypes failed: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public Long getManufactureId(Long clinicId) {
        ResponseEntity<String> responseBody = null;
        JSONObject jsonObject = null;
        Long getManufactureId = null;
        try {
            String billingServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() + "/api/getManufactureId?clinicId=" + clinicId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");

            //Constructing manufactureId request
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(billingServiceUrl, String.class);
            log.debug("get manufacture Id.");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>manufacture id not available: " + e.getMessage());
        }
        try {
            //extarcting responseBody
            jsonObject = new JSONObject(responseBody.getBody());
            //extarcting  generatedId
            getManufactureId = jsonObject.getLong("manufactureId");
        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>cannot retrieve manufacture id: " + e.getMessage());
        }
        return getManufactureId;
    }

    @Override
    public JSONObject getUomMasterByUomMasterId(Long uomMasterId) {
        ResponseEntity<String> responseBody = null;
        JSONObject jsonObject = null;
        String billingServiceUrl = null;
        try {
            billingServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/getUomMasterById?uomMasterId=" + uomMasterId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(billingServiceUrl, String.class);
            log.debug("get uom master by uom master id.");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>get uom master by uom master id failed: " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            }
        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>get uom master by uom master id failed: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject getMedicineTypeById(Long medicineTypeId) {
        ResponseEntity<String> responseBody = null;
        JSONObject jsonObject = null;
        String billingServiceUrl = null;
        try {
            billingServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/getMedicineTypeById?medicineTypeId=" + medicineTypeId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(billingServiceUrl, String.class);
            log.debug("get medicine type by medicine type id.");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>get medicine type by medicine type id failed: " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            }
        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>get medicine type by medicine type id failed: " + e.getMessage());
        }
        return jsonObject;
    }


    @Override
    public JSONObject getProductCatalogueCommonDetailById(Long productCatalogueCommonDetailId) {
        ResponseEntity<String> responseBody = null;
        JSONObject jsonObject = null;
        String billingServiceUrl = null;
        try {
            billingServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/productCatalogue/getProductCatalogueCommonDetailById?productCatalogueCommonDetailId=" + productCatalogueCommonDetailId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(billingServiceUrl, String.class);
            log.debug("get product name by product common detail id.");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>get product name by product common detail id failed: " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            }
        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>get product name by product common detail id failed: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject getDepartmentByDepartmentById(Long departmentId) {
        ResponseEntity<String> responseBody = null;
        JSONObject jsonObject = null;
        String utilityServiceUrl = null;
        try {
            utilityServiceUrl = erxProperties.getMicroservicesReferences().getUtilityService() +
                    "/api/getDepartmentById?id=" + departmentId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(utilityServiceUrl, String.class);
            log.debug("get department master by department master id.");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>get department master by department master id failed: " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            }
        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>get department master by department master id failed: " + e.getMessage());
        }
        return jsonObject;
    }


    @Override
    public JSONObject mapNewBedTypeWithAllServices(Long bedTypeId) {
        ResponseEntity<String> responseBody = null;
        JSONObject jsonObject = null;
        String billingServiceUrl = null;
        try {
            billingServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/serviceCatalogue/generateBedTypeRatePlanForServiceCatalogue?bedTypeId=" + bedTypeId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(billingServiceUrl, String.class);
            log.debug("mapNewBedTypeWithAllServices .");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>mapNewBedTypeWithAllServices failed: " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            }
        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>mapNewBedTypeWithAllServices failed: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject getServiceCatalogueByServiceId(Long serviceCatalogueId) {
        ResponseEntity<String> responseBody = null;
        ResponseEntity<String> responseBodyExtract = null;
        JSONObject jsonObject = null;
        log.debug("Call to getServiceCatalogueById");
        try {
            String billingServiceUrl = erxProperties.getMicroservicesReferences().getBillingService()
                    + "/api/getServiceCatalogueByServiceId?serviceId=" + serviceCatalogueId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(billingServiceUrl, String.class);
            log.debug("getServiceCatalogueById ==> SUCCESS");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>getServiceCatalogueByServiceId failed: " + e.getMessage());
        }
        try {
            if (responseBody != null)
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>getServiceCatalogueByServiceId retrieve failed: " + e.getMessage());

        }
        return jsonObject;
    }

    @Override
    public JSONObject mapNewBedTypeWithAllMedicineTypes(Long bedTypeId) {
        ResponseEntity<String> responseBody = null;
        JSONObject jsonObject = null;
        String billingServiceUrl = null;
        try {
            billingServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/generateBedTypeRatePlanForMedicineTypes?bedTypeId=" + bedTypeId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(billingServiceUrl, String.class);
            log.debug("mapNewBedTypeWithAllMedicineTypes .");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>mapNewBedTypeWithAllMedicineTypes failed: " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            }
        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>mapNewBedTypeWithAllMedicineTypes failed: " + e.getMessage());
        }
        return jsonObject;

    }

    //get services by module section master and clinic
    @Override
    public JSONObject getServicesByModuleSectionMasterAndClinic(Long moduleSectionMasterId, Long clinicId, Long clinicLocationId) {

        ResponseEntity<String> responseBody = null;
        log.debug("Call to get services by module section master by module section and clinic");
        JSONObject jsonObject = null;
        try {
            String billingServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/serviceCatalogue/getServicesByModuleSectionMasterAndClinic?moduleSectionMasterId=" + moduleSectionMasterId + "&clinicId=" + clinicId
                    + "&clinicLocationId=" + clinicLocationId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(billingServiceUrl, String.class);
            log.debug("getServicesByModuleSectionMasterAndClinic ==> SUCCESS");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>getServicesByModuleSectionMasterAndClinic failed: " + e.getMessage());
        }
        try {
            //extracting responseBody
            jsonObject = new JSONObject(responseBody.getBody().toString());
        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>getServicesByModuleSectionMasterAndClinic -->Error==>" + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject getItemByOrderId(String orderNumber) {
        ResponseEntity<String> responseBody = null;
        ResponseEntity<String> responseBodyExtract = null;
        JSONObject jsonObject = null;
        log.debug("Call to getItemByOrderId");
        try {
            String billingServiceUrl = erxProperties.getMicroservicesReferences().getBillingService()
                    + "/api/getItemByOrderId?orderId=" + orderNumber;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(billingServiceUrl, String.class);
            log.debug("getItemByOrderId ==> SUCCESS");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>getItemByOrderId failed: " + e.getMessage());
        }
        try {
            if (responseBody != null)
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>getItemByOrderId retrieve failed: " + e.getMessage());

        }
        return jsonObject;
    }

    @Override
    public JSONObject updateCaseOrder(UpdateCaseOrderDTO updateCaseOrderDTO) {
        ResponseEntity<String> responseBody = null;
        ResponseEntity<String> responseBodyExtract = null;
        JSONObject jsonObject = null;
        log.debug("Call to update case order ");
        try {
            String billingServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/updateCaseOrder";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            HttpEntity<UpdateCaseOrderDTO> updateCaseOrderDTORequest = new HttpEntity<>(updateCaseOrderDTO, httpHeaders);
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().postForEntity(billingServiceUrl, updateCaseOrderDTORequest, String.class);
            log.debug("updateCaseOrder");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>In update Case Order failed : " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            }

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>In update Case Order failed: " + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject getOrderDetailsByOrderId(Long bmOrderId) {
        ResponseEntity<String> responseBody = null;
        ResponseEntity<String> responseBodyExtract = null;
        JSONObject jsonObject = null;
        log.debug("Call to getOrderDetailsByOrderId");
        try {
            String billingServiceUrl = erxProperties.getMicroservicesReferences().getBillingService()
                    + "/api/getOrderByOrderId?orderId=" + bmOrderId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(billingServiceUrl, String.class);
            log.debug("getItemByOrderId ==> SUCCESS");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>getOrderDetailsByOrderId failed: " + e.getMessage());
        }
        try {
            if (responseBody != null)
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>getOrderDetailsByOrderId retrieve failed: " + e.getMessage());

        }
        return jsonObject;
    }


    @Override
    public Long getIpAdmissionIdByOrderId(Long bmOrderId) {
        ResponseEntity<String> responseBody = null;
        ResponseEntity<String> responseBodyExtract = null;
        JSONObject jsonObject = null;
        log.debug("Call to get IPAdmissionIdByOrderId ");
        Long ipAdmissionId = null;
        try {
            String billingServiceUrl = erxProperties.getMicroservicesReferences().getBillingService()
                    + "/api/getIPAdmissionIdByOrderId?orderId=" + bmOrderId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().getForEntity(billingServiceUrl, String.class);
            log.debug("getItemByOrderId ==> SUCCESS");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>getOrderDetailsByOrderId failed: " + e.getMessage());
        }
        try {
            if (responseBody != null)
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody());
            if(!jsonObject.getString("ipAdmissionId").equalsIgnoreCase("null")) {
                ipAdmissionId = jsonObject.getLong("ipAdmissionId");
            }

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>getOrderDetailsByOrderId retrieve failed: " + e.getMessage());

        }
        return ipAdmissionId;
    }

   public JSONObject updateTherapyMedicine(CreateBillingOrderInputDTO createBillingOrderInputDTO){
        ResponseEntity<String> responseBody = null;
        ResponseEntity<String> responseBodyExtract = null;
        JSONObject jsonObject = null;
        log.debug("Call to  update Medicine Order failed ");
        try {
            String billingServiceUrl = erxProperties.getMicroservicesReferences().getBillingService() +
                    "/api/createBillingOrderWithContext";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            HttpEntity<CreateBillingOrderInputDTO> updateCaseOrderDTORequest = new HttpEntity<>(createBillingOrderInputDTO, httpHeaders);
            //calling and getting response
            responseBody = restTemplateConfig.restTemplate().postForEntity(billingServiceUrl, updateCaseOrderDTORequest, String.class);
            log.debug("updateCaseOrder");
        } catch (Exception e) {
            log.error("ServiceGatewayImpl ==>Error==>In update Medicine Order failed : " + e.getMessage());
        }
        try {
            if (responseBody != null) {
                //extracting responseBody
                jsonObject = new JSONObject(responseBody.getBody()); }

        } catch (JSONException e) {
            log.error("ServiceGatewayImpl ==>Error==>In update Medicine Order failed: " + e.getMessage());
        }
        return jsonObject;
    }
}
