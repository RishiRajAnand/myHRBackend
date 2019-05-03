package com.erx.microservice.patientmanagement.service.patientsearch;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.domain.PatientSearchTypes;
import com.erx.microservice.patientmanagement.service.dto.CampRegistrationDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.PatientCaseCountSearchDTO;
import com.erx.microservice.patientmanagement.service.dto.PatientSearchDTO;
import com.erx.microservice.patientmanagement.service.patientsearch.camppatientbycampid.CampPatientSearchQueryBuilderByCAMPId;
import com.erx.microservice.patientmanagement.service.patientsearch.camppatientbycrn.CampPatientSearchQueryBuilderByCRN;
import com.erx.microservice.patientmanagement.service.patientsearch.camppatientbydaterange.CampPatientSearchQueryBuilderByDateRange;
import com.erx.microservice.patientmanagement.service.patientsearch.camppatientbylocation.CampPatientSearchQueryBuilderByLocation;
import com.erx.microservice.patientmanagement.service.patientsearch.camppatientbymobile.CampPatientSearchQueryBuilderByMobile;
import com.erx.microservice.patientmanagement.service.patientsearch.camppatientbymrn.CampPatientSearchQueryBuilderByMRN;
import com.erx.microservice.patientmanagement.service.patientsearch.camppatientbyname.CampPatientSearchQueryBuilderByName;
import com.erx.microservice.patientmanagement.service.patientsearch.patientbyclinic.PatientSearchQueryBuilderByClinic;
import com.erx.microservice.patientmanagement.service.patientsearch.patientbydaterange.PatientSearchQueryBuilderByDateRange;
import com.erx.microservice.patientmanagement.service.patientsearch.patientbymobile.PatientSearchQueryBuilderByMobile;
import com.erx.microservice.patientmanagement.service.patientsearch.patientbymobilevisit.PatientSearchQueryBuilderByMobileVisit;
import com.erx.microservice.patientmanagement.service.patientsearch.patientbymrn.PatientSearchQueryBuilderByMRN;
import com.erx.microservice.patientmanagement.service.patientsearch.patientbymrnvisit.PatientSearchQueryBuilderByMRNVisit;
import com.erx.microservice.patientmanagement.service.patientsearch.patientbyname.PatientSearchQueryBuilderByName;
import com.erx.microservice.patientmanagement.service.patientsearch.patientbynamevisit.PatientSearchQueryBuilderByNameVisit;
import com.erx.microservice.patientmanagement.service.patientsearch.patientbypatientid.PatientSearchQueryBuilderByPatientId;
import com.erx.microservice.patientmanagement.service.patientsearch.patientbyvisitid.PatientSearchQueryBuilderByVisit;
import com.erx.microservice.patientmanagement.service.patientsearch.patientcasesheet.patientcasecountbyclinic.PatientCaseCountSearchQueryBuilderByClinic;
import com.erx.microservice.patientmanagement.service.patientsearch.patientcasesheet.patientcasecountbyipadmission.PatientCaseCountSearchQueryBuilderByIpAdmission;
import com.erx.microservice.patientmanagement.service.patientsearch.patientcasesheet.patientcasecountbymobile.PatientCaseCountSearchQueryBuilderByMobile;
import com.erx.microservice.patientmanagement.service.patientsearch.patientcasesheet.patientcasecountbymrn.PatientCaseCountSearchQueryBuilderByMRN;
import com.erx.microservice.patientmanagement.service.patientsearch.patientcasesheet.patientcasecountbyname.PatientCaseCountSearchQueryBuilderByName;
import com.erx.microservice.patientmanagement.service.patientsearch.patientcasesheet.patientcasecountbypatientid.PatientCaseCountSearchQueryBuilderById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by mkpatil on 28/12/17.
 */

@Service
public class PatientSearchFactoryImpl implements PatientSearchFactory {

    @Autowired
    PatientSearchQueryBuilderByMRN patientSearchQueryBuilderByMRN;

    @Autowired
    PatientSearchQueryBuilderByMobile patientSearchQueryBuilderByMobile;

    @Autowired
    PatientSearchQueryBuilderByName patientSearchQueryBuilderByName;

    @Autowired
    PatientSearchQueryBuilderByMRNVisit patientSearchQueryBuilderByMRNVisit;

    @Autowired
    PatientSearchQueryBuilderByMobileVisit patientSearchQueryBuilderByMobileVisit;

    @Autowired
    PatientSearchQueryBuilderByVisit patientSearchQueryBuilderByVisit;

    @Autowired
    CampPatientSearchQueryBuilderByCRN campPatientSearchQueryBuilderByCRN;

    @Autowired
    CampPatientSearchQueryBuilderByLocation campPatientSearchQueryBuilderByLocation;

    @Autowired
    CampPatientSearchQueryBuilderByMobile campPatientSearchQueryBuilderByMobile;

    @Autowired
    CampPatientSearchQueryBuilderByMRN campPatientSearchQueryBuilderByMRN;

    @Autowired
    CampPatientSearchQueryBuilderByDateRange campPatientSearchQueryBuilderByDateRange;

    @Autowired
    PatientSearchQueryBuilderByDateRange patientSearchQueryBuilderByDateRange;

    @Autowired
    PatientSearchQueryBuilderByClinic patientSearchQueryBuilderByClinic;

