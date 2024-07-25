package com.hospital.hospital.repo;

import com.hospital.hospital.enums.AppointmentStatus;
import com.hospital.hospital.model.AppointmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepo extends JpaRepository<AppointmentDetails, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from appointment_details where doc_id= ?1 order by dateofappointment desc"
    )
    List<AppointmentDetails> findByDoctorId(Integer id);
    @Query(
            nativeQuery = true,
            value  ="select count(*) from appointment_details where doc_id= ?1 and appointment_status=?2 and dateofappointment= ?3"

    )

    int countByDoctorIdAndStatus(Integer doctorId, String appointmentStatus, LocalDate date);
    @Query(
            nativeQuery = true,
            value = "select * from appointment_details where user_id= ?1 "
    )
    List<AppointmentDetails> findByPatientId(Integer id);
}
