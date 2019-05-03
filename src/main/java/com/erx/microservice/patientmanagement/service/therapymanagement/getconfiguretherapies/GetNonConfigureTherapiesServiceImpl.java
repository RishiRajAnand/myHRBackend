package com.erx.microservice.patientmanagement.service.therapymanagement.getconfiguretherapies;

/*
* created by Latha on 27-09-2018
* */


import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyMaster;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.NonConfigureTherapiesDTO;
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


@Service("getNonConfigureTherapiesService")
public class GetNonConfigureTherapiesServiceImpl implements GetNonConfigureTherapiesService {

    private final Logger log = LoggerFactory.getLogger(GetNonConfigureTherapiesServiceImpl.class);

    @Autowired
    private ServiceGateway serviceGateway;

    @Autowired
    private TherapyMasterRepository therapyMasterRepository;

    @Override
    public GetNonConfigureTherapiesServiceResponse execute(GetNonConfigureTherapiesServiceRequest request) throws ServiceException {

        GetNonConfigureTherapiesServiceResponse response = new GetNonConfigureTherapiesServiceResponse();
        List<NonConfigureTherapiesDTO> nonConfigureTherapiesDTOs = null;
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;
        Long serviceCatalogueId = null;
        String serviceName = null;
        String departmentId = null;
        String departmentName = null;
        String catalogueCategoryName = null;
        List<TherapyMaster> therapyMasters = new ArrayList<>();

        log.debug("GetNonConfigureTherapiesServiceImpl ==> Call to get non configure therapies");
        try {

            therapyMasters = therapyMasterRepository.findTherapiesByClinic(request.getClinicId());
            jsonObject = serviceGateway.getServicesByModuleSectionMasterAndClinic(request.getModuleSectionMasterId(),request.getClinicId(),request.getClinicLocationId());
            if(jsonObject != null){
                jsonArray = new JSONArray(jsonObject.getString("serviceByModuleSectionDTOs"));
                if(jsonArray != null)
                    nonConfigureTherapiesDTOs = new ArrayList<>();
                NonConfigureTherapiesDTO nonConfigureTherapiesDTO = null;

                    if(therapyMasters != null){
                        for (int i = 0; i < jsonArray.length(); i++) {
                            boolean nonConfigureTherapy = false;
                            JSONObject objects = jsonArray.getJSONObject(i);
                            serviceCatalogueId = objects.getLong("serviceCatalogueId");
                            serviceName = objects.getString("serviceName");
                            departmentId = objects.getString("departmentId");
                            departmentName = objects.getString("departmentName");
                            catalogueCategoryName =  objects.getString("catalogueCategoryName");
                        for(TherapyMaster therapyMaster : therapyMasters){
                            if(therapyMaster.getServiceCatalogueId() != null)
                            if(therapyMaster.getServiceCatalogueId().equals(serviceCatalogueId)){
                                nonConfigureTherapy = true;
                                break;
                            }
                        }
                        if(nonConfigureTherapy == true){
                            continue;
                        }
                     if(nonConfigureTherapy == false) {
                           nonConfigureTherapiesDTO = new NonConfigureTherapiesDTO();
                           nonConfigureTherapiesDTO.setServiceCatalogueId(serviceCatalogueId);
                           nonConfigureTherapiesDTO.setServiceName(serviceName);
                           if (departmentId != "null") {
                               nonConfigureTherapiesDTO.setDepartmentId(Long.valueOf(departmentId));
                           }
                           nonConfigureTherapiesDTO.setDepartmentName(departmentName);
                           nonConfigureTherapiesDTO.setCatalogueCategoryName(catalogueCategoryName);
                           nonConfigureTherapiesDTOs.add(nonConfigureTherapiesDTO);

                      }
                     }
                }
            }
            response.SUCCESSFUL = true;
            response.setNonConfigureTherapiesDTOs(nonConfigureTherapiesDTOs);
            response.setMessage("Retrieved Non Configure Therapies Successfully");
            log.debug("Retrieved Non Configure Therapies Successfully");
        } catch (JSONException e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to get non configure therapies");
            log.error("Failed to get non configure therapies");
        }
        return response;
    }
}
