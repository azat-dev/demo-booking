package com.azat4dev.booking.users.commands.domain.handlers.password.reset.utils;

import com.azat4dev.booking.shared.domain.values.user.UserId;
import com.azat4dev.booking.users.common.infrastructure.presentation.security.services.jwt.JwtDataDecoder;
import com.azat4dev.booking.users.commands.domain.core.values.password.reset.TokenForPasswordReset;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetInfoForPasswordResetTokenImpl implements GetInfoForPasswordResetToken {

    private final JwtDataDecoder decoder;

    @Override
    public Data execute(TokenForPasswordReset token) throws Exception.InvalidToken {
        final var decodedData = decoder.decode(token.getValue());

        try {
            return new Data(
                UserId.checkAndMakeFrom(decodedData.subject()),
                decodedData.expiresAt()
            );
        } catch (UserId.WrongFormatException e) {
            throw new Exception.InvalidToken();
        }
    }
}