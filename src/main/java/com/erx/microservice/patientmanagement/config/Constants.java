package com.erx.microservice.patientmanagement.config;

/**
 * Application constants.
 */
public final class Constants {

    //Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String DOCTOR_SLOT_AVAILABLE = "AVAILABLE";
    public static final String APPOINTMENT_CANCEL_STATUS = "CANCELED";
    public static final String NOTIFICATION_STATUS = "Planned";
    public static final String TRANSFER_HIGHER_BED_CONSTANT = "Higher Bed";
    public static final String TRANSFER_LOWER_BED_CONSTANT = "Lower Bed";
    public static final String TRANSFER_MAX_STAY_CONSTANT ="Max Stay";

    public static final String APPOINTMENT_TRANSFER_NOTIFICATION_TYPE_NAME = "Appointment Transfer";

      /*    constants for Bed Config Master     */
    public static final String BED_CONFIG_MASTER_DELETE_SUCCESS = "Deleted bed config  Successfully";

    public static final String BED_CONFIG_MASTER_DELETE_FAILURE = " Failed to Delete bed config ";

    public static final String BED_CONFIG_MASTER_DELETE_FAILURE_INVALID_INPUT = " Failed to Delete bed config due to invalid inputs";

     /*    constants for Bed Type Master     */

    public static final String BED_TYPE_MASTER_DELETE_SUCCESS = "Deleted bed type  Successfully";

    public static final String BED_TYPE_MASTER_DELETE_FAILURE = " Failed to Delete bed type ";

    public static final String BED_TYPE_MASTER_DELETE_FAILURE_INVALID_INPUT = " Failed to Delete bed type due to invalid inputs";


  /*    constants for Ward Master     */

    public static final String WARD_MASTER_DELETE_SUCCESS = "Deleted ward  Successfully";

    public static final String WARD_MASTER_DELETE_FAILURE = " Failed to Delete ward ";

    public static final String WARD_MASTER_DELETE_FAILURE_INVALID_INPUT = " Failed to Delete ward due to invalid inputs";

    /*    constants for Room Config Master   */

    public static final String ROOM_CONFIG_MASTER_DELETE_SUCCESS = "Deleted room config master  Successfully";

    public static final String ROOM_CONFIG_MASTER_DELETE_FAILURE = " Failed to Delete room config master ";

    public static final String ROOM_CONFIG_MASTER_DELETE_FAILURE_INVALID_INPUT = " Failed to Delete room config master due to invalid inputs";

    /*    constants for Patient Type Master     */

    public static final String PATIENT_TYPE_MASTER_DELETE_SUCCESS = "Deleted patient type  Successfully";

    public static final String PATIENT_TYPE__MASTER_DELETE_FAILURE = " Failed to Delete patient type ";

    public static final String PATIENT_TYPE__MASTER_DELETE_FAILURE_INVALID_INPUT = " Failed to Delete patient type due to invalid inputs";

    public static final String PRODUCT_TYPE = "Product";

    public static final String PRODUCT_UPDATE_CONSTANT = "UPDATE";

    public static final String THERAPY_MEDICINE_CONSTANT = "THERAPY_MEDICINE";

    public static final String THERAPY_CHECK_IN = "CHECKIN";

    public static final String THERAPY_CHECK_OUT = "CHECKOUT";

    public static final String PRODUCT_DELETE_CONSTANT = "DELETE";

    private Constants() {
    }
}
