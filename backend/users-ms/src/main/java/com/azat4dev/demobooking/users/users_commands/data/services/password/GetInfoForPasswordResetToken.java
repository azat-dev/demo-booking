package com.azat4dev.demobooking.users.users_commands.data.services.password;

import com.azat4dev.demobooking.users.common.domain.values.UserId;
import com.azat4dev.demobooking.users.users_commands.domain.core.values.TokenForPasswordReset;

import java.time.LocalDateTime;

@FunctionalInterface
public interface GetInfoForPasswordResetToken {

    Data execute(TokenForPasswordReset token) throws InvalidTokenException;

    record Data(
        UserId userId,
        LocalDateTime expiresAt
    ) {
    }

    // Exceptions

    final class InvalidTokenException extends RuntimeException {
    }
}
