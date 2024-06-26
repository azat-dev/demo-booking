package com.azat4dev.booking.users.commands.application.handlers;

import com.azat4dev.booking.shared.application.ValidationException;
import com.azat4dev.booking.shared.domain.DomainException;
import com.azat4dev.booking.shared.domain.values.user.UserId;
import com.azat4dev.booking.users.commands.application.commands.SignUp;

public interface SignUpHandler {

    UserId handle(SignUp command) throws ValidationException, Exception.UserWithSameEmailAlreadyExists;

    // Exception

    abstract class Exception extends DomainException {
        public Exception(String message) {
            super(message);
        }

        public static class UserWithSameEmailAlreadyExists extends Exception {
            public UserWithSameEmailAlreadyExists() {
                super("User with same email already exists");
            }
        }
    }
}