    @Autowired
    private PatientSearchQueryBuilderByNameVisit patientSearchQueryBuilderByNameVisit;

    @Autowired
    private CampPatientSearchQueryBuilderByCAMPId campPatientSearchQueryBuilderByCAMPId;

    @Autowired
    private CampPatientSearchQueryBuilderByName campPatientSearchQueryBuilderByName;

    @Autowired
    private PatientSearchQueryBuilderByPatientId patientSearchQueryBuilderByPatientId;

    @Autowired
    private PatientCaseCountSearchQueryBuilderByMRN patientCaseCountSearchQueryBuilderByMRN;

    @Autowired
    private PatientCaseCountSearchQueryBuilderByMobile patientCaseCountSearchQueryBuilderByMobile;

    @Autowired
    private PatientCaseCountSearchQueryBuilderByName patientCaseCountSearchQueryBuilderByName;

    @Autowired
    private PatientCaseCountSearchQueryBuilderByIpAdmission patientCaseCountSearchQueryBuilderByIpAdmission;

    @Autowired
    private PatientCaseCountSearchQueryBuilderById patientCaseCountSearchQueryBuilderById;

    @Autowired
    private PatientCaseCountSearchQueryBuilderByClinic patientCaseCountSearchQueryBuilderByClinic;

    //use searchType to get respective query builders
    @Override
    public List<PatientSearchDTO> getPatientSearch(PatientSearchCriteria patientSearchCriteria) {

        switch (PatientSearchTypes.valueOf(patientSearchCriteria.getSearchType())) {
            case MRN:
                return patientSearchQueryBuilderByMRN.getPatientSearchResults(patientSearchCriteria);
            case MOBILE:
                return patientSearchQueryBuilderByMobile.getPatientSearchResults(patientSearchCriteria);
            case NAME:
                return patientSearchQueryBuilderByName.getPatientSearchResults(patientSearchCriteria);
            case VISITTYPE:
                return patientSearchQueryBuilderByVisit.getPatientSearchResults(patientSearchCriteria);
            case MRNVISIT:
                return patientSearchQueryBuilderByMRNVisit.getPatientSearchResults(patientSearchCriteria);
            case MOBILEVISIT:
                return patientSearchQueryBuilderByMobileVisit.getPatientSearchResults(patientSearchCriteria);
            case MRNDATERANGE:
                return patientSearchQueryBuilderByDateRange.getPatientSearchResults(patientSearchCriteria);
            case NAMEVISIT:
                return patientSearchQueryBuilderByNameVisit.getPatientSearchResults(patientSearchCriteria);
            case PATIENTID:
                return patientSearchQueryBuilderByPatientId.getPatientSearchResults(patientSearchCriteria);

            default:
                return patientSearchQueryBuilderByClinic.getPatientSearchResults(patientSearchCriteria);
        }

    }

    @Override
    public List<CampRegistrationDTO> getCampPatientSearch(PatientSearchCriteria patientSearchCriteria) {

        switch (PatientSearchTypes.valueOf(patientSearchCriteria.getSearchType())) {
            case CAMPCRN:
                return campPatientSearchQueryBuilderByCRN.getCampPatientSearchResults(patientSearchCriteria);
            case CAMPMOBILE:
                return campPatientSearchQueryBuilderByMobile.getCampPatientSearchResults(patientSearchCriteria);
            case CAMPLOCATION:
                return campPatientSearchQueryBuilderByLocation.getCampPatientSearchResults(patientSearchCriteria);
            case CAMPMRN:
                return campPatientSearchQueryBuilderByMRN.getCampPatientSearchResults(patientSearchCriteria);
            case CRNDATERANGE:
                return campPatientSearchQueryBuilderByDateRange.getCampPatientSearchResults(patientSearchCriteria);
            case CAMPID:
                return campPatientSearchQueryBuilderByCAMPId.getCampPatientSearchResults(patientSearchCriteria);
            case CAMPPATIENTNAME:
                return campPatientSearchQueryBuilderByName.getCampPatientSearchResults(patientSearchCriteria);
            default:
                return campPatientSearchQueryBuilderByCRN.getCampPatientSearchResults(patientSearchCriteria);
        }
    }

    @Override
    public Page<PatientCaseCountSearchDTO> getPatientCaseCountSearch(PatientSearchServiceRequest request) {

        switch (PatientSearchTypes.valueOf(request.getPatientSearchCriteria().getSearchType())) {
            case MRN:
                return patientCaseCountSearchQueryBuilderByMRN.getPatientSearchResults(request);
            case MOBILE:
                return patientCaseCountSearchQueryBuilderByMobile.getPatientSearchResults(request);
            case NAME:
                return patientCaseCountSearchQueryBuilderByName.getPatientSearchResults(request);
            case IPADMISSIONNUMBER:
                return patientCaseCountSearchQueryBuilderByIpAdmission.getPatientSearchResults(request);
            case PATIENTID:
                return patientCaseCountSearchQueryBuilderById.getPatientSearchResults(request);
            case ALL:
                return patientCaseCountSearchQueryBuilderByClinic.getPatientSearchResults(request);
            default:
                return patientCaseCountSearchQueryBuilderByClinic.getPatientSearchResults(request);
        }
    }
}
