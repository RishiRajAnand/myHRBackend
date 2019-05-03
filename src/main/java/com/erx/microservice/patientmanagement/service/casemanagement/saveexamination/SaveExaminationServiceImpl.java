package com.erx.microservice.patientmanagement.service.casemanagement.saveexamination;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.*;
import com.erx.microservice.patientmanagement.repository.casemanagement.*;
import com.erx.microservice.patientmanagement.service.datautil.casemanagement.savecmobservationcategory.SaveCmObservationCategoryDataService;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.*;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service("saveExaminationService")
public class SaveExaminationServiceImpl implements SaveExaminationService {

    private final Logger log = LoggerFactory.getLogger(SaveExaminationServiceImpl.class);

    @Autowired
    private CmMasterRepository cmMasterRepository;

    @Autowired
    private CmMasterDetailsRepository cmMasterDetailsRepository;

    @Autowired
    private SaveCmObservationCategoryDataService saveCmObservationCategoryDataService;

    @Autowired
    private CmExamntnDetailRepository cmExamntnDetailRepository;

    @Autowired
    private CmExamntnDashavidhaRepository cmExamntnDashavidhaRepository;

    @Autowired
    private CmExamntnTypeRepository cmExamntnTypeRepository;

    @Autowired
    private CmExamntnAsthaVidhaPareekshaRepository cmExamntnAsthaVidhaPareekshaRepository;

    @Autowired
    private CmExamntnGeneralRepository cmExamntnGeneralRepository;

    @Autowired
    private CmExamntnSarvaSrotoPareekshaRepository cmExamntnSarvaSrotoPareekshaRepository;

    @Autowired
    private CmExamntnSampraptiGhatakasRepository cmExamntnSampraptiGhatakasRepository;

    @Override
    public SaveExaminationServiceResponse execute(SaveExaminationServiceRequest request) throws ServiceException {

        SaveExaminationServiceResponse response = null;
        CmMaster cmMaster = new CmMaster();
        CmMasterDetails cmMasterDetails = new CmMasterDetails();
        CmExamntnDashavidha cmExamntnDashavidha = new CmExamntnDashavidha();
        CmExamntnAsthaVidhaPareeksha cmExamntnAsthaVidhaPareeksha = new CmExamntnAsthaVidhaPareeksha();
        CmExamntnGeneral cmExamntnGeneral = new CmExamntnGeneral();
        CmExamntnSarvaSrotoPareeksha cmExamntnSarvaSrotoPareeksha = new CmExamntnSarvaSrotoPareeksha();
        CmExamntnSampraptiGhatakas cmExamntnSampraptiGhatakas = new CmExamntnSampraptiGhatakas();
        CmExaminationResponseDTO cmExaminationResponseDTO = new CmExaminationResponseDTO();
        try {
            log.debug("Call to save examinations of case sheet");
            //find cm master by id
            cmMaster = cmMasterRepository.findOne(request.getSaveCmExaminationDTO().getCmMasterId());
            if(cmMaster != null)
                /*cmMasterDetails.setCmMaster(cmMaster);
            cmMasterDetails.setCaseCreatedDate(LocalDateTime.now());
            if(request.getSaveCmExaminationDTO().getPatientAppointmentId() != null){
                cmMasterDetails.setPatientAppointmentId(request.getSaveCmExaminationDTO().getPatientAppointmentId());
            }*/
            //retrieve cm master details
            cmMasterDetails = cmMasterDetailsRepository.findOne(request.getSaveCmExaminationDTO().getCmMasterDetailId());

            if(cmMasterDetails != null) {
                //saving the dashavidha examination
                if(request.getSaveCmExaminationDTO().getCmExamntnDashavidhaDTO() != null){
                    //save dashavidha pareeksha
                    cmExamntnDashavidha = frameAndSaveDashavidha(request.getSaveCmExaminationDTO().getCmExamntnDashavidhaDTO(),request.getSaveCmExaminationDTO().getClinicId(),cmMasterDetails);

                    if(cmExamntnDashavidha != null)
                        cmExaminationResponseDTO.setCmExamDashavidhaId(cmExamntnDashavidha.getId());
                }

                //saving the astha vidha pareeksha examination
                if(request.getSaveCmExaminationDTO().getCmExamntnAsthaVidhaPareekshaDTO() != null){
                    //save astha vidha pareeksha
                    cmExamntnAsthaVidhaPareeksha = frameAndSaveAsthaVidha(request.getSaveCmExaminationDTO().getCmExamntnAsthaVidhaPareekshaDTO(),request.getSaveCmExaminationDTO().getClinicId(),cmMasterDetails);

                    if(cmExamntnAsthaVidhaPareeksha != null)
                        cmExaminationResponseDTO.setCmAsthaVidhaId(cmExamntnAsthaVidhaPareeksha.getId());
                }

                //saving the general examination
                if(request.getSaveCmExaminationDTO().getCmExamntnGeneralDTO() != null){
                    //save general examination
                    cmExamntnGeneral = frameAndSaveGeneral(request.getSaveCmExaminationDTO().getCmExamntnGeneralDTO(),request.getSaveCmExaminationDTO().getClinicId(),cmMasterDetails);

                    if(cmExamntnGeneral != null)
                        cmExaminationResponseDTO.setCmGeneralId(cmExamntnGeneral.getId());
                }

                //saving the sarva sroto pareeksha
                if(request.getSaveCmExaminationDTO().getCmExamntnSarvaSrotoPareekshaDTO() != null){
                    //save general examination
                    cmExamntnSarvaSrotoPareeksha = frameAndSaveSarvaSroto(request.getSaveCmExaminationDTO().getCmExamntnSarvaSrotoPareekshaDTO(),request.getSaveCmExaminationDTO().getClinicId(),cmMasterDetails);

                    if(cmExamntnSarvaSrotoPareeksha != null)
                        cmExaminationResponseDTO.setCmSarvaSrotoparikshaId(cmExamntnSarvaSrotoPareeksha.getId());
                }

                //saving the samprapti ghatakas
                if(request.getSaveCmExaminationDTO().getCmExamntnSampraptiGhatakasDTO() != null){
                    //save samprapti ghatakas
                    cmExamntnSampraptiGhatakas = frameAndSaveSamprapti(request.getSaveCmExaminationDTO().getCmExamntnSampraptiGhatakasDTO(),request.getSaveCmExaminationDTO().getClinicId(),cmMasterDetails);

                    if(cmExamntnSampraptiGhatakas != null)
                        cmExaminationResponseDTO.setCmSampraptiGhatakasId(cmExamntnSampraptiGhatakas.getId());
                }

            }

            //create response
            response = new SaveExaminationServiceResponse(cmExaminationResponseDTO);
            response.setMessage("Case sheet examination details saved successfully");
            log.debug("Case sheet examination details saved successfully");
        } catch (Exception e) {
            response = new SaveExaminationServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to save case sheet examination details");
            response.setMessage(e.getMessage() + " so,Failed to save case sheet examination details");
        }

        return response;
    }

