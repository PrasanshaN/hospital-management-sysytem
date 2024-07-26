package com.hospital.hospital.controller;

import com.hospital.hospital.dto.UserDto;
import com.hospital.hospital.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public String saveUser(@Valid @RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return "Sucess";
    }

    @GetMapping("/list")
    public ResponseEntity fetchAllusers() {
        List<UserDto> data = userService.getAll();
        return ResponseEntity.ok(
                Map.of("message", "User List Fetched Successfully..", "data", data)
        );

    }
    @GetMapping("/id/{id}")
    public ResponseEntity fetchUserById (@PathVariable("id")Integer id)
    {
        UserDto Data=  userService.getById(id);
        return ResponseEntity.ok(
                Map.of("message","USER FETCHED SUCCESSFULLY!!!!","data",Data)
        );
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateUserById(
            @PathVariable("id") Integer id,
            @RequestBody UserDto userDto) {
        UserDto updatedData = userService.updateById(id, userDto);
        return ResponseEntity.ok(Map.of("message", "USER UPDATED SUCCESSFULLY!!!!", "data", updatedData));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteUserById(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "USER DELETED SUCCESSFULLY!!!!"));
    }
}