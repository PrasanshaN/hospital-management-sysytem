package com.hospital.hospital.dto;

public class AuthResponseDto {
    private String accessToken;
    private Long expirationTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public AuthResponseDto(String accessToken, Long expirationTime) {
        this.accessToken = accessToken;
        this.expirationTime = expirationTime;
    }
}