    //saving dashavidha pareeksha
    private CmExamntnDashavidha frameAndSaveDashavidha(CmExamntnDashavidhaDTO cmExamntnDashavidhaDTO, Long clinicId, CmMasterDetails cmMasterDetails) {

        List<CmExamntnDetail> allExaminatnDetails = new ArrayList<CmExamntnDetail>();
        CmExamntnDetail cmExamntnDetail = new CmExamntnDetail();
        CmExamntnType cmExamntnType = new CmExamntnType();
        CmExamntnDashavidha cmExamntnDashavidha = new CmExamntnDashavidha();
        CmObservationCategoryData cmPrakruthiObservationData = null;
        CmObservationCategoryData cmSatvaObservationData = null;
        CmObservationCategoryData cmSaraObservationData = null;
        CmObservationCategoryData cmSamhananaObservationData = null;
        CmObservationCategoryData cmAharaShakthiObservationData = null;
        CmObservationCategoryData cmVyayamaShakthiObservationData = null;
        CmObservationCategoryData cmPramanaObservationData = null;
        CmObservationCategoryData cmVayahaObservationData = null;
        CmObservationCategoryData cmSatmyaObservationData = null;
        CmObservationCategoryData cmVikruthiObservationData = null;

        try {
            log.debug("Call to frame and save dashavidha obj" + clinicId);
            // save for prakruthi category when observation data is null
            if(cmExamntnDashavidhaDTO.getPrakruthi() != null) {
                if (cmExamntnDashavidhaDTO.getPrakruthi().getId() == null && cmExamntnDashavidhaDTO.getPrakruthi().getCmObservationDataDTO().getDataName() != null) {
                    cmPrakruthiObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnDashavidhaDTO.getPrakruthi().getLookupValueId(),
                            cmExamntnDashavidhaDTO.getPrakruthi().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnDashavidha.setPrakruthi(cmPrakruthiObservationData);
                }
            }

            // save for satva category when observation data is null
            if(cmExamntnDashavidhaDTO.getSatva() != null) {
                if (cmExamntnDashavidhaDTO.getSatva().getId() == null && cmExamntnDashavidhaDTO.getSatva().getCmObservationDataDTO().getDataName() != null) {
                    cmSatvaObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnDashavidhaDTO.getSatva().getLookupValueId(),
                            cmExamntnDashavidhaDTO.getSatva().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnDashavidha.setSatva(cmSatvaObservationData);
                }
            }

            // save for sara category when observation data is null
            if(cmExamntnDashavidhaDTO.getSara() != null) {
                if (cmExamntnDashavidhaDTO.getSara().getId() == null && cmExamntnDashavidhaDTO.getSara().getCmObservationDataDTO().getDataName() != null) {
                    cmSaraObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnDashavidhaDTO.getSara().getLookupValueId(),
                            cmExamntnDashavidhaDTO.getSara().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnDashavidha.setSara(cmSaraObservationData);
                }
            }

            // save for Samhanana category when observation data is null
            if(cmExamntnDashavidhaDTO.getSamhanana() != null) {
                if (cmExamntnDashavidhaDTO.getSamhanana().getId() == null && cmExamntnDashavidhaDTO.getSamhanana().getCmObservationDataDTO().getDataName() != null) {
                    cmSamhananaObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnDashavidhaDTO.getSamhanana().getLookupValueId(),
                            cmExamntnDashavidhaDTO.getSamhanana().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnDashavidha.setSamhanana(cmSamhananaObservationData);
                }
            }

            // save for AharaShakthi category when observation data is null
            if(cmExamntnDashavidhaDTO.getAharaShakthi() != null) {
                if (cmExamntnDashavidhaDTO.getAharaShakthi().getId() == null && cmExamntnDashavidhaDTO.getAharaShakthi().getCmObservationDataDTO().getDataName() != null) {
                    cmAharaShakthiObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnDashavidhaDTO.getAharaShakthi().getLookupValueId(),
                            cmExamntnDashavidhaDTO.getAharaShakthi().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnDashavidha.setAharaShakthi(cmAharaShakthiObservationData);
                }
            }

            // save for VyayamaShakthi category when observation data is null
            if(cmExamntnDashavidhaDTO.getVyayamaShakthi() != null) {
                if (cmExamntnDashavidhaDTO.getVyayamaShakthi().getId() == null && cmExamntnDashavidhaDTO.getVyayamaShakthi().getCmObservationDataDTO().getDataName() != null) {
                    cmVyayamaShakthiObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnDashavidhaDTO.getVyayamaShakthi().getLookupValueId(),
                            cmExamntnDashavidhaDTO.getVyayamaShakthi().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnDashavidha.setVyayamaShakthi(cmVyayamaShakthiObservationData);
                }
            }

            // save for Pramana category when observation data is null
            if(cmExamntnDashavidhaDTO.getPramana() != null) {
                if (cmExamntnDashavidhaDTO.getPramana().getId() == null && cmExamntnDashavidhaDTO.getPramana().getCmObservationDataDTO().getDataName() != null) {
                    cmPramanaObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnDashavidhaDTO.getPramana().getLookupValueId(),
                            cmExamntnDashavidhaDTO.getPramana().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnDashavidha.setPramana(cmPramanaObservationData);
                }
            }

            // save for Vayaha category when observation data is null
            if(cmExamntnDashavidhaDTO.getVayaha() != null) {
                if (cmExamntnDashavidhaDTO.getVayaha().getId() == null && cmExamntnDashavidhaDTO.getVayaha().getCmObservationDataDTO().getDataName() != null) {
                    cmVayahaObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnDashavidhaDTO.getVayaha().getLookupValueId(),
                            cmExamntnDashavidhaDTO.getVayaha().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnDashavidha.setVayaha(cmVayahaObservationData);
                }
            }

            // save for Satmya category when observation data is null
            if(cmExamntnDashavidhaDTO.getSatmya() != null) {
                if (cmExamntnDashavidhaDTO.getSatmya().getId() == null && cmExamntnDashavidhaDTO.getSatmya().getCmObservationDataDTO().getDataName() != null) {
                    cmSatmyaObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnDashavidhaDTO.getSatmya().getLookupValueId(),
                            cmExamntnDashavidhaDTO.getSatmya().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnDashavidha.setSatmya(cmSatmyaObservationData);
                }
            }

            // save for Vikruthi category when observation data is null
            if(cmExamntnDashavidhaDTO.getVikruthi() != null) {
                if (cmExamntnDashavidhaDTO.getVikruthi().getId() == null && cmExamntnDashavidhaDTO.getVikruthi().getCmObservationDataDTO().getDataName() != null) {
                    cmVikruthiObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnDashavidhaDTO.getVikruthi().getLookupValueId(),
                            cmExamntnDashavidhaDTO.getVikruthi().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnDashavidha.setVikruthi(cmVikruthiObservationData);
                }
            }
            if(cmExamntnDashavidhaDTO.getPrakruthi() != null || cmExamntnDashavidhaDTO.getSatmya() != null || cmExamntnDashavidhaDTO.getVayaha() != null ||
                    cmExamntnDashavidhaDTO.getPramana() != null || cmExamntnDashavidhaDTO.getVyayamaShakthi() != null || cmExamntnDashavidhaDTO.getAharaShakthi() != null
                    || cmExamntnDashavidhaDTO.getSamhanana() != null || cmExamntnDashavidhaDTO.getSara() != null || cmExamntnDashavidhaDTO.getSatva() != null
            || cmExamntnDashavidhaDTO.getVikruthi() != null) {
                cmExamntnType = cmExamntnTypeRepository.getOne(ErxConstants.DASHAVIDHA_EXAMINATION_ID);
                cmExamntnDashavidha = cmExamntnDashavidhaRepository.save(cmExamntnDashavidha);
                cmExamntnDetail.setCmExaminationType(cmExamntnType);
                cmExamntnDetail.setCmMasterDetails(cmMasterDetails);
                cmExamntnDetail.setCreatedDate(cmMasterDetails.getCaseCreatedDate());
                cmExamntnDetail.setCmExamId(cmExamntnDashavidha.getId());
                cmExamntnDetail = cmExamntnDetailRepository.save(cmExamntnDetail);
                allExaminatnDetails.add(cmExamntnDetail);
            }
        } catch (Exception e) {
           log.error("Failed to frame and save dashavidha pareeksha" + e.getMessage());
        }

        return cmExamntnDashavidha;
    }

