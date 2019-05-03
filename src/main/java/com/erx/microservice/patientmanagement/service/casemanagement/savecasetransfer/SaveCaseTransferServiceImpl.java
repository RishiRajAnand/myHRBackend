package com.erx.microservice.patientmanagement.service.casemanagement.savecasetransfer;

/*
* created by Latha on 10-10-2018
* */

import com.erx.microservice.patientmanagement.domain.LookupValue;
import com.erx.microservice.patientmanagement.domain.UserDetail;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmCaseTransferHistory;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.repository.LookupValueRepository;
import com.erx.microservice.patientmanagement.repository.UserDetailRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmCaseTransferHistoryRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.casebillingorderdto.CreateBillingOrderInputDTO;
import com.erx.microservice.patientmanagement.service.dto.casebillingorderdto.CreateBillingOrderServiceDTO;
import com.erx.microservice.patientmanagement.service.generatbillingorder.GenerateCaseSheetBillingOrderService;
import com.erx.microservice.patientmanagement.service.util.PatientConstants;
import org.hibernate.service.spi.ServiceException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("saveCaseTransferService")
public class SaveCaseTransferServiceImpl implements SaveCaseTransferService {

    private final Logger log = LoggerFactory.getLogger(SaveCaseTransferServiceImpl.class);

    @Autowired
    private CmMasterRepository cmMasterRepository;

    @Autowired
    private CmCaseTransferHistoryRepository cmCaseTransferHistoryRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private LookupValueRepository lookupValueRepository;

    @Autowired
    private GenerateCaseSheetBillingOrderService generateCaseSheetBillingOrderService;


    @Override
    public SaveCaseTransferServiceResponse execute(SaveCaseTransferServiceRequest request) throws ServiceException {
        SaveCaseTransferServiceResponse response = null;
        int saveStatus = 0;
        CmMaster cmMaster = null;
        UserDetail caseTransferredTo = null;
        UserDetail caseTransferredBy = null;
        UserDetail previouslyCaseTreatedBy = null;
        CmCaseTransferHistory cmCaseTransferHistory = null;
        long InternalLookupValId = 0;
        CreateBillingOrderInputDTO createBillingOrderInputDTO = new CreateBillingOrderInputDTO();
        Long bmOrderId = null;

        try {
            log.debug("Call to save transfer case");
            cmMaster = new CmMaster();
            caseTransferredTo = new UserDetail();
            caseTransferredBy = new UserDetail();
            previouslyCaseTreatedBy = new UserDetail();
            cmCaseTransferHistory = new CmCaseTransferHistory();
            cmMaster = cmMasterRepository.findOne(request.getCaseTransferRequestDTO().getCaseId());
            previouslyCaseTreatedBy = cmMaster.getUserDetail();
            caseTransferredTo = userDetailRepository.findOne(request.getCaseTransferRequestDTO().getTransferredDoctorId());

            caseTransferredBy = userDetailRepository.findDoctorByUserId(request.getCaseTransferRequestDTO().getUserId(),request.getCaseTransferRequestDTO().getClinicId());
            InternalLookupValId = getLookUpValueIdForInternalCaseReferral();
            if (InternalLookupValId == request.getCaseTransferRequestDTO().getReferralTypeLookupValId()) {
                cmMaster.setInternalTransfer(true);
            } else {
                cmMaster.setUserDetail(caseTransferredTo);
            }
            cmCaseTransferHistory.setCmMaster(cmMaster);
            cmCaseTransferHistory.setCaseTransferredOn(LocalDateTime.now());
            cmCaseTransferHistory.setCaseTransferredBy(caseTransferredBy);
            cmCaseTransferHistory.setCaseTransferredTo(caseTransferredTo);
            cmCaseTransferHistory.setPreviouslyCaseTreatedBy(previouslyCaseTreatedBy);
            cmCaseTransferHistory.setReferralTypeLookupValId(request.getCaseTransferRequestDTO().getReferralTypeLookupValId());
            cmCaseTransferHistory.setServiceId(request.getCaseTransferRequestDTO().getServiceId());

            cmMaster = cmMasterRepository.save(cmMaster);
            cmCaseTransferHistory = cmCaseTransferHistoryRepository.save(cmCaseTransferHistory);

            if(cmCaseTransferHistory != null) {
                // generating order and bill No
                createBillingOrderInputDTO.setClinicId(request.getCaseTransferRequestDTO().getClinicId());
                createBillingOrderInputDTO.setClinicLocationId(cmMaster.getClinicLocation().getId());
                createBillingOrderInputDTO.setPatientId(Long.valueOf(cmMaster.getPatient().getId()));
                createBillingOrderInputDTO.setOrderBy(Long.valueOf(request.getCaseTransferRequestDTO().getUserId()));
                createBillingOrderInputDTO.setType("CASE");
                List<CreateBillingOrderServiceDTO> createBillingOrderServiceDTOList = new ArrayList<>();
                CreateBillingOrderServiceDTO billingOrderServiceDTO = new CreateBillingOrderServiceDTO();
                billingOrderServiceDTO.setServiceId(request.getCaseTransferRequestDTO().getServiceId());
                billingOrderServiceDTO.setType("Service");
                billingOrderServiceDTO.setQuantity(1);
                createBillingOrderServiceDTOList.add(billingOrderServiceDTO);
                createBillingOrderInputDTO.setCreateBillingOrderServiceDTOList(createBillingOrderServiceDTOList);
                //calling case sheet billing order service to generate order for cases
                JSONObject jsonObject = generateCaseSheetBillingOrderService.generateOrderForCase(createBillingOrderInputDTO);
                if(jsonObject != null){
                    bmOrderId = jsonObject.getLong("bmOrderId");
                }
            }
            //create response
            response = new SaveCaseTransferServiceResponse(saveStatus,cmCaseTransferHistory.getId(),bmOrderId);
            response.setMessage("Case sheet transfer case saved successfully");
            log.debug("Case sheet transfer case saved successfully");
        } catch (Exception e) {
            response = new SaveCaseTransferServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to save case sheet transfer case details");
            response.setMessage(e.getMessage() + " so,Failed to save case sheet transfer case details");
        }
        return response;
    }

    private long getLookUpValueIdForInternalCaseReferral() {
        long referralTypeLookupValId = 0;
        List<LookupValue> lookupValues = null;
        try {
            //assigning lookUpName
            String lookUpName = PatientConstants.REFERRAL_CASE_TYPE;
            lookupValues = lookupValueRepository.findByLookupName(lookUpName);
            if (lookupValues != null) {
                for (LookupValue lookupValue : lookupValues) {
                    if (lookupValue.getName().equalsIgnoreCase("Internal")) {
                        referralTypeLookupValId = lookupValue.getId();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SaveCaseTransferServiceImpl == > getLookUpValueIdForInternalCaseReferral ==> ERROR : " + e.getMessage());
        }
        return referralTypeLookupValId;
    }
}
