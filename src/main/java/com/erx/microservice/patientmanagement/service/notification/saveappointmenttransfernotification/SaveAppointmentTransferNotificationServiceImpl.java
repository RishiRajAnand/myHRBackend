package com.erx.microservice.patientmanagement.service.notification.saveappointmenttransfernotification;

/*
 * created by raushan on 13-07-2018
 * updated by Manisha on 06-10-2018
 * */

import com.erx.microservice.patientmanagement.config.Constants;
import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.*;
import com.erx.microservice.patientmanagement.service.dto.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SaveAppointmentTransferNotificationServiceImpl implements SaveAppointmentTransferNotificationService {

    private final Logger log = LoggerFactory.getLogger(SaveAppointmentTransferNotificationServiceImpl.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationTypeMasterRepository notificationTypeMasterRepository;
    @Autowired
    private ServiceGateway serviceGateway;
    @Autowired
    private PatientAppointmentRepository patientAppointmentRepository;
    @Autowired
    private DoctorSlotRepository doctorSlotRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private UserStaffRepository userStaffRepository;

    @Override
    public SaveAppointmentTransferNotificationServiceResponse execute(SaveAppointmentTransferNotificationServiceRequest request) throws ServiceException {
        SaveAppointmentTransferNotificationServiceResponse response = null;
        NotificationDTO notificationDTO = null;
        log.debug("Call to save Appointment Transfer Notification ");
        ClinicLocation clinicLocation = null;
        Notification notification = null;
        NotificationTypeMaster notificationTypeMaster = null;
        boolean status = true;
        UserStaff userStaffById = null;
        UserDetail userDetailById = null;
        try {
            notificationDTO = request.getNotificationDTO();
            response = isValidInput(notificationDTO);

            if (!response.SUCCESSFUL) {
                log.debug("Invalid Input");
                return response;
            }
            status = serviceGateway.cancelPatientAppointmentOrderAndDepositAmount(notificationDTO.getPatientAppointmentId());
            if (status) {
                //canceling patientAppointment
                PatientAppointment patientAppointment = patientAppointmentRepository.findOne(notificationDTO.getPatientAppointmentId());
                patientAppointment.setAppointmentStatus(Constants.APPOINTMENT_CANCEL_STATUS);
                patientAppointmentRepository.save(patientAppointment);
                DoctorSlot doctorSlot = patientAppointment.getDoctorSlot();
                doctorSlot.setSlotStatus(Constants.DOCTOR_SLOT_AVAILABLE);
                doctorSlotRepository.save(doctorSlot);
                //creating notification
                notification = new Notification();
                clinicLocation = new ClinicLocation();
                clinicLocation.setId(notificationDTO.getClinicLocationId());
                notification.setCliniclocation(clinicLocation);
                notification.setNotification_date(LocalDateTime.now());
                notification.setCreatedBy(notificationDTO.getUserId());
                UserStaff user = new UserStaff();
                user.setId(notificationDTO.getUserId());
                notification.setUser(user);
                notification.setNotification_status(Constants.NOTIFICATION_STATUS);
                notificationTypeMaster = notificationTypeMasterRepository.findNotificationTypeMasterByNotificationTypeName(Constants.APPOINTMENT_TRANSFER_NOTIFICATION_TYPE_NAME);
                notification.setNotificationTypeMaster(notificationTypeMaster);
                if (notificationDTO.getUserDetailId() != null) {
                    UserDetail userDetail = new UserDetail();
                    userDetail.setId(notificationDTO.getUserDetailId());
                    notification.setUserDetail(userDetail);
                }
                notification.setPatientAppointment(patientAppointment);
                notificationRepository.save(notification);
                userDetailById = userDetailRepository.findById(notificationDTO.getUserDetailId());
                if (userDetailById != null)
                    userStaffById = userStaffRepository.findOne(userDetailById.getUserStaff().getId());

                //construct response
                response = new SaveAppointmentTransferNotificationServiceResponse();
                response.setMessage("Appointment transfered successfully");
                response.setDoctorName("Dr. "+userStaffById.getFirstName().toUpperCase() + " " + userStaffById.getLastName().toUpperCase());
                response.SUCCESSFUL = true;
                log.debug("Appointment transfered successfully");
            } else {
                response = new SaveAppointmentTransferNotificationServiceResponse();
                response.SUCCESSFUL = false;
                response.setMessage("Failed to save Appointment Transfer");
                log.debug("Failed to save Appointment Transfer");
            }
        } catch (Exception e) {
            log.error("Failed to save Appointment Transfer");
            response = new SaveAppointmentTransferNotificationServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save Appointment Transfer");
        }
        return response;
    }

    private SaveAppointmentTransferNotificationServiceResponse isValidInput(NotificationDTO notificationDTO) throws Exception {
        SaveAppointmentTransferNotificationServiceResponse response = new SaveAppointmentTransferNotificationServiceResponse();
        response.SUCCESSFUL = false;
        String message = "Invalid input. Please provide mandatory fields";

        if (notificationDTO.getPatientAppointmentId() == null)
            response.setMessage(message);
        else if (notificationDTO.getUserId() == null) response.setMessage(message);
        else if (notificationDTO.getClinicLocationId() == null) response.setMessage(message);
        else {
            response.SUCCESSFUL = true;
            return response;
        }
        return response;
    }


}
