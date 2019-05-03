package com.erx.microservice.patientmanagement.service.casemanagement.savecomplaint;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.domain.casemanagement.*;
import com.erx.microservice.patientmanagement.repository.ClinicLocationRepository;
import com.erx.microservice.patientmanagement.repository.IpAdmissionRepository;
import com.erx.microservice.patientmanagement.repository.PatientRepository;
import com.erx.microservice.patientmanagement.repository.UserDetailRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.*;
import com.erx.microservice.patientmanagement.service.datautil.casemanagement.savecmobservationcategory.SaveCmObservationCategoryDataService;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmMasterDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveComplaintsDTO;
import com.erx.microservice.patientmanagement.service.mapper.casemanagement.CmMasterComplaintsMapper;
import com.erx.microservice.patientmanagement.service.mapper.casemanagement.CmMasterDetailsMapper;
import com.erx.microservice.patientmanagement.service.util.CaseStatus;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Service("saveComplaintService")
public class SaveComplaintServiceImpl implements SaveComplaintService {

    private final Logger log = LoggerFactory.getLogger(SaveComplaintServiceImpl.class);

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private CmMasterRepository cmMasterRepository;

    @Autowired
    private CmMasterDetailsRepository cmMasterDetailsRepository;

    @Autowired
    private CmMasterComplaintRepository cmMasterComplaintRepository;

    @Autowired
    private CmMasterComplaintsMapper cmMasterComplaintsMapper;

    @Autowired
    private ClinicLocationDetailRepository clinicLocationDetailRepository;

    @Autowired
    private IpAdmissionRepository ipAdmissionRepository;

    @Autowired
    private IpAdmissionCaseMappingRepository ipAdmissionCaseMappingRepository;

    @Autowired
    private CmMasterDetailsMapper cmMasterDetailsMapper;

    @Autowired
    private SaveCmObservationCategoryDataService saveCmObservationCategoryDataService;

    @Autowired
    private ClinicLocationRepository clinicLocationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public SaveComplaintServiceResponse execute(SaveComplaintServiceRequest request) throws ServiceException {
        SaveComplaintServiceResponse response = null;
        CmMaster cmMaster = new CmMaster();
        CmMaster savedCmMaster = new CmMaster();
        CmMasterComplaint cmMasterComplaints = new CmMasterComplaint();
        IpAdmissionCaseMapping ipAdmissionCaseMapping = null;
        IpAdmission ipAdmission = null;
        CmMasterDetails cmMasterDetails = null;
        CmMasterDetails savedCmMasterDetails = null;
        CmMasterDTO cmMasterDTO = new CmMasterDTO();
        try {
            log.debug("call to save complaints");
            if(request.getSaveComplaintsDTO() != null)
                if(request.getSaveComplaintsDTO().getCmMasterId() == null) {
                    cmMaster = frameCmMasterObject(request.getSaveComplaintsDTO());
                    // save cm master with chief complaint for saved status
                    savedCmMaster = cmMasterRepository.save(cmMaster);
                }else{
                    savedCmMaster = cmMasterRepository.findOne(request.getSaveComplaintsDTO().getCmMasterId());
                }
                // retrieve ipAdmission based on ip or dc number
            ipAdmission = ipAdmissionRepository.getIpAdmissionByIpDcNumber(request.getSaveComplaintsDTO().getIpDcAdmissionNumber());
            // Check for case mapped to ip admission
            if(ipAdmission != null) {
                if (request.getSaveComplaintsDTO().isIpCase()) {
                    ipAdmissionCaseMapping = checkIpExistInMapping(ipAdmission.getId(), savedCmMaster.getId());
                        if (ipAdmissionCaseMapping == null) {
                            ipAdmissionCaseMapping = new IpAdmissionCaseMapping();
                            ipAdmissionCaseMapping.setCmMaster(savedCmMaster);
                            ipAdmissionCaseMapping.setIpAdmission(ipAdmission);
                            ipAdmissionCaseMappingRepository.save(ipAdmissionCaseMapping);
                        }
                }
            }
            // save cm master details
            if(request.getSaveComplaintsDTO().getCmMasterDetailsDTO() != null) {
                cmMasterDetails = frameCmMasterDetailsObject(savedCmMaster, request.getSaveComplaintsDTO());
                if (cmMasterDetails != null) {
                    savedCmMasterDetails = cmMasterDetailsRepository.save(cmMasterDetails);
                    cmMasterDTO.setCmMasterDetailId(savedCmMasterDetails.getId());
                }
            }
            if(request.getSaveComplaintsDTO().getCmMasterComplaintDTO() != null) {
                // save cm master complaints
                cmMasterComplaints = frameCmMasterComplaints(savedCmMaster, request.getSaveComplaintsDTO());
                if (cmMasterComplaints != null) {
                    CmMasterComplaint savedCmMasterComplaints = cmMasterComplaintRepository.save(cmMasterComplaints);
                }
            }
            cmMasterDTO.setCaseId(savedCmMaster.getId());
            //create response
            response = new SaveComplaintServiceResponse(cmMasterDTO);
            response.setMessage("Case sheet saved successfully with case Number : " + savedCmMaster.getClinicCaseNumber());
            log.debug("Case sheet saved successfully with case Number : " + savedCmMaster.getClinicCaseNumber());
        } catch (Exception e) {
            response = new SaveComplaintServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to save case sheet");
            response.setMessage(e.getMessage() + " so,Failed to save case sheet");
        }
        return response;
    }

