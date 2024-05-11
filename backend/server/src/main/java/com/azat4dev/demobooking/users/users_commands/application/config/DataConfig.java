package com.azat4dev.demobooking.users.users_commands.application.config;

import com.azat4dev.demobooking.common.utils.SystemTimeProvider;
import com.azat4dev.demobooking.common.utils.TimeProvider;
import com.azat4dev.demobooking.users.users_commands.data.repositories.*;
import com.azat4dev.demobooking.users.users_commands.data.repositories.dao.OutboxEventsDao;
import com.azat4dev.demobooking.users.users_commands.data.repositories.dao.OutboxEventsDaoJdbc;
import com.azat4dev.demobooking.users.users_commands.data.repositories.dao.UsersDao;
import com.azat4dev.demobooking.users.users_commands.domain.interfaces.repositories.OutboxEventsRepository;
import com.azat4dev.demobooking.users.users_commands.domain.interfaces.repositories.UnitOfWork;
import com.azat4dev.demobooking.users.users_commands.domain.interfaces.repositories.UnitOfWorkFactory;
import com.azat4dev.demobooking.users.users_commands.domain.interfaces.repositories.UsersRepository;
import com.azat4dev.demobooking.users.users_commands.domain.interfaces.services.EmailService;
import com.azat4dev.demobooking.users.users_commands.domain.services.EmailData;
import com.azat4dev.demobooking.users.users_commands.domain.values.email.EmailAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataConfig {

    @Bean
    UsersRepository usersRepository(
        MapNewUserToData mapNewUserToData,
        MapUserDataToDomain mapUserDataToDomain,
        UsersDao usersDao
    ) {
        return new UsersRepositoryImpl(
            mapNewUserToData,
            mapUserDataToDomain,
            usersDao
        );
    }

    @Bean
    TimeProvider timeProvider() {
        return new SystemTimeProvider();
    }

    @Bean
    EmailService emailService() {
        return new EmailService() {
            @Override
            public void send(EmailAddress email, EmailData data) {
                System.out.println("Email sent to " + email);
            }
        };
    }

    @Bean
    MapNewUserToData mapNewUserToData() {
        return new MapNewUserToDataImpl();
    }

    @Bean
    MapUserDataToDomain mapUserDataToDomain() {
        return new MapUserDataToDomainImpl();
    }

    @Bean
    DomainEventSerializer domainEventSerializer() {
        return new DomainEventSerializerImpl();
    }

    @Bean
    OutboxEventsDao outboxEventsDao(NamedParameterJdbcTemplate jdbcTemplate) {
        return new OutboxEventsDaoJdbc(jdbcTemplate);
    }

    @Bean
    OutboxEventsRepository outboxEventsRepository(
        TimeProvider timeProvider,
        DomainEventSerializer domainEventSerializer,
        OutboxEventsDao outboxEventsDao
    ) {
        return new OutboxEventsRepositoryImpl(
            timeProvider,
            domainEventSerializer,
            outboxEventsDao
        );
    }

    @Bean
    UnitOfWorkFactory unitOfWorkFactory(
        PlatformTransactionManager transactionManager,
        OutboxEventsRepository outboxEventsRepository,
        UsersRepository usersRepository
    ) {

        return new UnitOfWorkFactory() {
            @Override
            public UnitOfWork make() {
                return new UnitOfWorkImpl(
                    transactionManager,
                    outboxEventsRepository,
                    usersRepository
                );
            }
        };
    }
}
