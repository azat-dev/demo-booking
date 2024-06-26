package com.azat4dev.booking.shared.domain.values.user;

import com.azat4dev.booking.shared.domain.DomainException;
import com.azat4dev.booking.shared.utils.Assert;

import java.util.UUID;

public record UserId(UUID value) {

    public static UserId checkAndMakeFrom(String id) throws WrongFormatException {

        Assert.notNull(id, () -> new WrongFormatException(id));

        try {
            return new UserId(UUID.fromString(id));
        } catch (IllegalArgumentException e) {
            throw new WrongFormatException(id);
        }
    }

    public static UserId fromUUID(UUID id) throws WrongFormatException {
        Assert.notNull(id, () -> new WrongFormatException("null"));
        return new UserId(id);
    }

    public static UserId dangerouslyMakeFrom(String id) {
        return new UserId(UUID.fromString(id));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public static class WrongFormatException extends DomainException {
        public WrongFormatException(String value) {
            super("Wrong formatted user id. Value: " + value);
        }

        @Override
        public String getCode() {
            return "WrongFormat";
        }
    }
}
