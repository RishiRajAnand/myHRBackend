package com.erx.microservice.patientmanagement.service.casemanagement.getgynaecandobshistorybylookup;

/*
* created by Latha on 18-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmObservationCategory;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmObservationCategoryData;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmObservationCategoryDataRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmObservationCategoryRepository;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmObservationCategoryDataDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmObservationDataDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getGynaecHistoryByLookupValueService")
public class GetGynaecHistoryByLookupValueServiceImpl implements GetGynaecHistoryByLookupValueService {

    private final Logger log = LoggerFactory.getLogger(GetGynaecHistoryByLookupValueServiceImpl.class);

    @Autowired
    private CmObservationCategoryRepository cmObservationCategoryRepository;

    @Autowired
    private CmObservationCategoryDataRepository cmObservationCategoryDataRepository;

    @Override
    public GetGynaecHistoryByLookupValueServiceResponse execute(GetGynaecHistoryByLookupValueServiceRequest request) throws ServiceException {

        GetGynaecHistoryByLookupValueServiceResponse response = null;
        CmObservationCategory cmObservationCategory = new CmObservationCategory();
        List<CmObservationCategoryData> observationValueList = new ArrayList<CmObservationCategoryData>();
        List<CmObservationCategoryDataDTO> cmObservationCategoryDataDTOs = new ArrayList<>();
        try {
            log.debug("Call to get gynaec history by lookup value");

            // retrieve cmObservation by lookup value id
            cmObservationCategory = cmObservationCategoryRepository.getCmObservationCategoryByLookupValueId(request.getLookupValueId());
            // retrieve observationValueList by clinic id and cm observation id
            if(cmObservationCategory != null)
                observationValueList = cmObservationCategoryDataRepository.getObservationListByClinicCategory(request.getClinicId(),cmObservationCategory.getId());
            if(observationValueList != null){
                for(CmObservationCategoryData cmObservationCategoryData : observationValueList){
                    CmObservationCategoryDataDTO cmObservationCategoryDataDTO = new CmObservationCategoryDataDTO();
                    CmObservationDataDTO cmObservationDataDTO = new CmObservationDataDTO();
                    cmObservationCategoryDataDTO.setId(cmObservationCategoryData.getId());
                    if(cmObservationCategoryData.getCmObservationData() != null)
                        cmObservationCategoryDataDTO.setLookupValueId(request.getLookupValueId());
                        cmObservationCategoryDataDTO.setCmObservationDataDTO(cmObservationDataDTO);
                    cmObservationCategoryDataDTO.getCmObservationDataDTO().setDataName(cmObservationCategoryData.getCmObservationData().getDataName());
                    cmObservationCategoryDataDTOs.add(cmObservationCategoryDataDTO);
                }
            }
            //create response
            response = new GetGynaecHistoryByLookupValueServiceResponse(cmObservationCategoryDataDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved gynaec history by lookup value successfully");
            log.debug("Retrieved gynaec history by lookup value successfully");
        } catch (Exception e) {
            //create response
            response = new GetGynaecHistoryByLookupValueServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieved gynaec history by lookup value");
            log.error("Failed to retrieved gynaec history by lookup value");
        }

        return response;
    }
}
