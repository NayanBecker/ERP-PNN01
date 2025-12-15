package com.pnn.api.users.controller.dto;

public record LoginResponse(String accessToken, long expiresIn) {
}
