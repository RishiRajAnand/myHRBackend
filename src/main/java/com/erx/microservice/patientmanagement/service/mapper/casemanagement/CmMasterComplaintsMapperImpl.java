package com.erx.microservice.patientmanagement.service.mapper.casemanagement;

/*
* created by Latha on 20-08-2018
* */


import com.erx.microservice.patientmanagement.domain.casemanagement.CmMasterComplaint;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmObservationCategoryData;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmObservationCategoryDataRepository;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmMasterComplaintDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmObservationCategoryDataDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmObservationDataDTO;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring", uses = {})
public class CmMasterComplaintsMapperImpl implements CmMasterComplaintsMapper{

    @Autowired
    private CmObservationCategoryDataRepository cmObservationCategoryDataRepository;

    @Override
    public CmMasterComplaintDTO cmMasterComplaintsToCmMasterComplaintDTO(CmMasterComplaint cmMasterComplaints) {
        return null;
    }

    @Override
    public CmMasterComplaint cmMasterComplaintDTOToCmMasterComplaints(CmMasterComplaintDTO cmMasterComplaintDTO) {

        CmMasterComplaint cmMasterComplaint = new CmMasterComplaint();
        CmObservationCategoryData cmDeliveryModeObservationData = null;
        CmObservationCategoryData cmOhObservationData = null;
        CmObservationCategoryData cmMsObservationData = null;
        CmObservationCategoryData cmMcObservationData = null;
        CmObservationCategoryData cmCharacteristicsObservationData = null;
        // setting the cm master complaint details
        cmMasterComplaint.setAssociatedComplaints(cmMasterComplaintDTO.getAssociatedComplaints().trim());
        cmMasterComplaint.setCoital(cmMasterComplaintDTO.getCoital().trim());
        cmMasterComplaint.setFamilyHistory(cmMasterComplaintDTO.getFamilyHistory().trim());
        cmMasterComplaint.setGynaecHistory(cmMasterComplaintDTO.getGynaecHistory().trim());
        cmMasterComplaint.setMenopause(cmMasterComplaintDTO.getMenopause());
        cmMasterComplaint.setMenstruation(cmMasterComplaintDTO.getMenstruation());
        cmMasterComplaint.setPastIllness(cmMasterComplaintDTO.getPastIllness().trim());
        cmMasterComplaint.setPresentIllness(cmMasterComplaintDTO.getPresentIllness().trim());
        cmMasterComplaint.setSignsAndSymptoms(cmMasterComplaintDTO.getSignsAndSymptoms().trim());
        cmMasterComplaint.setTreatmentHistory(cmMasterComplaintDTO.getTreatmentHistory().trim());

        // delivery mode retrieve
        if(cmMasterComplaintDTO.getDeliveryModeObservationDataDTO().getId() != null) {
            cmDeliveryModeObservationData = cmObservationCategoryDataRepository.findOne(cmMasterComplaintDTO.getDeliveryModeObservationDataDTO().getId());
            cmMasterComplaint.setDeliveryModeObservationData(cmDeliveryModeObservationData);
        }

        // Oh mode retrieve
        if(cmMasterComplaintDTO.getOhObservationDataDTO().getId() != null) {
            cmOhObservationData = cmObservationCategoryDataRepository.findOne(cmMasterComplaintDTO.getOhObservationDataDTO().getId());
            cmMasterComplaint.setOhObservationData(cmOhObservationData);
        }

        // Ms mode retrieve
        if(cmMasterComplaintDTO.getMsObservationDataDTO().getId() != null) {
            cmMsObservationData = cmObservationCategoryDataRepository.findOne(cmMasterComplaintDTO.getMsObservationDataDTO().getId());
            cmMasterComplaint.setMsObservationData(cmMsObservationData);
        }

        // Mc mode retrieve
        if(cmMasterComplaintDTO.getMcObservationDataDTO().getId() != null) {
            cmMcObservationData = cmObservationCategoryDataRepository.findOne(cmMasterComplaintDTO.getMcObservationDataDTO().getId());
            cmMasterComplaint.setMcObservationData(cmMcObservationData);
        }

        // Characteristics mode retrieve
        if(cmMasterComplaintDTO.getCharacteristicsObservationDataDTO().getId() != null) {
            cmCharacteristicsObservationData = cmObservationCategoryDataRepository.findOne(cmMasterComplaintDTO.getCharacteristicsObservationDataDTO().getId());
            cmMasterComplaint.setCharacteristicsObservationData(cmCharacteristicsObservationData);
        }


        return cmMasterComplaint;
    }