    //method to frame cmMaster complaint Details Object using mapper
    private CmMasterComplaint frameCmMasterComplaints(CmMaster savedCmMaster, SaveComplaintsDTO saveComplaintsDTO) {
        CmMasterComplaint cmMasterComplaint = new CmMasterComplaint();
        CmObservationCategoryData cmDeliveryModeObservationData = null;
        CmObservationCategoryData cmOhObservationData = null;
        CmObservationCategoryData cmMsObservationData = null;
        CmObservationCategoryData cmMcObservationData = null;
        CmObservationCategoryData cmCharacteristicsObservationData = null;
        cmMasterComplaint = cmMasterComplaintsMapper.cmMasterComplaintDTOToCmMasterComplaints(saveComplaintsDTO.getCmMasterComplaintDTO());
        cmMasterComplaint.setCmMaster(savedCmMaster);
        try {
            // save for delivery category when observation data is null
            if(saveComplaintsDTO.getCmMasterComplaintDTO().getDeliveryModeObservationDataDTO().getId() == null && saveComplaintsDTO.getCmMasterComplaintDTO().getDeliveryModeObservationDataDTO().getCmObservationDataDTO().getDataName() != null){
                cmDeliveryModeObservationData = saveCmObservationCategoryDataService.saveCmObservationData(saveComplaintsDTO.getCmMasterComplaintDTO().getDeliveryModeObservationDataDTO().getLookupValueId(),
                        saveComplaintsDTO.getCmMasterComplaintDTO().getDeliveryModeObservationDataDTO().getCmObservationDataDTO().getDataName().trim(),saveComplaintsDTO.getClinicId());
                cmMasterComplaint.setDeliveryModeObservationData(cmDeliveryModeObservationData);
            }
            // save for delivery category when oh data is null
            if(saveComplaintsDTO.getCmMasterComplaintDTO().getOhObservationDataDTO().getId() == null && saveComplaintsDTO.getCmMasterComplaintDTO().getOhObservationDataDTO().getCmObservationDataDTO().getDataName() != null){
                cmOhObservationData = saveCmObservationCategoryDataService.saveCmObservationData(saveComplaintsDTO.getCmMasterComplaintDTO().getOhObservationDataDTO().getLookupValueId(),
                        saveComplaintsDTO.getCmMasterComplaintDTO().getOhObservationDataDTO().getCmObservationDataDTO().getDataName().trim(),saveComplaintsDTO.getClinicId());
                cmMasterComplaint.setOhObservationData(cmOhObservationData);
            }
            // save for delivery category when ms data is null
            if(saveComplaintsDTO.getCmMasterComplaintDTO().getMsObservationDataDTO().getId() == null && saveComplaintsDTO.getCmMasterComplaintDTO().getMsObservationDataDTO().getCmObservationDataDTO().getDataName() != null){
                cmMsObservationData = saveCmObservationCategoryDataService.saveCmObservationData(saveComplaintsDTO.getCmMasterComplaintDTO().getMsObservationDataDTO().getLookupValueId(),
                        saveComplaintsDTO.getCmMasterComplaintDTO().getMsObservationDataDTO().getCmObservationDataDTO().getDataName().trim(),saveComplaintsDTO.getClinicId());
                cmMasterComplaint.setMsObservationData(cmMsObservationData);
            }
            // save for delivery category when mc data is null
            if(saveComplaintsDTO.getCmMasterComplaintDTO().getMcObservationDataDTO().getId() == null && saveComplaintsDTO.getCmMasterComplaintDTO().getMcObservationDataDTO().getCmObservationDataDTO().getDataName() != null){
                cmMcObservationData = saveCmObservationCategoryDataService.saveCmObservationData(saveComplaintsDTO.getCmMasterComplaintDTO().getMcObservationDataDTO().getLookupValueId(),
                        saveComplaintsDTO.getCmMasterComplaintDTO().getMcObservationDataDTO().getCmObservationDataDTO().getDataName().trim(),saveComplaintsDTO.getClinicId());
                cmMasterComplaint.setMcObservationData(cmMcObservationData);
            }
            // save for delivery category when characteristicsObservationData is null
            if(saveComplaintsDTO.getCmMasterComplaintDTO().getCharacteristicsObservationDataDTO().getId() == null && saveComplaintsDTO.getCmMasterComplaintDTO().getCharacteristicsObservationDataDTO().getCmObservationDataDTO().getDataName() != null){
                cmCharacteristicsObservationData = saveCmObservationCategoryDataService.saveCmObservationData(saveComplaintsDTO.getCmMasterComplaintDTO().getCharacteristicsObservationDataDTO().getLookupValueId(),
                        saveComplaintsDTO.getCmMasterComplaintDTO().getCharacteristicsObservationDataDTO().getCmObservationDataDTO().getDataName().trim(),saveComplaintsDTO.getClinicId());
                cmMasterComplaint.setCharacteristicsObservationData(cmCharacteristicsObservationData);
            }
        } catch (Exception e) {
            log.error("failed to frameCmMasterComplaints" + e.getMessage());
        }
        return cmMasterComplaint;
    }

