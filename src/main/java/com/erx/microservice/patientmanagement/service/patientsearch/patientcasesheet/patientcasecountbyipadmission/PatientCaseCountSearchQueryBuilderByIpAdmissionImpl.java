package com.erx.microservice.patientmanagement.service.patientsearch.patientcasesheet.patientcasecountbyipadmission;

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.repository.*;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.PatientCaseCountSearchDTO;
import com.erx.microservice.patientmanagement.service.patientsearch.PatientSearchServiceRequest;
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

/**
 * Created by Latha on 16/08/18.
 */

@Service("patientCaseCountSearchQueryBuilderByIpAdmission")
public class PatientCaseCountSearchQueryBuilderByIpAdmissionImpl implements PatientCaseCountSearchQueryBuilderByIpAdmission {
    private final Logger log = LoggerFactory.getLogger(PatientCaseCountSearchQueryBuilderByIpAdmissionImpl.class);

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    CmMasterRepository cmMasterRepository;

  @Override
    public Page<PatientCaseCountSearchDTO> getPatientSearchResults(PatientSearchServiceRequest request) {
      Page<Object[]> ipAdmissions = null;
      List<PatientCaseCountSearchDTO> patientCaseCountSearchDTOs = new ArrayList<PatientCaseCountSearchDTO>();
      Page<PatientCaseCountSearchDTO> patientCaseCountSearchDTOPage = null;
        try {
            log.debug("Call to search patient case count by " + request.getPatientSearchCriteria().getSearchType() + " for the value " + request.getPatientSearchCriteria().getSearchValue());
            Pageable pageable = new PageRequest(request.getPageable().getPageNumber(), request.getPageable().getPageSize());
            ipAdmissions = patientRepository.getIpAdmissionByIpAdmissionNumber(request.getPatientSearchCriteria().getClinicId(),request.getPatientSearchCriteria().getSearchValue(),pageable);
            for (Object[] ipAdmissionDetail : ipAdmissions.getContent()) {
                IpAdmission ipAdmission = null;
                Patient patient = null;
                UserDetail userDetail = new UserDetail();
                List<CmMaster> cmMasters = null;
                PatientCaseCountSearchDTO patientCaseCountSearchDTO = new PatientCaseCountSearchDTO();
                patient = (Patient) ipAdmissionDetail[0];
                if (ipAdmissionDetail[1] != null) {
                    ipAdmission = (IpAdmission) ipAdmissionDetail[1];
                }
                patientCaseCountSearchDTO.setPatientId(patient.getId());
                patientCaseCountSearchDTO.setPatientMRN(patient.getPatientIdExternal());
                if (patient.getPatientSalutation() != null) {
                    patientCaseCountSearchDTO.setPatientSalutation(patient.getPatientSalutation().getName());
                }
                patientCaseCountSearchDTO.setPatientName(patient.getPatientName());
                patientCaseCountSearchDTO.setPatientDateOfBirth(patient.getDateOfBirth());
                patientCaseCountSearchDTO.setGender(patient.getGender());
                patientCaseCountSearchDTO.setMobileNumber(patient.getMobileNumber());
                // to find doctor id based on user id
                userDetail = userDetailRepository.findDoctorByUserId(request.getPatientSearchCriteria().getUserId(),request.getPatientSearchCriteria().getClinicId());
                if(userDetail != null)
                    // to retrieve patient case count
                    cmMasters = cmMasterRepository.getCaseCountByPatient(patient.getId(), request.getPatientSearchCriteria().getClinicLocationId(),userDetail.getId());
                patientCaseCountSearchDTO.setPatientCaseCount(Long.valueOf(cmMasters.size()));
                patientCaseCountSearchDTOs.add(patientCaseCountSearchDTO);
                patientCaseCountSearchDTOPage = patientCaseCountPageableObject(patient, patientCaseCountSearchDTOs);
            }
        } catch (Exception e) {
           log.error("failed to retrieve patient and case count based on ip admission");
        }
        return patientCaseCountSearchDTOPage;
    }

    private Page<PatientCaseCountSearchDTO> patientCaseCountPageableObject(Patient patient, List<PatientCaseCountSearchDTO> patientCaseCountSearchDTOs) {

        Page<PatientCaseCountSearchDTO> patientCaseCountSearchDTOPage = new Page<PatientCaseCountSearchDTO>() {
            @Override
            public Iterator<PatientCaseCountSearchDTO> iterator() {
                return null;
            }

            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<PatientCaseCountSearchDTO> getContent() {
                return patientCaseCountSearchDTOs;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public <S> Page<S> map(Converter<? super PatientCaseCountSearchDTO, ? extends S> converter) {
                return null;
            }
        };
        return patientCaseCountSearchDTOPage;
    }

    //method for pageable

}
