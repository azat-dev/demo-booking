package com.azat4dev.demobooking.users.presentation.security.services;

import com.azat4dev.demobooking.users.presentation.security.services.jwt.JWTService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;


public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final Log logger = LogFactory.getLog(getClass());

    private final JWTService tokenProvider;

    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationProvider(
        JWTService tokenProvider,
        CustomUserDetailsService customUserDetailsService
    ) {
        this.tokenProvider = tokenProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public Authentication authenticate(Authentication authenticationRequest) throws InvalidJwtTokenException {
        final var request = (JWTAuthenticationToken) authenticationRequest;
        final var token = request.getToken();

        if (token == null) {
            this.logger.debug("No token provided");
            return null;
        }

        final var isValid = tokenProvider.verifyToken(token);

        if (!isValid) {
            this.logger.debug("Token is not valid");
            throw new InvalidJwtTokenException(null);
        }

        final var userId = tokenProvider.getUserIdFromToken(token);

        final var user = customUserDetailsService.loadUserById(userId);

        this.logger.debug("Authenticated token");
        return JWTAuthenticationToken.authenticated(
            user,
            token
        );
    }
}
