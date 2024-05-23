package com.azat4dev.booking.users.users_commands.api;


import com.azat4dev.booking.shared.domain.event.EventIdGenerator;
import com.azat4dev.booking.shared.domain.event.RandomEventIdGenerator;
import com.azat4dev.booking.shared.utils.SystemTimeProvider;
import com.azat4dev.booking.shared.utils.TimeProvider;
import com.azat4dev.booking.users.common.presentation.security.services.jwt.JwtService;
import com.azat4dev.booking.users.users_commands.application.config.presentation.WebSecurityConfig;
import com.azat4dev.booking.users.users_commands.domain.UserHelpers;
import com.azat4dev.booking.users.users_commands.domain.core.commands.CompletePasswordReset;
import com.azat4dev.booking.users.users_commands.domain.core.values.IdempotentOperationId;
import com.azat4dev.booking.users.users_commands.domain.core.values.password.EncodedPassword;
import com.azat4dev.booking.users.users_commands.domain.core.values.password.Password;
import com.azat4dev.booking.users.users_commands.domain.core.values.password.reset.TokenForPasswordReset;
import com.azat4dev.booking.users.users_commands.domain.handlers.password.reset.CompletePasswordResetHandler;
import com.azat4dev.booking.users.users_commands.domain.handlers.password.reset.ResetPasswordByEmailHandler;
import com.azat4dev.booking.users.users_commands.domain.interfaces.services.PasswordService;
import com.azat4dev.booking.users.users_commands.presentation.api.rest.authentication.entities.CompleteResetPasswordRequest;
import com.azat4dev.booking.users.users_commands.presentation.api.rest.authentication.entities.ResetPasswordByEmailRequest;
import com.azat4dev.booking.users.users_commands.presentation.api.rest.authentication.resources.ResetPasswordController;
import com.azat4dev.booking.users.users_queries.domain.services.UsersQueryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResetPasswordController.class)
@Import(WebSecurityConfig.class)
public class ResetPasswordControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsersQueryService usersService;

    @MockBean
    private JwtService tokenProvider;

    @MockBean
    private JwtEncoder jwtEncoder;

    @MockBean
    @Qualifier("accessTokenDecoder")
    private JwtDecoder accessTokenDecoder;

    @MockBean
    private ResetPasswordByEmailHandler resetPasswordByEmailHandler;

    @MockBean
    private PasswordService passwordService;

    @MockBean
    private CompletePasswordResetHandler completePasswordResetHandler;

    IdempotentOperationId anyIdempotentOperationId() throws IdempotentOperationId.Exception {
        return IdempotentOperationId.checkAndMakeFrom(UUID.randomUUID().toString());
    }

    @Test
    public void test_resetPasswordByEmail_givenEmail_thenPassToHandlerReturnOk() throws Exception {

        // Given
        final var request = new ResetPasswordByEmailRequest(
            anyIdempotentOperationId().toString(),
            UserHelpers.anyValidEmail().getValue()
        );

        willDoNothing().given(resetPasswordByEmailHandler)
            .handle(any(), any(), any());

        // When
        final var result = performResetPasswordByEmail(request);

        // Then
        result.andExpect(status().isOk());

        final var expectedCommand = request.toCommand();

        then(resetPasswordByEmailHandler).should(times(1))
            .handle(
                eq(expectedCommand),
                any(),
                any()
            );
    }

    @Test
    public void test_resetPasswordByEmail_givenInvalid_thenReturnBadRequest() throws Exception {

        // Given
        final var request = new ResetPasswordByEmailRequest(
            anyIdempotentOperationId().toString(),
            "notValidEmail"
        );

        // When
        final var result = performResetPasswordByEmail(request);

        // Then
        result.andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].code").value("WrongFormat"));
    }

    @Test
    public void test_completeResetPassword_givenValidToken_thenPassToHandlerReturnOk() throws Exception {

        // Given
        final var idempotencyKey = anyIdempotentOperationId();
        final var token = "tokenValue";

        final var newPassword = "newPassword";
        final var encodedPassword = new EncodedPassword("encodedPassword");

        final var request = new CompleteResetPasswordRequest(
            idempotencyKey.toString(),
            newPassword,
            token
        );

        given(passwordService.encodePassword(any()))
            .willReturn(encodedPassword);

        willDoNothing().given(resetPasswordByEmailHandler)
            .handle(any(), any(), any());

        // When
        final var result = performCompleteResetPassword(request);

        // Then
        result.andExpect(status().isOk());

        final var expectedCommand = new CompletePasswordReset(
            idempotencyKey,
            encodedPassword,
            TokenForPasswordReset.dangerouslyMakeFrom(token)
        );

        then(passwordService).should(times(1))
            .encodePassword(Password.checkAndMakeFromString(newPassword));

        then(completePasswordResetHandler).should(times(1))
            .handle(
                eq(expectedCommand),
                any(),
                any()
            );
    }

    private ResultActions performCompleteResetPassword(CompleteResetPasswordRequest request) throws Exception {
        final String url = "/api/public/set-new-password";
        return performPostRequest(
            url,
            request
        );
    }

    private ResultActions performResetPasswordByEmail(ResetPasswordByEmailRequest request) throws Exception {
        final String url = "/api/public/reset-password";
        return performPostRequest(
            url,
            request
        );
    }

    private ResultActions performGetRequest(
        String url,
        Map<String, String> params
    ) throws Exception {

        var request = get(url);

        for (var entry : params.entrySet()) {
            request = request.param(entry.getKey(), entry.getValue());
        }

        return mockMvc.perform(request);
    }

    private ResultActions performPostRequest(
        String url,
        Object request
    ) throws Exception {

        final var payload = objectMapper.writeValueAsString(request);

        return mockMvc.perform(
            post(url).contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        );
    }

    @TestConfiguration
    static class Config {
        @Bean
        EventIdGenerator eventIdGenerator() {
            return new RandomEventIdGenerator();
        }

        @Bean
        TimeProvider timeProvider() {
            return new SystemTimeProvider();
        }
    }
}