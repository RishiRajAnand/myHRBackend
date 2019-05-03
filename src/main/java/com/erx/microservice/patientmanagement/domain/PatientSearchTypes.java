package com.erx.microservice.patientmanagement.domain;

/**
 * Created by mkpatil on 28/12/17.
 */
public enum PatientSearchTypes {

    MRN("mrn"), MOBILE("mobile"), NAME("name"), VISITTYPE("visitType"), MRNVISIT("mrnVisit"), MOBILEVISIT("mobileVisit"),
    PATIENTID("patientId"), CAMPCRN("campCrn"), CAMPMOBILE("campMobile"), CAMPLOCATION("campLocation"), CAMPMRN("campMrn"),
    MRNDATERANGE("mrnDateRange"), CRNDATERANGE("crnDateRange"), NAMEVISIT(("nameVisit")), CAMPID("campId"),
    CAMPPATIENTNAME("campPatientName"), ALL("all"),IPADMISSIONNUMBER("ipAdmissionNumber");

    private String patientSearchType;

    PatientSearchTypes(String patientSearchType) {
        this.patientSearchType = patientSearchType;
    }


    public String getPatientSearchType() {
        return patientSearchType;
    }

    public void setPatientSearchType(String patientSearchType) {
        this.patientSearchType = patientSearchType;
    }
}
