package com.hospital.hospital.repo;

import com.hospital.hospital.model.DoctorDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<DoctorDetails,Integer> {
}