    //saving AsthaVidha pareeksha
    private CmExamntnAsthaVidhaPareeksha frameAndSaveAsthaVidha(CmExamntnAsthaVidhaPareekshaDTO cmExamntnAsthaVidhaPareekshaDTO, Long clinicId, CmMasterDetails cmMasterDetails) {

        List<CmExamntnDetail> allExaminatnDetails = new ArrayList<CmExamntnDetail>();
        CmExamntnDetail cmExamntnDetail = new CmExamntnDetail();
        CmExamntnType cmExamntnType = new CmExamntnType();
        CmExamntnAsthaVidhaPareeksha cmExamntnAsthaVidhaPareeksha = new CmExamntnAsthaVidhaPareeksha();
        CmObservationCategoryData cmAkritiObservationData = null;
        CmObservationCategoryData cmSabdaObservationData = null;
        CmObservationCategoryData cmNetraObservationData = null;
        CmObservationCategoryData cmSparshaObservationData = null;
        CmObservationCategoryData cmMutraObservationData = null;
        CmObservationCategoryData cmMalaObservationData = null;
        CmObservationCategoryData cmNadiObservationData = null;
        CmObservationCategoryData cmJivhaObservationData = null;

        try {
            log.debug("Call to frame and save astha vidha pareeksha");
            // save for Akriti category when observation data is null
            if(cmExamntnAsthaVidhaPareekshaDTO.getAkriti() != null) {
                if (cmExamntnAsthaVidhaPareekshaDTO.getAkriti().getId() == null && cmExamntnAsthaVidhaPareekshaDTO.getAkriti().getCmObservationDataDTO().getDataName() != null) {
                    cmAkritiObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnAsthaVidhaPareekshaDTO.getAkriti().getLookupValueId(),
                            cmExamntnAsthaVidhaPareekshaDTO.getAkriti().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnAsthaVidhaPareeksha.setAkriti(cmAkritiObservationData);
                }
            }

            // save for Sabda category when observation data is null
            if(cmExamntnAsthaVidhaPareekshaDTO.getSabda() != null) {
                if (cmExamntnAsthaVidhaPareekshaDTO.getSabda().getId() == null && cmExamntnAsthaVidhaPareekshaDTO.getSabda().getCmObservationDataDTO().getDataName() != null) {
                    cmSabdaObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnAsthaVidhaPareekshaDTO.getSabda().getLookupValueId(),
                            cmExamntnAsthaVidhaPareekshaDTO.getSabda().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnAsthaVidhaPareeksha.setSabda(cmSabdaObservationData);
                }
            }

            // save for netra category when observation data is null
            if(cmExamntnAsthaVidhaPareekshaDTO.getNetra() != null) {
                if (cmExamntnAsthaVidhaPareekshaDTO.getNetra().getId() == null && cmExamntnAsthaVidhaPareekshaDTO.getNetra().getCmObservationDataDTO().getDataName() != null) {
                    cmNetraObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnAsthaVidhaPareekshaDTO.getNetra().getLookupValueId(),
                            cmExamntnAsthaVidhaPareekshaDTO.getNetra().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnAsthaVidhaPareeksha.setNetra(cmNetraObservationData);
                }
            }

            // save for sparsha category when observation data is null
            if(cmExamntnAsthaVidhaPareekshaDTO.getSparsha() != null) {
                if (cmExamntnAsthaVidhaPareekshaDTO.getSparsha().getId() == null && cmExamntnAsthaVidhaPareekshaDTO.getSparsha().getCmObservationDataDTO().getDataName() != null) {
                    cmSparshaObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnAsthaVidhaPareekshaDTO.getSparsha().getLookupValueId(),
                            cmExamntnAsthaVidhaPareekshaDTO.getSparsha().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnAsthaVidhaPareeksha.setSparsha(cmSparshaObservationData);
                }
            }

            // save for mutra category when observation data is null
            if(cmExamntnAsthaVidhaPareekshaDTO.getMutra() != null) {
                if (cmExamntnAsthaVidhaPareekshaDTO.getMutra().getId() == null && cmExamntnAsthaVidhaPareekshaDTO.getMutra().getCmObservationDataDTO().getDataName() != null) {
                    cmMutraObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnAsthaVidhaPareekshaDTO.getMutra().getLookupValueId(),
                            cmExamntnAsthaVidhaPareekshaDTO.getMutra().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnAsthaVidhaPareeksha.setMutra(cmMutraObservationData);
                }
            }

            // save for mala category when observation data is null
            if(cmExamntnAsthaVidhaPareekshaDTO.getMala() != null) {
                if (cmExamntnAsthaVidhaPareekshaDTO.getMala().getId() == null && cmExamntnAsthaVidhaPareekshaDTO.getMala().getCmObservationDataDTO().getDataName() != null) {
                    cmMalaObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnAsthaVidhaPareekshaDTO.getMala().getLookupValueId(),
                            cmExamntnAsthaVidhaPareekshaDTO.getMala().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnAsthaVidhaPareeksha.setMala(cmMalaObservationData);
                }
            }

            // save for nadi category when observation data is null
            if(cmExamntnAsthaVidhaPareekshaDTO.getNadi() != null) {
                if (cmExamntnAsthaVidhaPareekshaDTO.getNadi().getId() == null && cmExamntnAsthaVidhaPareekshaDTO.getNadi().getCmObservationDataDTO().getDataName() != null) {
                    cmNadiObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnAsthaVidhaPareekshaDTO.getNadi().getLookupValueId(),
                            cmExamntnAsthaVidhaPareekshaDTO.getNadi().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnAsthaVidhaPareeksha.setNadi(cmNadiObservationData);
                }
            }

            // save for Jivha category when observation data is null
            if(cmExamntnAsthaVidhaPareekshaDTO.getJivha() != null) {
                if (cmExamntnAsthaVidhaPareekshaDTO.getJivha().getId() == null && cmExamntnAsthaVidhaPareekshaDTO.getJivha().getCmObservationDataDTO().getDataName() != null) {
                    cmJivhaObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnAsthaVidhaPareekshaDTO.getJivha().getLookupValueId(),
                            cmExamntnAsthaVidhaPareekshaDTO.getJivha().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnAsthaVidhaPareeksha.setJivha(cmJivhaObservationData);
                }
            }
            if(cmExamntnAsthaVidhaPareekshaDTO.getAkriti() != null || cmExamntnAsthaVidhaPareekshaDTO.getSabda() != null || cmExamntnAsthaVidhaPareekshaDTO.getNetra() != null ||
                    cmExamntnAsthaVidhaPareekshaDTO.getSparsha() != null || cmExamntnAsthaVidhaPareekshaDTO.getMutra() != null || cmExamntnAsthaVidhaPareekshaDTO.getMala() != null
                    || cmExamntnAsthaVidhaPareekshaDTO.getNadi() != null || cmExamntnAsthaVidhaPareekshaDTO.getJivha() != null) {
                cmExamntnType = cmExamntnTypeRepository.getOne(ErxConstants.ASTHA_VIDHA_EXAMINATION_ID);
                cmExamntnAsthaVidhaPareeksha = cmExamntnAsthaVidhaPareekshaRepository.save(cmExamntnAsthaVidhaPareeksha);
                cmExamntnDetail.setCmExaminationType(cmExamntnType);
                cmExamntnDetail.setCmMasterDetails(cmMasterDetails);
                cmExamntnDetail.setCreatedDate(cmMasterDetails.getCaseCreatedDate());
                cmExamntnDetail.setCmExamId(cmExamntnAsthaVidhaPareeksha.getId());
                cmExamntnDetail = cmExamntnDetailRepository.save(cmExamntnDetail);
                allExaminatnDetails.add(cmExamntnDetail);
            }
        } catch (Exception e) {
           log.error("Failed to frame and save astha vidha pareeksha" + e.getMessage());
        }
        return cmExamntnAsthaVidhaPareeksha;
    }


