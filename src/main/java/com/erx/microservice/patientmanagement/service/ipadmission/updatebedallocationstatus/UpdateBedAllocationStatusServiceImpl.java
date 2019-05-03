package com.erx.microservice.patientmanagement.service.ipadmission.updatebedallocationstatus;

import com.erx.microservice.patientmanagement.domain.BedConfigurationMaster;
import com.erx.microservice.patientmanagement.domain.IpAdmission;
import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.repository.BedConfigurationMasterRepository;
import com.erx.microservice.patientmanagement.service.util.PatientConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erx.microservice.patientmanagement.repository.IpAdmissionRepository;

import java.util.Optional;

@Service
public class UpdateBedAllocationStatusServiceImpl implements UpdateBedAllocationStatusService {
    private final Logger log = LoggerFactory.getLogger(UpdateBedAllocationStatusServiceImpl.class);

    @Autowired
    IpAdmissionRepository ipAdmissionRepository;

    @Autowired
    BedConfigurationMasterRepository bedConfigurationMasterRepository;

    @Override
    public UpdateBedAllocationStatusServiceResponse execute( UpdateBedAllocationStatusServiceRequest request){

        UpdateBedAllocationStatusServiceResponse response;
        Patient patient = new Patient();
        patient.setId(request.getPatientId());
        try {
            Optional<IpAdmission> optionalIpAdmission = ipAdmissionRepository.findFirstByPatientOrderByCreatedOnDesc(patient);
            String bedAllocationStatus = null;
            switch(request.getBedMasterAllocationStatus()){
                case PatientConstants.IP_BED_STATUS_OCCUPIED:
                    bedAllocationStatus =PatientConstants.IP_BED_STATUS_OCCUPIED;
                    break;
                case PatientConstants.IP_BED_STATUS_AVAILABLE:
                    bedAllocationStatus=PatientConstants.IP_BED_STATUS_AVAILABLE;
                    break;
                default:
                    bedAllocationStatus=null;
            }
            if(optionalIpAdmission.isPresent()&&bedAllocationStatus!=null){
                if(bedAllocationStatus.equalsIgnoreCase(PatientConstants.IP_BED_STATUS_AVAILABLE)) {
                    IpAdmission ipAdmission = optionalIpAdmission.get();
                    ipAdmission.setIpAdmissionStatus(PatientConstants.IP_STATUS_DISCHARGED.toUpperCase());
                    //save IpAdmission
                    ipAdmissionRepository.save(ipAdmission);
                }
                BedConfigurationMaster bedConfigurationMaster = optionalIpAdmission.get().getBedMaster();
                bedConfigurationMaster.setAllocatedStatus(bedAllocationStatus);
                bedConfigurationMasterRepository.save(bedConfigurationMaster);
                response = new UpdateBedAllocationStatusServiceResponse();
                response.SUCCESSFUL=true;
                response.setMessage("Availability status of Bed  "+ bedConfigurationMaster.getBedNumber()+ " is updated to "
                        +bedConfigurationMaster.getAllocatedStatus());
                log.debug("UpdateBedAllocationStatusServiceImpl >> Availability status of Bed  "+ bedConfigurationMaster.getBedNumber()+ " is updated to "
                        +bedConfigurationMaster.getAllocatedStatus());
            }else {
                //throw no ipdamission for the patient or invalid bed status
                response = new UpdateBedAllocationStatusServiceResponse();
                response.SUCCESSFUL=false;
                response.setMessage("Failed to update the availability status >> "+"no ipdamission for the patient or invalid bed status");
                log.debug("UpdateBedAllocationStatusServiceImpl >> Failed to update the availability status >> "+"no ipdamission for the patient or invalid bed status");
            }
        }catch (Exception e){
            response = new UpdateBedAllocationStatusServiceResponse();
            response.SUCCESSFUL=false;
            response.setMessage("Exception in updating the availability status >> "+"no ipdamission for the patient or invalid bed status");
            log.debug("UpdateBedAllocationStatusServiceImpl >> Exception in updating the availability status >> "+"no ipdamission for the patient or invalid bed status");

        }

        return response;
    }



}
