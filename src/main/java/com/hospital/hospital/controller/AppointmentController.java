package com.hospital.hospital.controller;

import com.hospital.hospital.dto.AppointmentDto;
import com.hospital.hospital.dto.DoctorDto;
import com.hospital.hospital.service.AppointmentService;
import com.hospital.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/details/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/save")
    public String saveUser(@RequestBody AppointmentDto appointmentDto) {
        appointmentService.saveAppointment(appointmentDto);
        return "Sucess";
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDto>> getAppointments(@PathVariable("doctorId") Integer doctorId) {
        List<AppointmentDto> appointments = appointmentService.findAppointmentsByDoctorId(doctorId);
        return ResponseEntity.ok(appointments);
    }
    @PutMapping("/acceptOrReject/{id}")
    public ResponseEntity<Map<String, Object>> approveOrRejectAppointment(
            @PathVariable("id") Integer id,
            @RequestParam("approved") boolean isApproved) {
        Map<String, Object> response = appointmentService.approveOrRejectAppointment(id, isApproved);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentDto>> getPatientAppointments(@PathVariable("patientId") Integer patientId) {
        List<AppointmentDto> appointments = appointmentService.findAppointmentByPatientId(patientId);
        return ResponseEntity.ok(appointments);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteUserById(@PathVariable("id") Integer id) {
        appointmentService.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "USER DELETED SUCCESSFULLY!!!!"));
    }
}

