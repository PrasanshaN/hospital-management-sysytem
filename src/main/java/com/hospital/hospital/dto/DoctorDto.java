package com.hospital.hospital.dto;

import com.hospital.hospital.model.DoctorDetails;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DoctorDto {
    private Integer id;
    private String name;
    @NotNull(message = "NMC number Cannot be Null!!!")
    @NotBlank(message="NMC number Cannot be Blank!!!")
    private Integer nmc;
    @NotNull(message = "Speciality Cannot be Null!!!")
    @NotBlank(message="Speciality Cannot be Blank!!!")
    private String speciality;
    @NotNull(message = "Degree Cannot be Null!!!")
    @NotBlank(message="Degree Cannot be Blank!!!")
    private String  degree;
private Integer userId;
private String patientName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNmc() {
        return nmc;
    }

    public void setNmc(Integer nmc) {
        this.nmc = nmc;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public DoctorDto(Integer id, String name, Integer nmc, String speciality, String degree, Integer userId) {
        this.id = id;
        this.name = name;
        this.nmc = nmc;
        this.speciality = speciality;
        this.degree = degree;
        this.userId = userId;

    }
    public DoctorDto(DoctorDetails doctorDetails)
    {
        this.id=doctorDetails.getId();
        this.nmc=doctorDetails.getNmc();
        this.speciality=doctorDetails.getSpeciality();
        this.userId=doctorDetails.getUser().getId();
        this.patientName=doctorDetails.getUser().getFullName();

    }
}
