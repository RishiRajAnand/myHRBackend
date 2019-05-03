package com.erx.microservice.patientmanagement.service.patientsearch;

public class PatientSearchUtil {


    public static String PATIENT_DETAIL_BASE_QUERY = "select p, ip,ibm from Patient p " +
            "left join p.ipAdmission ip " +
            "with trim(upper(ip.ipAdmissionStatus)) != 'DISCHARGED' " +
            "left join ip.ipAdmissionBedMovements ibm with ibm.currentBed = 1  ";

    public static String CAMP_PATIENT_DETAIL_BASE_QUERY = "select p, a, pcr from Patient p " +
            "left join p.addressInfo a " +
            "left join p.patientCampRegistrations pcr ";

    public static String IP_ADMISSION_REQUEST_BASE_QUERY = "select ipr from IpAdmissionRequest ipr " +
            "Join ipr.cmMaster cmm left Join cmm.clinicLocation cl Join cmm.patient p";

    public static String DISCHARGE_REQUEST_BASE_QUERY = "select dr from CmDischargeRequest dr " +
            "Join dr.cmMaster cmm left Join cmm.clinicLocation cl Join cmm.patient p";
}
