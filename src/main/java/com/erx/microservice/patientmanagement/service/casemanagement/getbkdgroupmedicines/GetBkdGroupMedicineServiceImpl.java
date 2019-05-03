package com.erx.microservice.patientmanagement.service.casemanagement.getbkdgroupmedicines;

/*
* created by Latha on 31-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmGroupMedicineMaster;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmGroupMedicineMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.BkdGroupMedicineDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.DosageTimeDTO;
import org.hibernate.service.spi.ServiceException;
import org.json.JSONArray;
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

@Service("getBkdGroupMedicineService")
public class GetBkdGroupMedicineServiceImpl implements GetBkdGroupMedicineService {

    private final Logger log = LoggerFactory.getLogger(GetBkdGroupMedicineServiceImpl.class);

    @Autowired
    private CmGroupMedicineMasterRepository cmGroupMedicineMasterRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    @Override
    public GetBkdGroupMedicineServiceResponse execute(GetBkdGroupMedicineServiceRequest request) throws ServiceException {
        GetBkdGroupMedicineServiceResponse response = null;
        Page<CmGroupMedicineMaster> cmGroupMedicineMasters = null;
        Page<BkdGroupMedicineDTO> bkdGroupMedicineDTOs  = null;

        try {
            log.debug("Call to retrieve all bkd group medicine detail for the clinic " + request.getClinicId());
            Pageable pageable = new PageRequest(request.getPageable().getPageNumber(), request.getPageable().getPageSize());

            //retrieve the bkd medicine group details
            cmGroupMedicineMasters = cmGroupMedicineMasterRepository.findByGroupMedicineClinic(request.getClinicId(), pageable);

            //set values to dto
            if(cmGroupMedicineMasters != null)
            bkdGroupMedicineDTOs = frameBkdGroupMedicineDTOs(cmGroupMedicineMasters);

            // setting the dto to response
            response = new GetBkdGroupMedicineServiceResponse(bkdGroupMedicineDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved bkd medicine group details Successfully");
            log.debug("Retrieved bkd medicine group details Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve bkd medicine group details");
            log.error("Failed to retrieve bkd medicine group details");
        }
        return response;
    }

    //method to frame bkd group medicine dto
    private Page<BkdGroupMedicineDTO> frameBkdGroupMedicineDTOs(Page<CmGroupMedicineMaster> cmGroupMedicineMasters) throws JSONException {
        Page<BkdGroupMedicineDTO> bkdGroupMedicineDTOPage = null;
        List<CmGroupMedicineMaster> cmGroupMedicineMastersPage = cmGroupMedicineMasters.getContent();
        List<BkdGroupMedicineDTO> bkdGroupMedicineDTOs = new ArrayList<>();
        List<DosageTimeDTO> dosageTimeDTOS = new ArrayList<>();
        try {
            log.debug("call to frame bkd group medicine");
            for(CmGroupMedicineMaster cmGroupMedicineMaster : cmGroupMedicineMastersPage){
                BkdGroupMedicineDTO bkdGroupMedicineDTO = new BkdGroupMedicineDTO();
                bkdGroupMedicineDTO.setCmGroupMedicineMasterId(cmGroupMedicineMaster.getId());
                bkdGroupMedicineDTO.setDosageQuantity(cmGroupMedicineMaster.getStrength());
                bkdGroupMedicineDTO.setTotalQuantity(cmGroupMedicineMaster.getTotalQuantity());
                if(cmGroupMedicineMaster.getDosageTime() != null) {
                    // to frame dosage time dto
                    JSONArray jsonArray = new JSONArray(cmGroupMedicineMaster.getDosageTime().toString());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        DosageTimeDTO dosageTimeDTO = new DosageTimeDTO();
                        dosageTimeDTO.setSequenceNo(jsonObject.getLong("sequenceNo"));
                        if (jsonObject.getString("dosageInstructionId") != null) {
                            dosageTimeDTO.setDosageInstructionId(jsonObject.getString("dosageInstructionId"));
                        }
                        dosageTimeDTO.setDosageInstructionName(jsonObject.getString("dosageInstructionName"));
                        dosageTimeDTO.setTime(jsonObject.getString("time"));
                        dosageTimeDTOS.add(dosageTimeDTO);
                    }
                    bkdGroupMedicineDTO.setDosageTimeDTOs(dosageTimeDTOS);
                }
                bkdGroupMedicineDTO.setGroupMedicineName(cmGroupMedicineMaster.getMedicineName());
                if(cmGroupMedicineMaster.getMedicineTypeId() != null) {
                    bkdGroupMedicineDTO.setGroupMedicineTypeId(cmGroupMedicineMaster.getMedicineTypeId());
                    //call to get medicine type name
                    JSONObject jsonObjectMedicineTypeMaster = serviceGateway.getMedicineTypeById(cmGroupMedicineMaster.getMedicineTypeId());
                    JSONObject medicineTypeMasterDTO = jsonObjectMedicineTypeMaster.getJSONObject("medicineTypeMasterDTO");
                    bkdGroupMedicineDTO.setGroupMedicineTypeName(medicineTypeMasterDTO.getString("typeName"));
                }
                bkdGroupMedicineDTO.setInstructions(cmGroupMedicineMaster.getInstruction());
                bkdGroupMedicineDTO.setNumberOfDays(cmGroupMedicineMaster.getNumberOfDays());
                if(cmGroupMedicineMaster.getCmDosageValueMapping() != null) {
                    bkdGroupMedicineDTO.setCmDosageValueMappingId(cmGroupMedicineMaster.getCmDosageValueMapping().getId());
                }
                if(cmGroupMedicineMaster.getUomMasterId() != null) {
                    bkdGroupMedicineDTO.setUomMasterId(cmGroupMedicineMaster.getUomMasterId());
                    //call to get uom master
                    JSONObject jsonObject = serviceGateway.getUomMasterByUomMasterId(cmGroupMedicineMaster.getUomMasterId());
                    if (jsonObject != null) {
                        JSONObject uomMaster = jsonObject.getJSONObject("uomMaster");
                        bkdGroupMedicineDTO.setUomMasterName(uomMaster.getString("uomName"));
                    }
                }
                bkdGroupMedicineDTOs.add(bkdGroupMedicineDTO);
            }
            bkdGroupMedicineDTOPage = new Page<BkdGroupMedicineDTO>() {

                @Override
                public int getTotalPages() {
                    return cmGroupMedicineMasters.getTotalPages();
                }

                @Override
                public long getTotalElements() {
                    return cmGroupMedicineMasters.getTotalElements();
                }

                @Override
                public <S> Page<S> map(Converter<? super BkdGroupMedicineDTO, ? extends S> converter) {
                    return null;
                }

                @Override
                public int getNumber() {
                    return cmGroupMedicineMasters.getNumber();
                }

                @Override
                public int getSize() {
                    return cmGroupMedicineMasters.getSize();
                }

                @Override
                public int getNumberOfElements() {
                    return cmGroupMedicineMasters.getNumberOfElements();
                }

                @Override
                public List<BkdGroupMedicineDTO> getContent() {
                    return bkdGroupMedicineDTOs;
                }

                @Override
                public boolean hasContent() {
                    return cmGroupMedicineMasters.hasContent();
                }

                @Override
                public Sort getSort() {
                    return cmGroupMedicineMasters.getSort();
                }

                @Override
                public boolean isFirst() {
                    return cmGroupMedicineMasters.isFirst();
                }

                @Override
                public boolean isLast() {
                    return cmGroupMedicineMasters.isLast();
                }

                @Override
                public boolean hasNext() {
                    return cmGroupMedicineMasters.hasNext();
                }

                @Override
                public boolean hasPrevious() {
                    return cmGroupMedicineMasters.hasPrevious();
                }

                @Override
                public Pageable nextPageable() {
                    return cmGroupMedicineMasters.nextPageable();
                }

                @Override
                public Pageable previousPageable() {
                    return cmGroupMedicineMasters.previousPageable();
                }

                @Override
                public Iterator<BkdGroupMedicineDTO> iterator() {
                    return null;
                }
            };
        } catch (JSONException e) {
            log.error("failed to frame bkd group medicine dto" + e.getMessage());
        }
        return bkdGroupMedicineDTOPage;
    }
}
