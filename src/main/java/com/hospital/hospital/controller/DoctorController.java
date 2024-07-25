package com.hospital.hospital.controller;

import com.hospital.hospital.dto.DoctorDto;
import com.hospital.hospital.dto.UserDto;
import com.hospital.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/details/doc")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @PostMapping("/save")
    public String saveDoctor(@RequestBody @Validated DoctorDto doctorDto) {
        doctorService.save(doctorDto);
        return "Sucess";
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteDoctorById(@PathVariable("id") Integer id) {
        doctorService.deleteDoctorById(id);
        return ResponseEntity.ok(Map.of("message", "DOCTOR DELETED SUCCESSFULLY!!!!"));
    }


}