    //method to frame cmMaster Details Object using mapper
    private CmMasterDetails frameCmMasterDetailsObject(CmMaster savedCmMaster, SaveComplaintsDTO saveComplaintsDTO) {
        CmMasterDetails cmMasterDetails = new CmMasterDetails();
        try {
            log.debug("call to frame cm master details");
            if(saveComplaintsDTO.getCmMasterDetailsDTO().getPatientAppointmentId() != null){
                saveComplaintsDTO.getCmMasterDetailsDTO().setPatientAppointmentId(saveComplaintsDTO.getCmMasterDetailsDTO().getPatientAppointmentId());
            }
            if(saveComplaintsDTO.getCmMasterDetailsDTO().getDoctorNote() != null){
                saveComplaintsDTO.getCmMasterDetailsDTO().setDoctorNote(saveComplaintsDTO.getCmMasterDetailsDTO().getDoctorNote().trim());
            }
            cmMasterDetails = cmMasterDetailsMapper.cmMasterDetailsDTOToCmMasterDetails(saveComplaintsDTO.getCmMasterDetailsDTO());
            cmMasterDetails.setCmMaster(savedCmMaster);
        } catch (Exception e) {
            log.error("failed to frame cm master details object");
        }
        return cmMasterDetails;
    }

    // method to check ip number exist in mapping
    private IpAdmissionCaseMapping checkIpExistInMapping(Long ipAdmissionNumber, Long cmMasterId) {
        IpAdmissionCaseMapping ipObjInMapping = null;
        try {
            log.debug("call to check ip number exist in mapping");
            ipObjInMapping = new IpAdmissionCaseMapping();
            // retrieve ip case mapping object based on ip admission id and cm master id
            ipObjInMapping = ipAdmissionCaseMappingRepository.getIpCaseDetailsByCaseId(ipAdmissionNumber, cmMasterId);
        } catch (Exception e) {
            log.error("failed to check ip number exists in mapping" + e.getMessage());
        }
        return ipObjInMapping;
    }

