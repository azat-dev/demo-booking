package com.azat4dev.demobooking.users.users_commands.data.services.password;

import com.azat4dev.demobooking.common.utils.TimeProvider;
import com.azat4dev.demobooking.users.common.domain.values.UserId;
import com.azat4dev.demobooking.users.common.presentation.security.services.jwt.JwtDataEncoder;
import com.azat4dev.demobooking.users.users_commands.domain.core.values.TokenForPasswordReset;
import com.azat4dev.demobooking.users.users_commands.domain.interfaces.functions.ProvideResetPasswordToken;
import lombok.RequiredArgsConstructor;

import java.time.temporal.ChronoUnit;
import java.util.Map;

@RequiredArgsConstructor
public final class ProvideResetPasswordTokenImpl implements ProvideResetPasswordToken {

    public static String TYPE = "password_reset";

    private final long expirationInMs;
    private final JwtDataEncoder jwtDataEncoder;
    private final TimeProvider timeProvider;

    @Override
    public TokenForPasswordReset execute(UserId userId) {

        final var now = timeProvider.currentTime();

        final var token = jwtDataEncoder.encode(
            userId.toString(),
            "self",
            now,
            now.plus(expirationInMs, ChronoUnit.MILLIS),
            Map.of("type", TYPE)
        );

        return TokenForPasswordReset.dangerouslyMakeFrom(token);
    }
}
