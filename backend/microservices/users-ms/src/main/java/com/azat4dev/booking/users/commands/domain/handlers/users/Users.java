package com.azat4dev.booking.users.commands.domain.handlers.users;

import com.azat4dev.booking.shared.domain.DomainException;
import com.azat4dev.booking.shared.domain.values.user.UserId;
import com.azat4dev.booking.users.commands.domain.core.commands.NewUserData;
import com.azat4dev.booking.users.commands.domain.core.entities.User;
import com.azat4dev.booking.users.commands.domain.core.entities.UserPhotoPath;
import com.azat4dev.booking.users.commands.domain.core.values.email.EmailAddress;

import java.util.Optional;

public interface Users {

    void createNew(NewUserData newUserData) throws Exception.UserWithSameEmailAlreadyExists, User.Exception;

    void addVerifiedEmail(UserId userId, EmailAddress email) throws Exception.UserNotFound, Exception.EmailNotFound;

    Optional<User> findByEmail(EmailAddress email);

    void updatePhoto(UserId userId, UserPhotoPath newPhotoPath) throws Exception.FailedToUpdateUser, Exception.UserNotFound;

    // Exceptions

    abstract class Exception extends DomainException {
        public Exception(String message) {
            super(message);
        }

        public static final class UserWithSameEmailAlreadyExists extends Exception {
            public UserWithSameEmailAlreadyExists() {
                super("User with same email already exists");
            }
        }

        public static final class UserNotFound extends Exception {
            public UserNotFound() {
                super("User not found");
            }
        }

        public static final class EmailNotFound extends Exception {
            public EmailNotFound() {
                super("Email not found");
            }
        }

        public static final class FailedToUpdateUser extends Exception {
            public FailedToUpdateUser() {
                super("Failed to update user");
            }
        }
    }
}