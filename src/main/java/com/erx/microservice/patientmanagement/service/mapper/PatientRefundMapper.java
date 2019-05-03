package com.erx.microservice.patientmanagement.service.mapper;

import com.erx.microservice.patientmanagement.domain.PatientRefund;
import com.erx.microservice.patientmanagement.service.dto.PatientRefundDTO;
import com.erx.microservice.patientmanagement.service.dto.PatientRefundDetailDTO;
import org.mapstruct.Mapper;

import java.util.List;

/*
* created by Brighty on 14-11-2017
* */

@Mapper(componentModel = "spring", uses = {})
public interface PatientRefundMapper {

    PatientRefundDTO patientRefundToPatientRefundDTO(PatientRefund patientRefund);

    PatientRefund patientRefundDTOToPatientRefund(PatientRefundDTO patientRefundDTO);

    List<PatientRefundDTO> patientRefundsToPatientRefundDTOs(List<PatientRefund> patientRefunds);

    List<PatientRefund> patientRefundDTOsToPatientRefunds(List<PatientRefundDTO> patientRefundDTOS);

    //PatientRefundDetailDTO
    PatientRefundDetailDTO patientRefundToPatientRefundDetailDTO(PatientRefund patientRefund);

    PatientRefund patientRefundDetailDTOToPatientRefund(PatientRefundDetailDTO patientRefundDetailDTO);

    List<PatientRefundDetailDTO> patientRefundsToPatientRefundDetailDTOs(List<PatientRefund> patientRefunds);

    List<PatientRefund> patientRefundDetailDTOsToPatientRefunds(List<PatientRefundDetailDTO> patientRefundDetailDTOS);

}
