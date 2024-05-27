package com.azat4dev.booking.users.users_commands.data.repositories.dto;

import com.azat4dev.booking.shared.data.DomainEventPayloadDTO;
import com.azat4dev.booking.shared.domain.core.UserId;
import com.azat4dev.booking.users.users_commands.domain.core.events.UserVerifiedEmail;
import com.azat4dev.booking.users.users_commands.domain.core.values.email.EmailAddress;

public record UserVerifiedEmailDTO(
    String userId,
    String email
) implements DomainEventPayloadDTO {

    public static UserVerifiedEmailDTO fromDomain(UserVerifiedEmail event) {
        return new UserVerifiedEmailDTO(
            event.userId().value().toString(),
            event.emailAddress().getValue()
        );
    }

    public UserVerifiedEmail toDomain() {
        return new UserVerifiedEmail(
            UserId.dangerouslyMakeFrom(userId),
            EmailAddress.dangerMakeWithoutChecks(email)
        );
    }
}
