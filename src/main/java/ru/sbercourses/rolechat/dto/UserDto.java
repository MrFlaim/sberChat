package ru.sbercourses.rolechat.dto;

import ru.sbercourses.rolechat.model.enums.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserDto {
    @NotBlank(message = "Username can not be empty")
    private String username;
    @NotBlank(message = "password can not be empty")
    private String password;
    @NotBlank(message = "Name can not be empty")
    private String name;
    private String surname;
    private Role role;

    @NotBlank(message = "Phone number can not be empty")
    @Pattern(regexp = "\\d{11}", message = "Phone number must contain 11 digits")
    private String phoneNumber;

    public UserDto(String username, String password, String name, String surname, Role role, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    public UserDto() {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
