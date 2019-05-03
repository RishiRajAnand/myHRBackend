package com.erx.microservice.patientmanagement.service.casemanagement.allcasesbypatientanddoctor;

/*
* created by Latha on 18-08-2018
* */

import com.erx.microservice.patientmanagement.domain.IpAdmissionCaseMapping;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.domain.UserDetail;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmCaseTransferHistoryRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterRepository;
import com.erx.microservice.patientmanagement.repository.UserDetailRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.IpAdmissionCaseMappingRepository;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.AllCasesDTO;
import org.hibernate.service.spi.ServiceException;
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

@Service("allCasesByPatientAndDoctorService")
public class AllCasesByPatientAndDoctorServiceImpl implements AllCasesByPatientAndDoctorService {

    private final Logger log = LoggerFactory.getLogger(AllCasesByPatientAndDoctorServiceImpl.class);

    @Autowired
    private CmMasterRepository cmMasterRepository;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    private IpAdmissionCaseMappingRepository ipAdmissionCaseMappingRepository;

    @Autowired
    private CmCaseTransferHistoryRepository cmCaseTransferHistoryRepository;

    @Override
    public AllCasesByPatientAndDoctorServiceResponse execute(AllCasesByPatientAndDoctorServiceRequest request) throws ServiceException {

        AllCasesByPatientAndDoctorServiceResponse response = null;
        List<AllCasesDTO> allCasesDTOs = new ArrayList<AllCasesDTO>();
        Page<AllCasesDTO> allCasesDTOsPage = null;
        Page<CmMaster> patientCaseDetail = null;
        UserDetail userDetail = new UserDetail();
        IpAdmissionCaseMapping ipAdmissionCaseMapping = new IpAdmissionCaseMapping();
        try {
            log.debug("Call to get patient case details by patient id and doctor id");
            if(request.getAllCasesRequestDTO().getPatientId() != null && request.getAllCasesRequestDTO().getClinicLocationId() != null &&
                    request.getAllCasesRequestDTO().getUserId() != null){
                Pageable pageable = new PageRequest(request.getPageable().getPageNumber(), request.getPageable().getPageSize());
                // to find doctor id based on user id
                userDetail = userDetailRepository.findDoctorByUserId(request.getAllCasesRequestDTO().getUserId(),request.getAllCasesRequestDTO().getClinicId());
                patientCaseDetail = cmMasterRepository.getCaseDetailsByPatientAndDoctor(request.getAllCasesRequestDTO().getClinicLocationId(), request.getAllCasesRequestDTO().getPatientId(),
                        userDetail.getId(),pageable);

            /*    patientCaseDetail =  cmCaseTransferHistoryRepository.getAllPatientCases(request.getAllCasesRequestDTO().getClinicLocationId(), request.getAllCasesRequestDTO().getPatientId(),
                        userDetail.getId(), request.getAllCasesRequestDTO().getInternalReferralTypeLookupValId(),pageable);
*/
                if(patientCaseDetail != null)
                    for(CmMaster cmMaster : patientCaseDetail){
                      AllCasesDTO allCasesDTO = new AllCasesDTO();
                        allCasesDTO.setPatientId(cmMaster.getPatient().getId());
                        allCasesDTO.setCaseId(cmMaster.getId());
                        allCasesDTO.setCaseNumber(cmMaster.getClinicCaseNumber());
                        allCasesDTO.setCaseCreatedDate(cmMaster.getCaseCreatedDate());
                        allCasesDTO.setCaseStatus(cmMaster.getCaseStatus());
                        allCasesDTO.setChiefComplaint(cmMaster.getChiefComplaint());
                        allCasesDTO.setPatientCaseCount(Long.valueOf(patientCaseDetail.getSize()));
                        ipAdmissionCaseMapping = ipAdmissionCaseMappingRepository.getIpDetailsByCaseId(cmMaster.getId());
                        if(ipAdmissionCaseMapping != null) {
                            if(ipAdmissionCaseMapping.getIpAdmission() != null)
                            if (ipAdmissionCaseMapping.getIpAdmission().getIpAdmissionNumber() != null) {
                                allCasesDTO.setIpDcAdmissionNumber(ipAdmissionCaseMapping.getIpAdmission().getIpAdmissionNumber());
                            } else {
                                allCasesDTO.setIpDcAdmissionNumber(ipAdmissionCaseMapping.getIpAdmission().getDayCareAdmissionNumber());
                            }
                        }
                        // add details to list
                        allCasesDTOs.add(allCasesDTO);
                    }
                allCasesDTOsPage = allCasesPageableObject(patientCaseDetail, allCasesDTOs);
            }
            response = new AllCasesByPatientAndDoctorServiceResponse(allCasesDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved patient Case details Successfully");
            log.debug("Retrieved patient Case Details Successfully");

        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve patient Case details");
            log.error("Failed to retrieve patient Case Details");
        }
        return response;
    }

    //method for pageable
    private Page<AllCasesDTO> allCasesPageableObject(Page<CmMaster> patientCaseDetail, List<AllCasesDTO> allCasesDTOs) {

        Page<AllCasesDTO> allCasesDTOsPageable = new Page<AllCasesDTO>() {

            @Override
            public Iterator<AllCasesDTO> iterator() {
                return null;
            }

            @Override
            public int getTotalPages() {
                return patientCaseDetail.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return patientCaseDetail.getTotalElements();
            }

            @Override
            public int getNumber() {
                return patientCaseDetail.getNumber();
            }

            @Override
            public int getSize() {
                return patientCaseDetail.getSize();
            }

            @Override
            public int getNumberOfElements() {
                return patientCaseDetail.getNumberOfElements();
            }

            @Override
            public List<AllCasesDTO> getContent() {
                return allCasesDTOs;
            }

            @Override
            public boolean hasContent() {
                return patientCaseDetail.hasContent();
            }

            @Override
            public Sort getSort() {
                return patientCaseDetail.getSort();
            }

            @Override
            public boolean isFirst() {
                return patientCaseDetail.isFirst();
            }

            @Override
            public boolean isLast() {
                return patientCaseDetail.isLast();
            }

            @Override
            public boolean hasNext() {
                return patientCaseDetail.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return patientCaseDetail.hasPrevious();
            }

            @Override
            public Pageable nextPageable() {
                return patientCaseDetail.nextPageable();
            }

            @Override
            public Pageable previousPageable() {
                return patientCaseDetail.previousPageable();
            }

            @Override
            public <S> Page<S> map(Converter<? super AllCasesDTO, ? extends S> converter) {
                return null;
            }
        };
        return allCasesDTOsPageable;
    }
}
