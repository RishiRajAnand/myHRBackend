package com.erx.microservice.patientmanagement.gateway;


import com.erx.microservice.patientmanagement.domain.SourceFrequencyWeb;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.service.dto.*;
import com.erx.microservice.patientmanagement.service.dto.casebillingorderdto.CreateBillingOrderInputDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.UpdateCaseOrderDTO;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Latha on 30/11/17.
 */
public interface ServiceGateway {

    String generateUniqueID(GenerateUniqueIDDTO generateUniqueIDDTO) throws Exception;

    List<VisitTypeMasterDTO> getVisitTypeMaster(Long clinicLocationId);

    JSONObject getInvoiceDetails(Long patientId);

    JSONObject getBillingInvoiceMaster(Long patientId);

    JSONObject saveBillingInvoiceMaster(BillNumberRequestDTO billNumberRequestDTO);

    JSONObject getPackageCatalogueByName(Long clinicLocationId, String packageName);

    JSONObject getPackageCatalogueById(Long packageCatalogueId);

    JSONObject generateCampOrder(CreateBillingOrderInputDTO createBillingOrderInputDTO);

    JSONObject generateCaseOrder(CreateBillingOrderInputDTO createBillingOrderInputDTO);

    JSONObject setScheduler(SourceFrequencyWeb sourceFrequencyWeb);

    JSONObject getServiceCatalogueRecurringRule(Long clinicLocationId);

    JSONObject getServiceCatalogueById(Long serviceCatalogueId);

    JSONObject generateSlotOrderByAdmissionTime(GenerateOrderByAdmissionTimeDTO generateOrderByAdmissionTimeDTO);

    JSONObject generateCutOffOrderByAdmissionTime(GenerateOrderByAdmissionTimeDTO generateOrderByAdmissionTimeDTO);

    boolean  cancelPatientAppointmentOrderAndDepositAmount(Long patientAppointmentId);

    JSONObject utilizePatientDepositValue(Long patientId, Long patientDepositId, double utilizedAmount) throws ServiceException;

    JSONObject mapNewPatientTypeWithAllServices(Long patientTypeId);

    JSONObject mapNewPatientTypeWithAllMedicineTypes(Long patientTypeId);

    Long getManufactureId(Long clinicId);

    JSONObject getUomMasterByUomMasterId(Long uomMasterId);

    JSONObject getMedicineTypeById(Long medicineTypeId);

    JSONObject getProductCatalogueCommonDetailById(Long productCatalogueCommonDetailId);

    JSONObject getDepartmentByDepartmentById(Long departmentId);


    JSONObject mapNewBedTypeWithAllServices(Long bedTypeId);

    JSONObject getServiceCatalogueByServiceId(Long departmentId);

    JSONObject mapNewBedTypeWithAllMedicineTypes(Long bedTypeId);

    JSONObject getServicesByModuleSectionMasterAndClinic(Long moduleSectionMasterId, Long clinicId, Long clinicLocationId);

    JSONObject getItemByOrderId(String orderNumber);

    JSONObject updateCaseOrder(UpdateCaseOrderDTO updateCaseOrderDTO);

    JSONObject getOrderDetailsByOrderId(Long bmOrderId);

    Long getIpAdmissionIdByOrderId(Long bmOrderId) ;

    JSONObject updateTherapyMedicine(CreateBillingOrderInputDTO createBillingOrderInputDTO);
}
