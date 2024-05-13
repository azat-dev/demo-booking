package com.azat4dev.demobooking.users.users_commands.application.config.domain;

import com.azat4dev.demobooking.common.domain.AutoConnectCommandHandlersToBus;
import com.azat4dev.demobooking.common.domain.event.DomainEventsBus;
import com.azat4dev.demobooking.common.domain.event.DomainEventsFactory;
import com.azat4dev.demobooking.users.users_commands.domain.core.values.email.EmailAddress;
import com.azat4dev.demobooking.users.users_commands.domain.handlers.SendVerificationEmailHandler;
import com.azat4dev.demobooking.users.users_commands.domain.interfaces.services.EmailService;
import com.azat4dev.demobooking.users.users_commands.domain.interfaces.services.EmailVerificationTokensService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@AutoConnectCommandHandlersToBus
@Configuration
public class CommandHandlersConfig {

    @Bean
    SendVerificationEmailHandler sendVerificationEmailPolicy(
        @Value
            ("${app.verification_email.base_verification_link_url}")
        URL baseVerificationLinkUrl,
        @Value("${app.verification_email.outgoing.fromAddress}")
        String fromAddress,
        @Value("${app.verification_email.outgoing.fromName}")
        String fromName,
        EmailService emailService,
        EmailVerificationTokensService emailVerificationTokensService,
        DomainEventsBus domainEventsBus,
        DomainEventsFactory domainEventsFactory
    ) {
        return new SendVerificationEmailHandler(
            baseVerificationLinkUrl,
            EmailAddress.checkAndMakeFromString(fromAddress),
            fromName,
            emailService,
            emailVerificationTokensService,
            domainEventsBus,
            domainEventsFactory
        );
    }
}