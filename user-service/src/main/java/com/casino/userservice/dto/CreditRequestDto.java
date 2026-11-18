package com.casino.userservice.dto;

import jakarta.validation.constraints.Positive;

public record CreditRequestDto(
    @Positive int amount
) {}
