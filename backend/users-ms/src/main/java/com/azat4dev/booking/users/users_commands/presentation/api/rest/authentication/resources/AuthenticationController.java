package com.azat4dev.booking.users.users_commands.presentation.api.rest.authentication.resources;

import com.azat4dev.booking.shared.domain.core.UserIdFactory;
import com.azat4dev.booking.common.presentation.ErrorDTO;
import com.azat4dev.booking.shared.domain.core.UserId;
import com.azat4dev.booking.users.common.presentation.security.services.CustomUserDetailsService;
import com.azat4dev.booking.users.common.presentation.security.services.jwt.JwtService;
import com.azat4dev.booking.users.common.presentation.security.services.jwt.UserIdNotFoundException;
import com.azat4dev.booking.users.users_commands.domain.core.commands.CreateUser;
import com.azat4dev.booking.users.users_commands.domain.core.values.password.EncodedPassword;
import com.azat4dev.booking.users.users_commands.domain.handlers.users.UsersService;
import com.azat4dev.booking.users.users_commands.domain.interfaces.services.PasswordService;
import com.azat4dev.booking.users.users_commands.presentation.api.rest.authentication.entities.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.log.LogMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;

@Component
public class AuthenticationController implements AuthenticationResource {

    protected final Log logger = LogFactory.getLog(this.getClass());

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
        .getContextHolderStrategy();

    @Autowired
    private UserIdFactory userIdFactory;

    @Autowired
    private JwtService tokenProvider;

    @Autowired
    private SecurityContextRepository securityContextRepository;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @ExceptionHandler(UsersService.Exception.UserWithSameEmailAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDTO handleUserWithSameEmailAlreadyExists(UsersService.Exception.UserWithSameEmailAlreadyExists e) {
        return new ErrorDTO(e.getCode(), e.getMessage());
    }

    @Override
    public ResponseEntity<SignUpResponse> signUp(
        SignUpRequest signUpRequest,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {

        final var email = signUpRequest.parseEmail();
        final var password = signUpRequest.parsePassword();
        final var fullName = signUpRequest.parseFullName();

        final var encodedPassword = passwordService.encodePassword(password);
        final var userId = userIdFactory.generateNewUserId();

        usersService.handle(
            new CreateUser(
                userId,
                fullName,
                email,
                encodedPassword
            )
        );

        final var authorities = new String[]{"ROLE_USER"};
        final var authenticationResult = this.authenticateUser(userId, authorities, request, response);

        final var signUpResponse = new SignUpResponse(
            userId.toString(),
            authenticationResult
        );

        return ResponseEntity.created(URI.create("")).body(signUpResponse);
    }

    private GeneratedTokensDTO authenticateUser(
        UserId userId,
        String[] authorities,
        HttpServletRequest request,
        HttpServletResponse response
    ) {

        try {
            final var authenticationResponse = new GeneratedTokensDTO(
                tokenProvider.generateAccessToken(userId, authorities),
                tokenProvider.generateRefreshToken(userId, authorities)
            );

            final var token = new BearerTokenAuthenticationToken(authenticationResponse.access());

            final var context = securityContextHolderStrategy.createEmptyContext();
            context.setAuthentication(token);

            this.securityContextRepository.saveContext(
                context,
                request,
                response
            );

            if (this.logger.isDebugEnabled()) {
                this.logger.debug(LogMessage.format("Set SecurityContextHolder to %s", token));
            }

            return authenticationResponse;

        } catch (Exception failed) {
            this.securityContextHolderStrategy.clearContext();
            this.logger.trace("Failed to process authentication request", failed);

            throw failed;
        }
    }

    public ResponseEntity<LoginByEmailResponse> authenticate(
        @RequestBody LoginByEmailRequest authenticationRequest,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {

        final var email = authenticationRequest.parseEmail();
        final var password = authenticationRequest.parsePassword();

        try {
            final var user = userDetailsService.loadUserByEmail(email);

            final var encodedPassword = new EncodedPassword(user.getPassword());

            if (!passwordService.matches(password, encodedPassword)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            final var authenticationResponse = authenticateUser(
                user.id(),
                new String[]{"USER_ROLE"},
                request,
                response
            );

            return ResponseEntity.ok(new LoginByEmailResponse(authenticationResponse));

        } catch (UserIdNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            throw e;
        }
    }
}