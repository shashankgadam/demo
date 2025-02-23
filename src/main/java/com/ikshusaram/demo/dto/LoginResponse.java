package com.ikshusaram.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ensures null values are not included in response
public class LoginResponse<T> {
    private String message;
    private int status;
    private boolean success;
    private String accessToken;
    private String refreshToken;
    private String role;

    // ✅ No-args constructor (important for JSON serialization)
    public LoginResponse() {}

    // ✅ Constructor with parameters
    public LoginResponse(String message, int status, boolean success, String accessToken, String refreshToken) {
        this.message = message;
        this.status = status;
        this.success = success;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        // this.role = role;
    }

    // ✅ Getters and setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String token) { this.accessToken = token; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String token) { this.refreshToken = token; }

    // public String getRole() { return role; }
    // public void setRole(String role) { this.role = role; }
}
