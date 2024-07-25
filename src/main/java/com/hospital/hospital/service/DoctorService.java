package com.hospital.hospital.service;

import com.hospital.hospital.dto.AppointmentDto;
import com.hospital.hospital.dto.DoctorDto;
import com.hospital.hospital.model.DoctorDetails;

public interface DoctorService {
    public void save(DoctorDto doctorDto);
    public void deleteDoctorById(Integer id);
    public DoctorDto updateDoctorById(Integer id, DoctorDto  doctorDto);
   // private boolean checkSeatAvailability(DoctorDetails do)
}
