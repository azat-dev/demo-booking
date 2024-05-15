package com.azat4dev.demobooking.users.users_commands.application.config.domain;

import com.azat4dev.demobooking.common.domain.annotations.CommandHandlerBean;
import com.azat4dev.demobooking.common.domain.event.DomainEventsBus;
import com.azat4dev.demobooking.common.utils.TimeProvider;
import com.azat4dev.demobooking.users.users_commands.domain.core.values.email.EmailAddress;
import com.azat4dev.demobooking.users.users_commands.domain.handlers.email.verification.utils.BuildEmailVerificationLink;
import com.azat4dev.demobooking.users.users_commands.domain.handlers.email.verification.CompleteEmailVerificationHandler;
import com.azat4dev.demobooking.users.users_commands.domain.handlers.email.verification.SendVerificationEmailHandler;
import com.azat4dev.demobooking.users.users_commands.domain.interfaces.repositories.UsersRepository;
import com.azat4dev.demobooking.users.users_commands.domain.interfaces.services.EmailService;
import com.azat4dev.demobooking.users.users_commands.domain.handlers.email.verification.utils.GetInfoForEmailVerificationToken;
import com.azat4dev.demobooking.users.users_commands.domain.handlers.email.verification.utils.ProvideEmailVerificationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Configuration
public class EmailVerificationConfig {

    @Bean
    BuildEmailVerificationLink buildVerificationLink(
        @Value("${app.verification_email.base_verification_link_url}")
        URL baseVerificationLinkUrl
    ) {
        return token -> {
            return baseVerificationLinkUrl + "?token=" + URLEncoder.encode(token.value(), StandardCharsets.UTF_8);
        };
    }

    @CommandHandlerBean
    SendVerificationEmailHandler sendVerificationEmailCommandHandler(
        BuildEmailVerificationLink buildEmailVerificationLink,
        @Value("${app.verification_email.outgoing.fromAddress}")
        String fromAddress,
        @Value("${app.verification_email.outgoing.fromName}")
        String fromName,
        EmailService emailService,
        ProvideEmailVerificationToken provideEmailVerificationToken,
        DomainEventsBus domainEventsBus
    ) {
        return new SendVerificationEmailHandler(
            buildEmailVerificationLink,
            EmailAddress.checkAndMakeFromString(fromAddress),
            fromName,
            emailService,
            provideEmailVerificationToken,
            domainEventsBus
        );
    }

    @CommandHandlerBean
    CompleteEmailVerificationHandler completeEmailVerificationCommandHandler(
        GetInfoForEmailVerificationToken getTokenInfo,
        DomainEventsBus domainEventsBus,
        UsersRepository usersRepository,
        TimeProvider timeProvider
    ) {
        return new CompleteEmailVerificationHandler(
            getTokenInfo,
            usersRepository,
            domainEventsBus,
            timeProvider
        );
    }
}
