package com.hospital.hospital.dto;

import com.hospital.hospital.enums.Role;
import com.hospital.hospital.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDto {
    private Integer id;
    @NotNull(message = "Name Cannot be Null!!!")
    @NotBlank(message="Name Cannot be Blank!!!")
    private String username;
    @NotNull(message = "password Cannot be Null!!!")
    @NotBlank(message="Password Cannot be Blank!!!")
    private String password;
    @NotNull(message = "Address Cannot be Null!!!")
    @NotBlank(message="Address Cannot be Blank!!!")
    private String address;
    @NotNull(message = "FullName Cannot be Null!!!")
    @NotBlank(message="FullName Cannot be Blank!!!")
    private String fullName;

    private Role role;
    private String contact;
    @NotNull(message = "Email Cannot be Null!!!")
    @NotBlank(message="Email Cannot be Blank!!!")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDto(Integer id, String username, String password, String address, String fullName, Role role, String contact,String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.fullName = fullName;
        this.role = role;
        this.contact = contact;
        this.email=email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public UserDto(User user)
    {
        this.id = user.getId();
        this.username = user.getUsername();
        this.address = user.getAddress();
        this.fullName = user.getFullName();
        this.role = user.getRole();
        this.contact=user.getContact();
        this.email=user.getEmail();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserDto() {
    }
}
