package com.casino.userservice.dto;

import jakarta.validation.constraints.Positive;

public record DebitRequestDto(
    @Positive int amount
) {}
