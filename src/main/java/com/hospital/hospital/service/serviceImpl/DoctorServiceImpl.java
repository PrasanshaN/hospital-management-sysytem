package com.hospital.hospital.service.serviceImpl;

import com.hospital.hospital.dto.AppointmentDto;
import com.hospital.hospital.dto.DoctorDto;
import com.hospital.hospital.enums.Role;
import com.hospital.hospital.exception.ResourceNotFoundException;
import com.hospital.hospital.model.DoctorDetails;
import com.hospital.hospital.model.User;
import com.hospital.hospital.repo.DoctorRepo;
import com.hospital.hospital.repo.UserRepo;
import com.hospital.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private UserRepo userRepo;


    @Override
    public void save(DoctorDto doctorDto) {

        User user = userRepo.findById(doctorDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if(!user.getRole().equals(Role.DOCTOR)){
            throw new RuntimeException("USER MUST BE DOCTOR");
        }
            DoctorDetails doctorDetail =new DoctorDetails();

        doctorDetail.setUser(user);
        doctorDetail.setNmc(doctorDto.getNmc());
        doctorDetail.setDegree(doctorDto.getDegree());
        doctorDetail.setSpeciality(doctorDto.getSpeciality());

        doctorRepo.save(doctorDetail);
    }

    @Override
    public void deleteDoctorById(Integer id) {
        if (!doctorRepo.existsById(id)){
            throw new RuntimeException("Doctor not Found");
        }
        doctorRepo.deleteById(id);
    }

    @Override
    public DoctorDto
    updateDoctorById(Integer id, DoctorDto doctorDto) {
        Optional <DoctorDetails> doctorDetailsOptional=doctorRepo.findById(id);
        if(doctorDetailsOptional.isPresent()){
            DoctorDetails doctorDetails=doctorDetailsOptional.get();
            doctorDetails.setDegree(doctorDto.getDegree());
            doctorDetails.setNmc(doctorDto.getNmc());
            DoctorDetails updatedetails=doctorRepo.save(doctorDetails);
            return new DoctorDto(updatedetails);

        }else{
            throw new ResourceNotFoundException("Doctor Not found");
        }

    }

}
