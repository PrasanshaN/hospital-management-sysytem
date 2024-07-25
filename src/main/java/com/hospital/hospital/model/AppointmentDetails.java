package com.hospital.hospital.model;

import com.hospital.hospital.enums.AppointmentStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Optional;

@Entity
public class AppointmentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "appoint_seq")
    @SequenceGenerator(name = "appoint_seq",allocationSize = 1,initialValue = 0,sequenceName = "appoint_seq" )
    private Integer id;
    @Column(name = "dateofappointment")
    private LocalDate dateOfAppointment;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private String description;
    @ManyToOne
    @JoinColumn(name = "doc_id")
    private DoctorDetails doctorDetails;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public DoctorDetails getDoctorDetails() {
        return doctorDetails;
    }

    public void setDoctorDetails(DoctorDetails doctorDetails) {
        this.doctorDetails = doctorDetails;
    }

    public AppointmentDetails(Integer id, LocalDate dateOfAppointment, User user, String description, DoctorDetails doctorDetails) {
        this.id = id;
        this.dateOfAppointment = dateOfAppointment;
        this.user = user;
        this.description = description;
        this.doctorDetails = doctorDetails;
    }

    public AppointmentDetails() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
