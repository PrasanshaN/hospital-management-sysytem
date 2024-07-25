package com.hospital.hospital.service.serviceImpl;

import com.hospital.hospital.dto.UserDto;
import com.hospital.hospital.exception.ResourceNotFoundException;
import com.hospital.hospital.model.User;
import com.hospital.hospital.repo.UserRepo;
import com.hospital.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;//if not autowired we can use constructor or setter
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void saveUser(UserDto userdto) {
//       User userByUserName = userRepo.findByUsername(userdto.getUsername()).orElse(null);
        //User userByEmail = userRepo.findByEmail(userdto.getEmail()).orElse(null);
      //  Optional<User> userByEmail = userRepo.findByEmail(userdto.getEmail());
        //Optional<User> userByUserName = userRepo.findByUsername(userdto.getUsername());
     //   if(!userByEmail.isPresent()) {


         //   if (!userByUserName.isPresent()) {


                User user = new User();
                user.setUsername(userdto.getUsername());
                user.setAddress((userdto.getAddress()));

                String rawPassword = userdto.getPassword();
                String encodedPassword = passwordEncoder.encode(rawPassword);
                user.setPassword(encodedPassword);

                user.setFullName(userdto.getFullName());
                user.setRole(userdto.getRole());
                user.setContact(userdto.getContact());
                user.setEmail(userdto.getEmail());
                userRepo.save(user);
          //  } else {
            //    throw new RuntimeException("User already exists with username: "+userdto.getUsername());

          //  }
        }
        //else {
       //     throw new RuntimeException("User already exists with email: "+userdto.getEmail());
     //   }

   // }

    public List<UserDto> getAll() {
        List<User> userList = userRepo.findAll();
        List<UserDto> userDtoList = userList.stream()
                .map(user -> {
                    UserDto userDto = new UserDto(user);
                    return userDto;
                }).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public UserDto getById(Integer id) {
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDto userDto=new UserDto();
            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());
            userDto.setAddress(user.getAddress());
            userDto.setContact(user.getContact());
            userDto.setFullName(user.getFullName());
            userDto.setRole(user.getRole());
            user.setEmail(user.getEmail());
            return userDto;
        }
        else {
           throw new ResourceNotFoundException("USER NOT FOUND");
        }

    }

    @Override
    public UserDto updateById(Integer id, UserDto userDto) {
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setUsername(userDto.getUsername());
            user.setAddress(userDto.getAddress());
            user.setContact(userDto.getContact());
            user.setFullName(userDto.getFullName());
            user.setRole(userDto.getRole());
            user.setEmail(userDto.getEmail());
            User updatedUser = userRepo.save(user);
            return new UserDto(updatedUser);
        }else {
            throw new ResourceNotFoundException("USER NOT FOUND");
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepo.deleteById(id);
    }
}

