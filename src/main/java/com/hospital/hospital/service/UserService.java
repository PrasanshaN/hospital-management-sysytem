package com.hospital.hospital.service;

import com.hospital.hospital.dto.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    List<UserDto> getAll();
    UserDto getById(Integer id);
    UserDto updateById(Integer id,UserDto userDto);
    void deleteById(Integer id);

}
