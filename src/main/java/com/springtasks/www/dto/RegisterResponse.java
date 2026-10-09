package com.springtasks.www.dto;

public class RegisterResponse {

    private Integer userId;
    private String userName;
    private String fullName;
    private String phone;
    private String role;
    private boolean active;

    public RegisterResponse(
            Integer userId,
            String userName,
            String fullName,
            String phone,
            String role,
            boolean active) {

        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.phone = phone;
        this.role = role;
        this.active = active;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }
}