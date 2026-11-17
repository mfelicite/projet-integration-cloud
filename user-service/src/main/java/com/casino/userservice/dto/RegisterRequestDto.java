package com.casino.userservice.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDto(
    @NotBlank String username, 
    @NotBlank String password
) {}
