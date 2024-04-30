package com.azat4dev.demobooking.users.presentation.api.rest.authentication.entities;

import jakarta.validation.constraints.NotNull;

public record SignUpResponse(
        @NotNull AuthenticationResponse authenticationInfo,
        @NotNull String userId
) {

}