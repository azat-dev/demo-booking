package com.azat4dev.demobooking.users.users_commands.data.repositories.dao;

import com.azat4dev.demobooking.users.users_commands.data.entities.UserData;

import java.util.Optional;
import java.util.UUID;

public interface UsersDao {

    void addNew(UserData userData) throws Exception.UserAlreadyExists;

    void update(UserData userData) throws Exception.UserNotFound;

    Optional<UserData> findByEmail(String email);

    Optional<UserData> findById(UUID userId);

    // Exceptions

    abstract class Exception extends RuntimeException {

        public String getCode() {
            return getClass().getSimpleName();
        }

        public final static class UserAlreadyExists extends Exception {
        }

        public final static class UserNotFound extends Exception {
        }

        public final static class WrongJsonFormat extends Exception {
        }
    }
}
