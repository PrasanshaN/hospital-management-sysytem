package com.hospital.hospital.service.serviceImpl;

import com.hospital.hospital.dto.AppointmentDto;
import com.hospital.hospital.dto.EmailDto;
import com.hospital.hospital.enums.AppointmentStatus;
import com.hospital.hospital.enums.Role;
import com.hospital.hospital.exception.NoSeatAvailableException;
import com.hospital.hospital.exception.ResourceNotFoundException;
import com.hospital.hospital.model.AppointmentDetails;
import com.hospital.hospital.model.DoctorDetails;
import com.hospital.hospital.model.User;
import com.hospital.hospital.repo.AppointmentRepo;
import com.hospital.hospital.repo.DoctorRepo;
import com.hospital.hospital.repo.UserRepo;
import com.hospital.hospital.service.AppointmentService;
import com.hospital.hospital.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private EmailService emailService;

    @Override
    public void saveAppointment(AppointmentDto appointmentDto) {
        User patient = userRepo.findById(appointmentDto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if(!patient.getRole().equals(Role.PATIENT))
        {
            throw new RuntimeException("User must be patient!!");
        }
        DoctorDetails doctorDetails=doctorRepo.findById(appointmentDto.getDoctorId())
                .orElseThrow(()-> new  ResourceNotFoundException("Doctor Not Found"));


        AppointmentDetails appointmentDetails=new AppointmentDetails();
        appointmentDetails.setDoctorDetails(doctorDetails);
        appointmentDetails.setUser(patient);
        appointmentDetails.setDateOfAppointment(appointmentDto.getDateOfAppointment());
        appointmentDetails.setDescription(appointmentDto.getDescription());
        appointmentDetails.setAppointmentStatus(AppointmentStatus.PENDING);

        //status save garne ho yesma
       appointmentRepo.save(appointmentDetails);
       try {
           String message = String.format("Dear %s, \n Your Appointment has been booked to %s  %s.\n Confirmation is yet to come after the availability of seat is checked.", patient.getFullName(),doctorDetails.getSpeciality(), doctorDetails.getUser().getFullName());
           EmailDto emailDto = new EmailDto("Appointment Booked!", message, patient.getEmail());
           emailService.sendEmail(emailDto);
       }catch (Exception e){
           throw new RuntimeException("Appointment has been booked but unable to send email");
       }
    }

    @Override
  public List<AppointmentDto> findAppointmentsByDoctorId(Integer doctorId) {
    List<AppointmentDetails> appointmentList=appointmentRepo.findByDoctorId(doctorId);
    List<AppointmentDto>appointmentDtoList=appointmentList.stream()
            .map(appointments->{
                AppointmentDto appointmentDto=new AppointmentDto(appointments);
                return appointmentDto;
            }).collect(Collectors.toList());

return appointmentDtoList;

 }

    @Override
    public Map<String, Object> approveOrRejectAppointment(Integer appointmentId, boolean isApproved) {
        AppointmentDetails appointmentDetails=appointmentRepo.findById(appointmentId)
                .orElseThrow(()->new ResourceNotFoundException("Appointment not found"));

        if (appointmentDetails.getAppointmentStatus().equals(AppointmentStatus.ACCEPTED)){
            throw new RuntimeException("Appointmnet already approved.");
        }

        if (!isApproved){
            appointmentDetails.setAppointmentStatus(AppointmentStatus.REJECTED);
            appointmentRepo.save(appointmentDetails);
            return null;
        }

        DoctorDetails doctorDetails=doctorRepo.findById(appointmentDetails.getDoctorDetails().getId())
                .orElseThrow(()->new ResourceNotFoundException("Doctor not found"));
        User patient =userRepo.findById(appointmentDetails.getUser().getId())
                .orElseThrow(()-> new ResourceNotFoundException("Patient  Not Found"));
        boolean seatsAvailable=checkSeatAvailability(doctorDetails,appointmentDetails.getDateOfAppointment());
        if (isApproved && seatsAvailable) {
            appointmentDetails.setAppointmentStatus(AppointmentStatus.ACCEPTED);
            appointmentRepo.save(appointmentDetails);
            String message = String.format("Dear %s, \n Your Appointment has been confirmed.\n Please kindly visit between 10am to 12am.", patient.getFullName());
            EmailDto emailDto = new EmailDto("Appointment Confirmation", message, patient.getEmail());
            emailService.sendEmail(emailDto);
            return Map.of("message", "Appointment approved successfully", "status", "APPROVED");
        } else if (!isApproved) {
            appointmentDetails.setAppointmentStatus(AppointmentStatus.REJECTED);
            appointmentRepo.save(appointmentDetails);
            String message = String.format("Dear %s, \n Your Appointment has been rejected.\n Your appointment request was not aprroved.", patient.getFullName());
            EmailDto emailDto = new EmailDto("Appointment rejection", message, patient.getEmail());
            emailService.sendEmail(emailDto);
            return Map.of("message", "Appointment rejected successfully", "status", "REJECTED");
        } else {
            String message = String.format("Dear %s, \n Your Appointment has been rejected.\n The number of patient were already fulfilled.so try booking for tommorrow.", patient.getFullName());
            EmailDto emailDto = new EmailDto("Appointment Rejection", message, patient.getEmail());
            emailService.sendEmail(emailDto);
            throw new NoSeatAvailableException("No seats available");
        }

    }

    @Override
    public List<AppointmentDto> findAppointmentByPatientId(Integer patientId) {
        List<AppointmentDetails> appointmentList=appointmentRepo.findByPatientId(patientId);
        List<AppointmentDto>appointmentDtoList=appointmentList.stream()
                .map(appointments->{
                    AppointmentDto appointmentDto=new AppointmentDto(appointments);
                    return appointmentDto;
                }).collect(Collectors.toList());
        return appointmentDtoList;
    }

    @Override
    public void deleteById(Integer id) {
        AppointmentDetails appointmentDetails=appointmentRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Appointment not found"));
        if (!appointmentDetails.equals(AppointmentStatus.PENDING)){
            throw new RuntimeException("Appointment cannot be cancelled");
        }
        appointmentRepo.delete(appointmentDetails);
    }

    private boolean checkSeatAvailability(DoctorDetails doctorDetails, LocalDate dateOfAppointment) {

         int appointmentCount = appointmentRepo.countByDoctorIdAndStatus(doctorDetails.getId(),AppointmentStatus.ACCEPTED.name(),dateOfAppointment);
        return appointmentCount < doctorDetails.getMaxSeats();
    }
}
