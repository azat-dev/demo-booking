package com.azat4dev.demobooking.users.data.repositories;

import com.azat4dev.demobooking.users.data.entities.UserData;
import com.azat4dev.demobooking.users.data.repositories.jpa.JpaUsersRepository;
import com.azat4dev.demobooking.users.domain.UserHelpers;
import com.azat4dev.demobooking.users.domain.interfaces.repositories.NewUserData;
import com.azat4dev.demobooking.users.domain.interfaces.repositories.UsersRepository;
import com.azat4dev.demobooking.users.domain.services.EmailVerificationStatus;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

public class UsersRepositoryImplTests {

    SUT createSUT() {

        final var mapNewUserData = mock(MapNewUserToData.class);
        final var jpaUsersRepository = mock(JpaUsersRepository.class);

        return new SUT(
            new UsersRepositoryImpl(
                mapNewUserData,
                jpaUsersRepository
            ),
            jpaUsersRepository,
            mapNewUserData
        );
    }

    NewUserData anyNewUserData() {
        return new NewUserData(
            UserHelpers.anyValidUserId(),
            new Date(),
            UserHelpers.anyValidEmail(),
            UserHelpers.anyEncodedPassword(),
            EmailVerificationStatus.NOT_VERIFIED
        );
    }

    @Test
    public void test_createUser_givenNotExistingUser_thenThrowException() throws Exception {

        // Given
        final var sut = createSUT();
        final var newUserData = anyNewUserData();

        final var persistentUserData = new UserData();

        given(sut.mapNewUserToData.map(any()))
            .willReturn(persistentUserData);

        given(sut.jpaUsersRepository.saveAndFlush(persistentUserData))
            .willReturn(persistentUserData);

        // When
        sut.repository.createUser(newUserData);

        // Then
        then(sut.mapNewUserToData()).should(times(1))
            .map(newUserData);

        then(sut.jpaUsersRepository).should(times(1))
            .saveAndFlush(persistentUserData);
    }

    @Test
    public void test_createUser_givenExistingUserWithSameIdAndEmail_thenThrowException() throws Exception {

        // Given
        final var sut = createSUT();
        final var newUserData = anyNewUserData();
        final var persistentUserData = new UserData();
        persistentUserData.setId(newUserData.userId().value());

        given(sut.mapNewUserToData.map(any()))
            .willReturn(persistentUserData);

        given(sut.jpaUsersRepository.saveAndFlush(persistentUserData))
            .willThrow(new DataIntegrityViolationException("User exists"));

        given(sut.jpaUsersRepository.findByEmail(any()))
            .willReturn(Optional.of(persistentUserData));

        // When
        assertThrows(UsersRepository.UserWithSameEmailAndIdAlreadyExistsException.class,
            () -> sut.repository.createUser(newUserData));

        // Then
        then(sut.jpaUsersRepository).should(times(1))
            .findByEmail(newUserData.email().getValue());
    }

    record SUT(
        UsersRepository repository,
        JpaUsersRepository jpaUsersRepository,
        MapNewUserToData mapNewUserToData
    ) {
    }
}
