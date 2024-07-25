package com.hospital.hospital.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doctordetails")
public class DoctorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "doc_seq")
    @SequenceGenerator(name = "doc_seq",allocationSize = 1,sequenceName = "doc_seq" )
    private Integer id;
    private Integer nmc;
    private String speciality;
    private String degree;
    @OneToOne
    @JoinColumn(name="users_id")
    private User user;
    private Integer maxSeats=15;

    public Integer getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(Integer maxSeats) {
        this.maxSeats = maxSeats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DoctorDetails(Integer id, Integer nmc, String speciality, String degree, User user) {
        this.id = id;
        this.nmc = nmc;
        this.speciality = speciality;
        this.degree = degree;
        this.user = user;
    }

    @Override
    public String toString() {
        return "DoctorDetails{" +
                "id=" + id + ", nmc=" + nmc +
                ", speciality='" + speciality + '\'' +
                ", degree='" + degree + '\'' +
                '}';
    }

    public DoctorDetails() {
    }
}
