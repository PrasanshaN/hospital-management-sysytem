package com.hospital.hospital.dto;

import com.hospital.hospital.enums.AppointmentStatus;
import com.hospital.hospital.model.AppointmentDetails;
import com.hospital.hospital.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AppointmentDto {
    private Integer id;

    private String patientName;
    private String docName;
    @NotNull(message = "Description Cannot be Null!!!")
    @NotBlank(message="Description Cannot be Blank!!!")
    private String description;
    private Integer patientId;
    private Integer doctorId;
    @NotNull(message = "DateOfAppointment Cannot be Null!!!")
    @NotBlank(message="DateOfAppointment Cannot be Blank!!!")
private LocalDate dateOfAppointment;
private AppointmentStatus appointmentStatus;

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public  Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId( Integer doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public AppointmentDto(Integer id, String patientName, String docName, String description, Integer patientId, Integer doctorId, LocalDate dateOfAppointment, AppointmentStatus appointmentStatus) {
        this.id = id;
        this.patientName = patientName;
        this.docName = docName;
        this.description = description;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.dateOfAppointment = dateOfAppointment;
        this.appointmentStatus = appointmentStatus;
    }

    public AppointmentDto() {
    }
    public AppointmentDto(AppointmentDetails appointmentDetails) {
        this.id = appointmentDetails.getId();
        this.patientId = appointmentDetails.getUser().getId();
        this.patientName = appointmentDetails.getUser().getFullName();
        this.description = appointmentDetails.getDescription();
        this.doctorId=appointmentDetails.getDoctorDetails().getId();
        this.docName=appointmentDetails.getDoctorDetails().getUser().getFullName();
        this.dateOfAppointment =appointmentDetails.getDateOfAppointment();
        this.appointmentStatus=appointmentDetails.getAppointmentStatus();
    }
}
