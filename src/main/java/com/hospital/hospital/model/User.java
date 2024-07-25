package com.hospital.hospital.model;

import com.hospital.hospital.enums.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(
        name = "users",
uniqueConstraints={
                @UniqueConstraint(name = "UNIQUE_username",columnNames = "username"),
        @UniqueConstraint(name = "UNIQUE_email",columnNames = "email")

}
)

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", allocationSize = 1, sequenceName = "user_seq")
    private Integer id;
@Column(name = "username",nullable = true)
    private String username;
    private String password;
    private String contact;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String fullName;
    private String address;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;
    private boolean credentialNonExpired=true;
    private boolean enabled=true;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialNonExpired(boolean credentialNonExpired) {
        this.credentialNonExpired = credentialNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User(Integer id, String username, String password, String contact, Role role, String fullName, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.contact = contact;
        this.role = role;
        this.fullName = fullName;
        this.address = address;


    }

    public User(Integer id, String username, String password, String contact, Role role, String fullName, String address, String email, boolean accountNonExpired, boolean accountNonLocked, boolean credentialNonExpired, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.contact = contact;
        this.role = role;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialNonExpired = credentialNonExpired;
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", contact='" + contact + '\'' +
                ", role=" + role +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public User() {
    }

}
