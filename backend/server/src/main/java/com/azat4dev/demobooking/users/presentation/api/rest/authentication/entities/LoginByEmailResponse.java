package com.azat4dev.demobooking.users.presentation.api.rest.authentication.entities;

public record LoginByEmailResponse(
    AuthenticationResponse tokens
) {
}