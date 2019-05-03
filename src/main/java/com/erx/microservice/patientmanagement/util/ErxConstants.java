package com.erx.microservice.patientmanagement.util;

/**
 * Created by vanantharaman on 4/19/17.
 */
public class ErxConstants {

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "qa";
    public static final String SPRING_PROFILE_SB = "sb";
    public static final String SPRING_PROFILE_UAT = "uat";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    public static final String SPRING_PROFILE_SWAGGER = "swagger";

    //ipAdmission constants
    public static final String IP_CURRENT_TABLE_NAME = "IP_ADMISSION";
    public static final String IP_CURRENT_COLUMN_NAME = "IP_ADMISSION_NUMBER";
    public static final String DAY_CARE_CURRENT_COLUMN_NAME = "DAYCARE_ADMISSION_NUMBER";

    //Deposit
    public static final String DM_CURRENT_TABLE_NAME = "DEPOSIT_MASTER";
    public static final String DM_CURRENT_COLUMN_NAME = "DEPOSIT_NUMBER";

    //PatientRefund
    public static final String RM_CURRENT_TABLE_NAME = "REFUND_MASTER";
    public static final String RM_CURRENT_COLUMN_NAME = "REFUND_NUMBER";

    //DepositTransfer
    public static final String DTM_CURRENT_TABLE_NAME = "PATIENT_DEPOSIT_TRANSFER";
    public static final String DTM_CURRENT_COLUMN_NAME = "TRANSFER_CODE";

    //CAMP
    public static final String CRN_CURRENT_TABLE_NAME = "PATIENT_CAMP_REGISTRATION";
    public static final String CRN_CURRENT_COLUMN_NAME = "CAMP_REGISTRATION_NUMBER";

    //IP_REQUEST
    public static final String IPR_CURRENT_TABLE_NAME = "IP_ADMISSION_REQUEST";
    public static final String IPR_CURRENT_COLUMN_NAME = "IP_REQUEST_NUMBER";

    //DISCHARGE_REQUEST
    public static final String CDR_CURRENT_TABLE_NAME = "CM_DISCHARGE_REQUEST";
    public static final String CDR_CURRENT_COLUMN_NAME = "DISCHARGE_REQUEST_NUMBER";

    //non registered patient
    public static final String PATIENT_ID_EXTERNAL_COLUMN_NAME = "PATIENT_ID_EXTERNAL";
    public static final String PATIENT_CURRENT_TABLE_NAME = "PATIENT";

    //BedConfigurationMaster
    public static final String BCM_CURRENT_TABLE_NAME = "BED_MASTER";
    public static final String BCM_CURRENT_COLUMN_NAME = "BED_CODE";

    //BedTypeMaster
    public static final String BTM_CURRENT_TABLE_NAME = "BED_TYPE_MASTER";
    public static final String BTM_CURRENT_COLUMN_NAME = "BED_TYPE_CODE";

    private ErxConstants() {
    }

    // case sheet examination details
    public static final Long DASHAVIDHA_EXAMINATION_ID = (long) 1;

    public static final Long GENERAL_EXAMINATION_ID = (long) 2;

    public static final Long SARVA_SROTOPAREEKSHA_EXAMINATION_ID = (long) 3;

    public static final Long ASTHA_VIDHA_EXAMINATION_ID = (long) 4;

    public static final Long SAMPRAPTI_GHATAKAS_EXAMINATION_ID = (long) 5;

    //patient all details constants
    public static final String PATIENT_SAVE = "PATIENT_DETAILS_SAVE";
    public static final String PATIENT_IP_SAVE = "PATIENT_IP_SAVE";
    public static final String PATIENT_UPDATE= "PATIENT_DETAILS_UPDATE";
    public static final String PATIENT_IP_UPDATE= "PATIENT_IP_UPDATE";
    public static final String PATIENT_MRN_SAVE= "PATIENT_MRN_SAVE";
    public static final String PATIENT_BED_TRANSFER_UPDATE= "PATIENT_BED_TRANSFER_UPDATE";
    public static final String PATIENT_APPOINTMENT_SAVE= "PATIENT_APPOINTMENT_SAVE";
    public static final String PATIENT_CONVERT_NON_REGISTERED_TO_REGISTERED= "PATIENT_CONVERT_NON_REGISTERED_TO_REGISTERED";




}
