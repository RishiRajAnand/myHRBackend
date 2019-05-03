package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymaster;

/*
* created by Latha on 04-09-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyMaster;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.TherapyMasterGetAllDTO;
import org.hibernate.service.spi.ServiceException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service("getTherapyMasterService")
public class GetTherapyMasterServiceImpl implements GetTherapyMasterService {

    private final Logger log = LoggerFactory.getLogger(GetTherapyMasterServiceImpl.class);

    @Autowired
    private TherapyMasterRepository therapyMasterRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    @Override
    public GetTherapyMasterServiceResponse execute(GetTherapyMasterServiceRequest request) throws ServiceException {
        GetTherapyMasterServiceResponse response = null;
        Page<TherapyMaster> therapyMasters = null;
        Page<TherapyMasterGetAllDTO> therapyMasterGetAllDTOs  = null;
        try {
            log.debug("Call to retrieve all therapy master detail for the clinic " + request.getClinicId());
            Pageable pageable = new PageRequest(request.getPageable().getPageNumber(), request.getPageable().getPageSize());
            //retrieve the therapy master details
            therapyMasters = therapyMasterRepository.findByClinic(request.getClinicId(),request.getPageable());

            //set values to dto
            if(therapyMasters != null)
                therapyMasterGetAllDTOs = frameTherapyMasterGetAllDTOs(therapyMasters);

            // setting the dto to response
            response = new GetTherapyMasterServiceResponse(therapyMasterGetAllDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved therapy master details Successfully");
            log.debug("Retrieved therapy master details Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve therapy master details");
            log.error("Failed to retrieve therapy master details");
        }
        return response;
    }

    //method to frame therapy master dto
    private Page<TherapyMasterGetAllDTO> frameTherapyMasterGetAllDTOs(Page<TherapyMaster> therapyMasters) {
        Page<TherapyMasterGetAllDTO> therapyMasterGetAllDTOPage = null;
        List<TherapyMaster> therapyMastersPage = therapyMasters.getContent();
        List<TherapyMasterGetAllDTO> therapyMasterGetAllDTOs = new ArrayList<>();
        try {
            log.debug("call to frame therapy master get all dto");
            for(TherapyMaster therapyMaster : therapyMastersPage){
                TherapyMasterGetAllDTO therapyMasterGetAllDTO = new TherapyMasterGetAllDTO();
                therapyMasterGetAllDTO.setTherapyMasterId(therapyMaster.getId());
                //call to get service name which is therapy name by service id
                if(therapyMaster.getServiceCatalogueId() != null) {
                    JSONObject jsonServiceObject = serviceGateway.getServiceCatalogueByServiceId(therapyMaster.getServiceCatalogueId());
                    if (jsonServiceObject != null) {
                        JSONObject serviceObjectJSONObject = jsonServiceObject.getJSONObject("serviceCatalogueDTO");
                        therapyMasterGetAllDTO.setServiceCatalogueId(therapyMaster.getServiceCatalogueId());
                        therapyMasterGetAllDTO.setTherapyName(serviceObjectJSONObject.getString("serviceName"));
                    }
                }
                //call to get therapy department id
                if(therapyMaster.getTherapyDepartmentId() != null) {
                    JSONObject jsonObject = serviceGateway.getDepartmentByDepartmentById(therapyMaster.getTherapyDepartmentId());
                    if(jsonObject != null) {
                        JSONObject departmentMaster = jsonObject.getJSONObject("departmentMasterDetailsDTO");
                        therapyMasterGetAllDTO.setTherapyDepartmentId(therapyMaster.getTherapyDepartmentId());
                        therapyMasterGetAllDTO.setTherapyDepartmentName(departmentMaster.getString("departmentName"));
                    }
                }
                therapyMasterGetAllDTO.setDuration(therapyMaster.getDuration());
                therapyMasterGetAllDTO.setMedicineCharged(therapyMaster.isMedicineCharged());
                if(therapyMaster.getTherapyMasterRoomDetails() != null){
                    therapyMasterGetAllDTO.setRoomRequired(therapyMaster.getTherapyMasterRoomDetails().size());
                }
                therapyMasterGetAllDTOs.add(therapyMasterGetAllDTO);
            }
            therapyMasterGetAllDTOPage = new Page<TherapyMasterGetAllDTO>() {

                @Override
                public int getTotalPages() {
                    return therapyMasters.getTotalPages();
                }

                @Override
                public long getTotalElements() {
                    return therapyMasters.getTotalElements();
                }

                @Override
                public <S> Page<S> map(Converter<? super TherapyMasterGetAllDTO, ? extends S> converter) {
                    return null;
                }

                @Override
                public int getNumber() {
                    return therapyMasters.getNumber();
                }

                @Override
                public int getSize() {
                    return therapyMasters.getSize();
                }

                @Override
                public int getNumberOfElements() {
                    return therapyMasters.getNumberOfElements();
                }

                @Override
                public List<TherapyMasterGetAllDTO> getContent() {
                    return therapyMasterGetAllDTOs;
                }

                @Override
                public boolean hasContent() {
                    return therapyMasters.hasContent();
                }

                @Override
                public Sort getSort() {
                    return therapyMasters.getSort();
                }

                @Override
                public boolean isFirst() {
                    return therapyMasters.isFirst();
                }

                @Override
                public boolean isLast() {
                    return therapyMasters.isLast();
                }

                @Override
                public boolean hasNext() {
                    return therapyMasters.hasNext();
                }

                @Override
                public boolean hasPrevious() {
                    return therapyMasters.hasPrevious();
                }

                @Override
                public Pageable nextPageable() {
                    return therapyMasters.nextPageable();
                }

                @Override
                public Pageable previousPageable() {
                    return therapyMasters.previousPageable();
                }

                @Override
                public Iterator<TherapyMasterGetAllDTO> iterator() {
                    return null;
                }
            };
        } catch (JSONException e) {
            log.error("Failed to frame therapy master get all dto" + e.getMessage());
        }
        return therapyMasterGetAllDTOPage;
    }
}
