package ru.sbercourses.rolechat.dto;

import ru.sbercourses.rolechat.model.enums.Role;

public class UserDto {
    private String username;
    private String password;

    private String name;
    private Role role;
    private String phoneNumber;

    public UserDto(String username, String password, String name, Role role, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
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
}