    // method to frame cm master object
    private CmMaster frameCmMasterObject(SaveComplaintsDTO saveComplaintsDTO) {
        CmMaster cmMaster = new CmMaster();
        String caseNumber = new String();
        UserDetail userDetail = new UserDetail();
        ClinicLocationDetail clinicLocationDetail = new ClinicLocationDetail();
        ClinicLocation clinicLocation = new ClinicLocation();
        Patient patient = new Patient();
        try {
            log.debug("frame case master object");
            //find doctor by user and clinic
            userDetail = userDetailRepository.findDoctorByUserId(saveComplaintsDTO.getUserId(),saveComplaintsDTO.getClinicId());
            //find clinic location detail which is case and lab order format by clinic location
            clinicLocationDetail = clinicLocationDetailRepository.getClinicLocationDetail(saveComplaintsDTO.getClinicLocationId());
            clinicLocation = clinicLocationRepository.findOne(saveComplaintsDTO.getClinicLocationId());
            patient = patientRepository.findOne(saveComplaintsDTO.getPatientId());
            cmMaster.setClinicLocation(clinicLocation);
            cmMaster.setPatient(patient);
            cmMaster.setUserDetail(userDetail);
            cmMaster.setChiefComplaint(saveComplaintsDTO.getChiefComplaint());
            if(saveComplaintsDTO.getCaseCreatedDate() != null) {
                cmMaster.setCaseCreatedDate(saveComplaintsDTO.getCaseCreatedDate());
            }else {
                cmMaster.setCaseCreatedDate(LocalDateTime.now());
            }
            caseNumber = generateCaseNumberByClinicLocation(clinicLocationDetail);
            if (caseNumber != null) {
                cmMaster.setClinicCaseNumber(caseNumber);
                cmMaster.setErxCaseNumber(caseNumber);
            }
            cmMaster.setCaseStatus(CaseStatus.CASE_SAVED_STATUS);

        } catch (Exception e) {
           log.error("failed to frame case master object" + e.getMessage());
        }
        return cmMaster;
    }

    // method to generate case number by clinic location
    @Override
    public String generateCaseNumberByClinicLocation(ClinicLocationDetail clinicLocationDetail) {
        int lastLabOrderNum = 0;
        int length = 0;
        long clinicLocationID = (long) 0;
        StringBuilder formatedLabOrder = new StringBuilder();
        String caseNumberFormat = new String();
        String lastCaseNumber = new String();
        String generatedCaseNum = new String();
        LocalDate presentDate = null;

        try {
            log.debug("call to generate case number by clinic location");
            clinicLocationID = clinicLocationDetail.getClinicLocation().getId();
            caseNumberFormat = clinicLocationDetail.getCaseIdFormat();
            lastCaseNumber = clinicLocationDetail.getLastCaseId();
            lastLabOrderNum = Integer.parseInt(lastCaseNumber);
            length = lastCaseNumber.length();
            lastLabOrderNum++;
            generatedCaseNum = (String.format("%0" + length + "d", lastLabOrderNum));
            presentDate = presentDate.now();
            formatedLabOrder.append(caseNumberFormat);
            formatedLabOrder.append(clinicLocationID);
            formatedLabOrder.append(presentDate.getDayOfMonth());
            formatedLabOrder.append(presentDate.getYear());
            formatedLabOrder.append(generatedCaseNum);
            String caseNumberTemp = new String();
            caseNumberTemp = String.valueOf(generatedCaseNum);
            clinicLocationDetail.setLastCaseId(caseNumberTemp);
            clinicLocationDetailRepository.save(clinicLocationDetail);
        } catch (NumberFormatException e) {
           log.error("failed to generate case number by clinic location");
        }
        return formatedLabOrder.toString();
    }
}
