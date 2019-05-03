package com.erx.microservice.patientmanagement.service.patientsearch.patientcasesheet.patientcasecountbymrn;

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterRepository;
import com.erx.microservice.patientmanagement.repository.PatientRepository;
import com.erx.microservice.patientmanagement.repository.UserDetailRepository;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.PatientCaseCountSearchDTO;
import com.erx.microservice.patientmanagement.service.patientsearch.PatientSearchServiceRequest;
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

/**
 * Created by Latha on 16/08/18.
 */

@Service("patientCaseCountSearchQueryBuilderByMRN")
public class PatientCaseCountSearchQueryBuilderByMRNImpl implements PatientCaseCountSearchQueryBuilderByMRN {
    private final Logger log = LoggerFactory.getLogger(PatientCaseCountSearchQueryBuilderByMRNImpl.class);

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    CmMasterRepository cmMasterRepository;


    @Override
    public Page<PatientCaseCountSearchDTO> getPatientSearchResults(PatientSearchServiceRequest request) throws ServiceException {
        Page<Patient> patient = null;
        List<PatientCaseCountSearchDTO> patientCaseCountSearchDTOs = new ArrayList<PatientCaseCountSearchDTO>();
        Page<PatientCaseCountSearchDTO> patientCaseCountSearchDTOPage = null;
        try {
            log.debug("Call to search patient case count by " + request.getPatientSearchCriteria().getSearchType() + " for the value " + request.getPatientSearchCriteria().getSearchValue());
            Pageable pageable = new PageRequest(request.getPageable().getPageNumber(), request.getPageable().getPageSize());
            patient = patientRepository.getPatientDetailsByMRN(request.getPatientSearchCriteria().getClinicId(),request.getPatientSearchCriteria().getSearchValue(),pageable);
            if(patient != null)
                for(Patient patientDetails : patient){
                    UserDetail userDetail = new UserDetail();
                    List<CmMaster> cmMasters = null;
                    PatientCaseCountSearchDTO patientCaseCountSearchDTO = new PatientCaseCountSearchDTO();
                    patientCaseCountSearchDTO.setPatientId(patientDetails.getId());
                    patientCaseCountSearchDTO.setPatientMRN(patientDetails.getPatientIdExternal());
                    if (patientDetails.getPatientSalutation() != null) {
                        patientCaseCountSearchDTO.setPatientSalutation(patientDetails.getPatientSalutation().getName());
                    }
                    patientCaseCountSearchDTO.setPatientName(patientDetails.getPatientName());
                    patientCaseCountSearchDTO.setPatientDateOfBirth(patientDetails.getDateOfBirth());
                    patientCaseCountSearchDTO.setGender(patientDetails.getGender());
                    patientCaseCountSearchDTO.setMobileNumber(patientDetails.getMobileNumber());
                    // to find doctor id based on user id
                    userDetail = userDetailRepository.findDoctorByUserId(request.getPatientSearchCriteria().getUserId(),request.getPatientSearchCriteria().getClinicId());
                    if(userDetail != null)
                        // to retrieve patient case count
                        cmMasters = cmMasterRepository.getCaseCountByPatient(patientDetails.getId(), request.getPatientSearchCriteria().getClinicLocationId(),userDetail.getId());
                    patientCaseCountSearchDTO.setPatientCaseCount(Long.valueOf(cmMasters.size()));
                    patientCaseCountSearchDTOs.add(patientCaseCountSearchDTO);
                }
            patientCaseCountSearchDTOPage = patientCaseCountPageableObject(patient, patientCaseCountSearchDTOs);
        } catch (Exception e) {
           log.error("Search patient case count by mrn failed" + e.getMessage());
        }
        return patientCaseCountSearchDTOPage;
    }

    //method for pageable
    private Page<PatientCaseCountSearchDTO> patientCaseCountPageableObject(Page<Patient> patient, List<PatientCaseCountSearchDTO> patientCaseCountSearchDTOs) {

        Page<PatientCaseCountSearchDTO> patientCaseCountSearchDTOPage = new Page<PatientCaseCountSearchDTO>() {

            @Override
            public int getTotalPages() {
                return patient.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return patient.getTotalElements();
            }

            @Override
            public <S> Page<S> map(Converter<? super PatientCaseCountSearchDTO, ? extends S> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return patient.getNumber();
            }

            @Override
            public int getSize() {
                return patient.getSize();
            }

            @Override
            public int getNumberOfElements() {
                return patient.getNumberOfElements();
            }

            @Override
            public List<PatientCaseCountSearchDTO> getContent() {
                return patientCaseCountSearchDTOs;
            }

            @Override
            public boolean hasContent() {
                return patient.hasContent();
            }

            @Override
            public Sort getSort() {
                return patient.getSort();
            }

            @Override
            public boolean isFirst() {
                return patient.isFirst();
            }

            @Override
            public boolean isLast() {
                return patient.isLast();
            }

            @Override
            public boolean hasNext() {
                return patient.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return patient.hasPrevious();
            }

            @Override
            public Pageable nextPageable() {
                return patient.nextPageable();
            }

            @Override
            public Pageable previousPageable() {
                return patient.previousPageable();
            }

            @Override
            public Iterator<PatientCaseCountSearchDTO> iterator() {
                return null;
            }
        };

        return patientCaseCountSearchDTOPage;
    }
}
