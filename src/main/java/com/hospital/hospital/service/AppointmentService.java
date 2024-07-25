package com.hospital.hospital.service;

import com.hospital.hospital.dto.AppointmentDto;

import java.util.List;
import java.util.Map;

public interface AppointmentService {
    public void saveAppointment(AppointmentDto appointmentDto);
    List<AppointmentDto> findAppointmentsByDoctorId(Integer doctorId);
    Map<String, Object> approveOrRejectAppointment(Integer appointmentId, boolean isApproved);

    List<AppointmentDto> findAppointmentByPatientId(Integer patientId);
    void deleteById(Integer id);
}
