package com.erx.microservice.patientmanagement.service.util;

/*
* created by Brighty on 26-03-2018
* */

public class PatientConstants {

    public static final String IMPORT_CAMP_CSV_INVALID_INPUT = "Please provide all Mandatory fields";

    public static final String IMPORT_CAMP_CSV_INVALID_MRN = "Invalid mrn for the patient ";

    public static final String IMPORT_CAMP_CSV_INVALID_PACKAGE = "Invalid package name for the patient ";

    public static final String DEFAULT_PATIENT_TYPE = "General ";

    public static final String GET_PATIENT_APPOINTMENT_SUCCESS =
            "Retrieved patient appointment detail Successfully for given id and visitType ";

    public static final String GET_PATIENT_APPOINTMENT_FAILURE =
            "Failed to retrieve patient appointment detail for given id and visitType ";

    public static final String INVALID_INPUT = "Invalid input ";

    public static final String GET_ALL_PATIENT_BY_CLINIC_LOCATION_SUCCESS = "Retrieved Patient Details Successfully ";

    public static final String GET_ALL_PATIENT_BY_CLINIC_LOCATION_FAILURE = "Failed to retrieve Patient Details";

    public static final String GET_PATIENT_IP_DETAILS_BY_PATIENT_ID_SUCCESS = "Retrieved Patient Details Successfully ";

    public static final String GET_PATIENT_IP_DETAILS_BY_PATIENT_ID_FAILURE = "Failed to retrieve Patient Details";

    public static final String SLOT = "slot";

    public static final String IP_ADMISSION_REQUEST_SAVE_SUCCESS = "IpAdmissionRequest saved successfully";

    public static final String IP_ADMISSION_REQUEST_SAVE_FAILURE = "Failed to save IpAdmissionRequest";

    public static final String IP_STATUS_DISCHARGED = "Discharged";

    public static final String IP_STATUS_ADMITTED = "Admitted";

    public static final String IP_DISCHARGE_SUCCESS = "Patient Discharged successfully";

    public static final String IP_DISCHARGE_FAILURE = "Failed to discharge patient";

    public static final String IP_BED_STATUS_AVAILABLE = "availability";

    public static final String IP_BED_STATUS_OCCUPIED = "occupied";

    public static final String PATIENT_APPOINTMENT_FAILURE = "Failed to retrieve Last appointment details";

    public static final String PATIENT_APPOINTMENT_SUCCESS = "Retrieved Last appointment details";

    public static final String CREATE_NON_REGISTERED_PATIENT_FAILED ="Failed to save non registerd patient";

    public static final String CREATE_NON_REGISTERED_PATIENT_SUCCESS="Patient details save succussfully";

    public static final String CASE_MODULE_CODE = "AYURCM01";

    public static final String REFERRAL_CASE_TYPE = "referral_case_type";

}
