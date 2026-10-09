package com.springtasks.www.dto;

public class LoginResponse {

    private String token;
    private Integer userId;
    private String userName;
    private String fullName;
    private String role;

    public LoginResponse(
            String token,
            Integer userId,
            String userName,
            String fullName,
            String role) {

        this.token = token;
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.role = role;
    }

    public String getToken() {
        return token;
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

    public String getRole() {
        return role;
    }
}