    //saving general examnination
    private CmExamntnGeneral frameAndSaveGeneral(CmExamntnGeneralDTO cmExamntnGeneralDTO, Long clinicId, CmMasterDetails cmMasterDetails) {
        List<CmExamntnDetail> allExaminatnDetails = new ArrayList<CmExamntnDetail>();
        CmExamntnDetail cmExamntnDetail = new CmExamntnDetail();
        CmExamntnType cmExamntnType = new CmExamntnType();
        CmExamntnGeneral cmExamntnGeneral = new CmExamntnGeneral();

        CmObservationCategoryData cmConjuctivaObservationData = null;
        CmObservationCategoryData cmTongueObservationData = null;
        CmObservationCategoryData cmNailsObservationData = null;
        CmObservationCategoryData cmPulseObservationData = null;
        CmObservationCategoryData cmSkinObservationData = null;

        try {
            log.debug("Call to frame and save general examination");
            // save for Conjuctiva category when observation data is null
            if(cmExamntnGeneralDTO.getConjuctiva() != null) {
                if (cmExamntnGeneralDTO.getConjuctiva().getId() == null && cmExamntnGeneralDTO.getConjuctiva().getCmObservationDataDTO().getDataName() != null) {
                    cmConjuctivaObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnGeneralDTO.getConjuctiva().getLookupValueId(),
                            cmExamntnGeneralDTO.getConjuctiva().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnGeneral.setConjuctiva(cmConjuctivaObservationData);
                }
            }

            // save for tongue category when observation data is null
            if(cmExamntnGeneralDTO.getTongue() != null) {
                if (cmExamntnGeneralDTO.getTongue().getId() == null && cmExamntnGeneralDTO.getTongue().getCmObservationDataDTO().getDataName() != null) {
                    cmTongueObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnGeneralDTO.getTongue().getLookupValueId(),
                            cmExamntnGeneralDTO.getTongue().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnGeneral.setTongue(cmTongueObservationData);
                }
            }

            // save for nails category when observation data is null
            if(cmExamntnGeneralDTO.getNails() != null) {
                if (cmExamntnGeneralDTO.getNails().getId() == null && cmExamntnGeneralDTO.getNails().getCmObservationDataDTO().getDataName() != null) {
                    cmNailsObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnGeneralDTO.getNails().getLookupValueId(),
                            cmExamntnGeneralDTO.getNails().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnGeneral.setNails(cmNailsObservationData);
                }
            }

            // save for pulse category when observation data is null
            if(cmExamntnGeneralDTO.getPulse() != null) {
                if (cmExamntnGeneralDTO.getPulse().getId() == null && cmExamntnGeneralDTO.getPulse().getCmObservationDataDTO().getDataName() != null) {
                    cmPulseObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnGeneralDTO.getPulse().getLookupValueId(),
                            cmExamntnGeneralDTO.getPulse().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnGeneral.setPulse(cmPulseObservationData);
                }
            }

            // save for skin category when observation data is null
            if(cmExamntnGeneralDTO.getSkin() != null)
            if (cmExamntnGeneralDTO.getSkin().getId() == null && cmExamntnGeneralDTO.getSkin().getCmObservationDataDTO().getDataName() != null) {
                cmSkinObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnGeneralDTO.getSkin().getLookupValueId(),
                        cmExamntnGeneralDTO.getSkin().getCmObservationDataDTO().getDataName().trim(), clinicId);
                cmExamntnGeneral.setSkin(cmSkinObservationData);
            }

            if(cmExamntnGeneralDTO.getBloodPressure() != null){
                cmExamntnGeneral.setBloodPressure(cmExamntnGeneralDTO.getBloodPressure());
            }
            if(cmExamntnGeneralDTO.getBloodPressure() != null || cmExamntnGeneralDTO.getSkin() != null || cmExamntnGeneralDTO.getPulse() != null ||
                    cmExamntnGeneralDTO.getNails() != null || cmExamntnGeneralDTO.getTongue() != null || cmExamntnGeneralDTO.getConjuctiva() != null) {
                cmExamntnType = cmExamntnTypeRepository.getOne(ErxConstants.GENERAL_EXAMINATION_ID);
                cmExamntnGeneral = cmExamntnGeneralRepository.save(cmExamntnGeneral);
                cmExamntnDetail.setCmExaminationType(cmExamntnType);
                cmExamntnDetail.setCmMasterDetails(cmMasterDetails);
                cmExamntnDetail.setCreatedDate(cmMasterDetails.getCaseCreatedDate());
                cmExamntnDetail.setCmExamId(cmExamntnGeneral.getId());
                cmExamntnDetail = cmExamntnDetailRepository.save(cmExamntnDetail);
                allExaminatnDetails.add(cmExamntnDetail);
            }
        } catch (Exception e) {
            log.error("Failed to frame and save general examination" + e.getMessage());
        }
        return cmExamntnGeneral;
    }

    //saving sarva sroto pareeksha
    private CmExamntnSarvaSrotoPareeksha frameAndSaveSarvaSroto(CmExamntnSarvaSrotoPareekshaDTO cmExamntnSarvaSrotoPareekshaDTO, Long clinicId, CmMasterDetails cmMasterDetails) {
        List<CmExamntnDetail> allExaminatnDetails = new ArrayList<CmExamntnDetail>();
        CmExamntnDetail cmExamntnDetail = new CmExamntnDetail();
        CmExamntnType cmExamntnType = new CmExamntnType();
        CmExamntnSarvaSrotoPareeksha cmExamntnSarvaSrotoPareeksha = new CmExamntnSarvaSrotoPareeksha();
        CmObservationCategoryData cmCvsObservationData = null;
        CmObservationCategoryData cmRsObservationData = null;
        CmObservationCategoryData cmPaObservationData = null;
        CmObservationCategoryData cmCnsObservationData = null;
        CmObservationCategoryData cmPrObservationData = null;

        try {
            log.debug("Call to frame and save sarva sroto pareeksha");
            // save for cvs category when observation data is null
            if(cmExamntnSarvaSrotoPareekshaDTO.getCvs() != null) {
                if (cmExamntnSarvaSrotoPareekshaDTO.getCvs().getId() == null && cmExamntnSarvaSrotoPareekshaDTO.getCvs().getCmObservationDataDTO().getDataName() != null) {
                    cmCvsObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnSarvaSrotoPareekshaDTO.getCvs().getLookupValueId(),
                            cmExamntnSarvaSrotoPareekshaDTO.getCvs().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnSarvaSrotoPareeksha.setCvs(cmCvsObservationData);
                }
            }

            // save for rs category when observation data is null
            if(cmExamntnSarvaSrotoPareekshaDTO.getRs() != null) {
                if (cmExamntnSarvaSrotoPareekshaDTO.getRs().getId() == null && cmExamntnSarvaSrotoPareekshaDTO.getRs().getCmObservationDataDTO().getDataName() != null) {
                    cmRsObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnSarvaSrotoPareekshaDTO.getRs().getLookupValueId(),
                            cmExamntnSarvaSrotoPareekshaDTO.getRs().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnSarvaSrotoPareeksha.setRs(cmRsObservationData);
                }
            }

            // save for pa category when observation data is null
            if(cmExamntnSarvaSrotoPareekshaDTO.getPa() != null) {
                if (cmExamntnSarvaSrotoPareekshaDTO.getPa().getId() == null && cmExamntnSarvaSrotoPareekshaDTO.getPa().getCmObservationDataDTO().getDataName() != null) {
                    cmPaObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnSarvaSrotoPareekshaDTO.getPa().getLookupValueId(),
                            cmExamntnSarvaSrotoPareekshaDTO.getPa().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnSarvaSrotoPareeksha.setPa(cmPaObservationData);
                }
            }

            // save for cns category when observation data is null
            if(cmExamntnSarvaSrotoPareekshaDTO.getCns() != null) {
                if (cmExamntnSarvaSrotoPareekshaDTO.getCns().getId() == null && cmExamntnSarvaSrotoPareekshaDTO.getCns().getCmObservationDataDTO().getDataName() != null) {
                    cmCnsObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnSarvaSrotoPareekshaDTO.getCns().getLookupValueId(),
                            cmExamntnSarvaSrotoPareekshaDTO.getCns().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnSarvaSrotoPareeksha.setCns(cmCnsObservationData);
                }
            }

            // save for pr category when observation data is null
            if(cmExamntnSarvaSrotoPareekshaDTO.getPr() != null) {
                if (cmExamntnSarvaSrotoPareekshaDTO.getPr().getId() == null && cmExamntnSarvaSrotoPareekshaDTO.getPr().getCmObservationDataDTO().getDataName() != null) {
                    cmPrObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnSarvaSrotoPareekshaDTO.getPr().getLookupValueId(),
                            cmExamntnSarvaSrotoPareekshaDTO.getPr().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnSarvaSrotoPareeksha.setPr(cmPrObservationData);
                }
            }

            if(cmExamntnSarvaSrotoPareekshaDTO.getLocalExamination() != null){
                cmExamntnSarvaSrotoPareeksha.setLocalExamination(cmExamntnSarvaSrotoPareekshaDTO.getLocalExamination());
            }
            if(cmExamntnSarvaSrotoPareekshaDTO.getLocoMotorSystem() != null){
                cmExamntnSarvaSrotoPareeksha.setLocoMotorSystem(cmExamntnSarvaSrotoPareekshaDTO.getLocoMotorSystem());
            }
            if(cmExamntnSarvaSrotoPareekshaDTO.getLocoMotorSystem() != null || cmExamntnSarvaSrotoPareekshaDTO.getLocalExamination() != null || cmExamntnSarvaSrotoPareekshaDTO.getPr() != null ||
                    cmExamntnSarvaSrotoPareekshaDTO.getCns() != null || cmExamntnSarvaSrotoPareekshaDTO.getPa() != null || cmExamntnSarvaSrotoPareekshaDTO.getRs() != null ||
                    cmExamntnSarvaSrotoPareekshaDTO.getCvs() != null) {
                cmExamntnType = cmExamntnTypeRepository.getOne(ErxConstants.SARVA_SROTOPAREEKSHA_EXAMINATION_ID);
                cmExamntnSarvaSrotoPareeksha = cmExamntnSarvaSrotoPareekshaRepository.save(cmExamntnSarvaSrotoPareeksha);
                cmExamntnDetail.setCmExaminationType(cmExamntnType);
                cmExamntnDetail.setCmMasterDetails(cmMasterDetails);
                cmExamntnDetail.setCreatedDate(cmMasterDetails.getCaseCreatedDate());
                cmExamntnDetail.setCmExamId(cmExamntnSarvaSrotoPareeksha.getId());
                cmExamntnDetail = cmExamntnDetailRepository.save(cmExamntnDetail);
                allExaminatnDetails.add(cmExamntnDetail);
            }
        } catch (Exception e) {
           log.error("Failed to frame and save sarva sroto" + e.getMessage());
        }
        return cmExamntnSarvaSrotoPareeksha;
    }

    //saving samprapti ghatakas
    private CmExamntnSampraptiGhatakas frameAndSaveSamprapti(CmExamntnSampraptiGhatakasDTO cmExamntnSampraptiGhatakasDTO, Long clinicId, CmMasterDetails cmMasterDetails) {

        List<CmExamntnDetail> allExaminatnDetails = new ArrayList<CmExamntnDetail>();
        CmExamntnDetail cmExamntnDetail = new CmExamntnDetail();
        CmExamntnType cmExamntnType = new CmExamntnType();
        CmExamntnSampraptiGhatakas cmExamntnSampraptiGhatakas = new CmExamntnSampraptiGhatakas();
        CmObservationCategoryData cmDoshaObservationData = null;
        CmObservationCategoryData cmDooshyaObservationData = null;
        CmObservationCategoryData cmSrotasObservationData = null;
        CmObservationCategoryData cmSrotoDushtiObservationData = null;
        CmObservationCategoryData cmRogaMargaObservationData = null;
        CmObservationCategoryData cmSadhyaAsadhyataObservationData = null;

        try {
            log.debug("Call to frame and save samprapti ghatakas");
            // save for Dosha category when observation data is null
            if(cmExamntnSampraptiGhatakasDTO.getDosha() != null) {
                if (cmExamntnSampraptiGhatakasDTO.getDosha().getId() == null && cmExamntnSampraptiGhatakasDTO.getDosha().getCmObservationDataDTO().getDataName() != null) {
                    cmDoshaObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnSampraptiGhatakasDTO.getDosha().getLookupValueId(),
                            cmExamntnSampraptiGhatakasDTO.getDosha().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnSampraptiGhatakas.setDosha(cmDoshaObservationData);
                }
            }

            // save for Dooshya category when observation data is null
            if(cmExamntnSampraptiGhatakasDTO.getDooshya() != null) {
                if (cmExamntnSampraptiGhatakasDTO.getDooshya().getId() == null && cmExamntnSampraptiGhatakasDTO.getDooshya().getCmObservationDataDTO().getDataName() != null) {
                    cmDooshyaObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnSampraptiGhatakasDTO.getDooshya().getLookupValueId(),
                            cmExamntnSampraptiGhatakasDTO.getDooshya().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnSampraptiGhatakas.setDooshya(cmDooshyaObservationData);
                }
            }

            // save for srotas category when observation data is null
            if(cmExamntnSampraptiGhatakasDTO.getSrotas() != null) {
                if (cmExamntnSampraptiGhatakasDTO.getSrotas().getId() == null && cmExamntnSampraptiGhatakasDTO.getSrotas().getCmObservationDataDTO().getDataName() != null) {
                    cmSrotasObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnSampraptiGhatakasDTO.getSrotas().getLookupValueId(),
                            cmExamntnSampraptiGhatakasDTO.getSrotas().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnSampraptiGhatakas.setSrotas(cmSrotasObservationData);
                }
            }

            // save for sroto dushti category when observation data is null
            if(cmExamntnSampraptiGhatakasDTO.getSrotoDushti() != null) {
                if (cmExamntnSampraptiGhatakasDTO.getSrotoDushti().getId() == null && cmExamntnSampraptiGhatakasDTO.getSrotoDushti().getCmObservationDataDTO().getDataName() != null) {
                    cmSrotoDushtiObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnSampraptiGhatakasDTO.getSrotoDushti().getLookupValueId(),
                            cmExamntnSampraptiGhatakasDTO.getSrotoDushti().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnSampraptiGhatakas.setSrotoDushti(cmSrotoDushtiObservationData);
                }
            }

            // save for roga marga category when observation data is null
            if(cmExamntnSampraptiGhatakasDTO.getRogaMarga() != null) {
                if (cmExamntnSampraptiGhatakasDTO.getRogaMarga().getId() == null && cmExamntnSampraptiGhatakasDTO.getRogaMarga().getCmObservationDataDTO().getDataName() != null) {
                    cmRogaMargaObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnSampraptiGhatakasDTO.getRogaMarga().getLookupValueId(),
                            cmExamntnSampraptiGhatakasDTO.getRogaMarga().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnSampraptiGhatakas.setRogaMarga(cmRogaMargaObservationData);
                }
            }

            // save for sadhya asadhya category when observation data is null
            if(cmExamntnSampraptiGhatakasDTO.getSadhyaAsadhyata() != null) {
                if (cmExamntnSampraptiGhatakasDTO.getSadhyaAsadhyata().getId() == null && cmExamntnSampraptiGhatakasDTO.getSadhyaAsadhyata().getCmObservationDataDTO().getDataName() != null) {
                    cmSadhyaAsadhyataObservationData = saveCmObservationCategoryDataService.saveCmObservationData(cmExamntnSampraptiGhatakasDTO.getSadhyaAsadhyata().getLookupValueId(),
                            cmExamntnSampraptiGhatakasDTO.getSadhyaAsadhyata().getCmObservationDataDTO().getDataName().trim(), clinicId);
                    cmExamntnSampraptiGhatakas.setSadhyaAsadhyata(cmSadhyaAsadhyataObservationData);
                }
            }
            if(cmExamntnSampraptiGhatakasDTO.getSadhyaAsadhyata() != null || cmExamntnSampraptiGhatakasDTO.getRogaMarga() != null || cmExamntnSampraptiGhatakasDTO.getSrotoDushti() != null ||
                    cmExamntnSampraptiGhatakasDTO.getSrotas() != null || cmExamntnSampraptiGhatakasDTO.getDooshya() != null || cmExamntnSampraptiGhatakasDTO.getDosha() != null) {
                cmExamntnType = cmExamntnTypeRepository.getOne(ErxConstants.SAMPRAPTI_GHATAKAS_EXAMINATION_ID);
                cmExamntnSampraptiGhatakas = cmExamntnSampraptiGhatakasRepository.save(cmExamntnSampraptiGhatakas);
                cmExamntnDetail.setCmExaminationType(cmExamntnType);
                cmExamntnDetail.setCmMasterDetails(cmMasterDetails);
                cmExamntnDetail.setCreatedDate(cmMasterDetails.getCaseCreatedDate());
                cmExamntnDetail.setCmExamId(cmExamntnSampraptiGhatakas.getId());
                cmExamntnDetail = cmExamntnDetailRepository.save(cmExamntnDetail);
                allExaminatnDetails.add(cmExamntnDetail);
            }
        } catch (Exception e) {
            log.error("Failed to frame and save samprapti ghatakas" + e.getMessage());
        }

        return cmExamntnSampraptiGhatakas;
    }
}
