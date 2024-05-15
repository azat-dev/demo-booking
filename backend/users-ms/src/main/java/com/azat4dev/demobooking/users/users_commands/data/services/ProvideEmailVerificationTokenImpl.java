package com.azat4dev.demobooking.users.users_commands.data.services;

import com.azat4dev.demobooking.common.utils.TimeProvider;
import com.azat4dev.demobooking.users.common.domain.values.UserId;
import com.azat4dev.demobooking.users.common.presentation.security.services.jwt.JwtDataEncoder;
import com.azat4dev.demobooking.users.users_commands.domain.core.values.email.EmailAddress;
import com.azat4dev.demobooking.users.users_commands.domain.interfaces.services.EmailVerificationToken;
import com.azat4dev.demobooking.users.users_commands.domain.interfaces.services.ProvideEmailVerificationToken;
import lombok.RequiredArgsConstructor;

import java.time.temporal.ChronoUnit;
import java.util.Map;

@RequiredArgsConstructor
public final class ProvideEmailVerificationTokenImpl implements ProvideEmailVerificationToken {

    public static final String TYPE = "email_verification";

    private final long tokenExpirationInMs;
    private final JwtDataEncoder jwtDataEncoder;
    private final TimeProvider dateTimeProvider;

    @Override
    public EmailVerificationToken execute(UserId userId, EmailAddress emailAddress) {

        final var now = dateTimeProvider.currentTime();

        final var token = jwtDataEncoder.encode(
            userId.toString(),
            "self",
            now,
            now.plus(tokenExpirationInMs, ChronoUnit.MILLIS),
            Map.of(
                "email", emailAddress.getValue(),
                "type", TYPE
            )
        );

        return new EmailVerificationToken(token.toString());
    }
}