    @Override
    public List<CmMasterComplaintDTO> cmMasterComplaintsToCmMasterComplaintDTOs(List<CmMasterComplaint> cmMasterComplaints) {
        List<CmMasterComplaintDTO> cmMasterComplaintDTOs = new ArrayList<CmMasterComplaintDTO>();
        if (cmMasterComplaints.size() != 0) {
            for (CmMasterComplaint cmMasterComplaint : cmMasterComplaints) {
                int n = cmMasterComplaints.size();
                CmMasterComplaintDTO cmMasterComplaintDTO = new CmMasterComplaintDTO();

                    if (cmMasterComplaints.get(n - 1) != null) {
                        cmMasterComplaintDTO.setAssociatedComplaints(cmMasterComplaint.getAssociatedComplaints());
                        cmMasterComplaintDTO.setCoital(cmMasterComplaint.getCoital());
                        cmMasterComplaintDTO.setFamilyHistory(cmMasterComplaint.getFamilyHistory());
                        cmMasterComplaintDTO.setGynaecHistory(cmMasterComplaint.getGynaecHistory());
                        cmMasterComplaintDTO.setMenopause(cmMasterComplaint.getMenopause());
                        cmMasterComplaintDTO.setMenstruation(cmMasterComplaint.getMenstruation());
                        cmMasterComplaintDTO.setPastIllness(cmMasterComplaint.getPastIllness());
                        cmMasterComplaintDTO.setPresentIllness(cmMasterComplaint.getPresentIllness());
                        cmMasterComplaintDTO.setSignsAndSymptoms(cmMasterComplaint.getSignsAndSymptoms());
                        cmMasterComplaintDTO.setTreatmentHistory(cmMasterComplaint.getTreatmentHistory());
                        // setting delivery mode

                        if (cmMasterComplaint.getDeliveryModeObservationData() != null) {
                            if (cmMasterComplaint.getDeliveryModeObservationData().getId() != null)
                                cmMasterComplaintDTO.setDeliveryModeObservationDataDTO(new CmObservationCategoryDataDTO());
                            cmMasterComplaintDTO.getDeliveryModeObservationDataDTO().setId(cmMasterComplaint.getDeliveryModeObservationData().getId());
                            if (cmMasterComplaint.getDeliveryModeObservationData().getCmObservationData() != null) {
                                cmMasterComplaintDTO.getDeliveryModeObservationDataDTO().setCmObservationDataDTO(new CmObservationDataDTO());
                                cmMasterComplaintDTO.getDeliveryModeObservationDataDTO().getCmObservationDataDTO().setDataName(cmMasterComplaint.getDeliveryModeObservationData().getCmObservationData().getDataName());
                            }
                        }

                        // setting oh
                        if (cmMasterComplaint.getOhObservationData() != null) {
                            cmMasterComplaintDTO.setOhObservationDataDTO(new CmObservationCategoryDataDTO());
                            if (cmMasterComplaint.getOhObservationData().getId() != null)
                                cmMasterComplaintDTO.getOhObservationDataDTO().setId(cmMasterComplaint.getOhObservationData().getId());
                            if (cmMasterComplaint.getOhObservationData().getCmObservationData() != null) {
                                cmMasterComplaintDTO.getOhObservationDataDTO().setCmObservationDataDTO(new CmObservationDataDTO());
                                cmMasterComplaintDTO.getOhObservationDataDTO().getCmObservationDataDTO().setDataName(cmMasterComplaint.getOhObservationData().getCmObservationData().getDataName());
                            }
                        }

                        // setting ms
                        if (cmMasterComplaint.getMsObservationData() != null) {
                            cmMasterComplaintDTO.setMsObservationDataDTO(new CmObservationCategoryDataDTO());
                            if (cmMasterComplaint.getMsObservationData().getId() != null)
                                cmMasterComplaintDTO.getMsObservationDataDTO().setId(cmMasterComplaint.getMsObservationData().getId());
                            if (cmMasterComplaint.getMsObservationData().getCmObservationData() != null) {
                                cmMasterComplaintDTO.getMsObservationDataDTO().setCmObservationDataDTO(new CmObservationDataDTO());
                                cmMasterComplaintDTO.getMsObservationDataDTO().getCmObservationDataDTO().setDataName(cmMasterComplaint.getMsObservationData().getCmObservationData().getDataName());
                            }
                        }

                        // setting mc
                        if (cmMasterComplaint.getMcObservationData() != null) {
                            cmMasterComplaintDTO.setMcObservationDataDTO(new CmObservationCategoryDataDTO());
                            if (cmMasterComplaint.getMcObservationData().getId() != null)
                                cmMasterComplaintDTO.getMcObservationDataDTO().setId(cmMasterComplaint.getMcObservationData().getId());
                            if (cmMasterComplaint.getMcObservationData().getCmObservationData() != null) {
                                cmMasterComplaintDTO.getMcObservationDataDTO().setCmObservationDataDTO(new CmObservationDataDTO());
                                cmMasterComplaintDTO.getMcObservationDataDTO().getCmObservationDataDTO().setDataName(cmMasterComplaint.getMcObservationData().getCmObservationData().getDataName());
                            }
                        }

                        // setting characteristics
                        if (cmMasterComplaint.getCharacteristicsObservationData() != null) {
                            cmMasterComplaintDTO.setCharacteristicsObservationDataDTO(new CmObservationCategoryDataDTO());
                            if (cmMasterComplaint.getCharacteristicsObservationData().getId() != null)
                                cmMasterComplaintDTO.getCharacteristicsObservationDataDTO().setId(cmMasterComplaint.getCharacteristicsObservationData().getId());
                            if (cmMasterComplaint.getCharacteristicsObservationData().getCmObservationData() != null) {
                                cmMasterComplaintDTO.getCharacteristicsObservationDataDTO().setCmObservationDataDTO(new CmObservationDataDTO());
                                cmMasterComplaintDTO.getCharacteristicsObservationDataDTO().getCmObservationDataDTO().setDataName(cmMasterComplaint.getCharacteristicsObservationData().getCmObservationData().getDataName());
                            }
                        }
                        cmMasterComplaintDTOs.add(cmMasterComplaintDTO);

                    }
                }
            }
        return cmMasterComplaintDTOs;
    }
    @Override
    public List<CmMasterComplaint> cmMasterComplaintDTOsToCmMasterComplaints(List<CmMasterComplaintDTO> cmMasterComplaintDTOs) {
        return null;
    }
}
