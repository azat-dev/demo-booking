package com.azat4dev.booking.users.users_commands.domain.core.events;

import com.azat4dev.booking.shared.domain.event.DomainEventPayload;
import com.azat4dev.booking.shared.domain.core.UserId;
import com.azat4dev.booking.users.users_commands.domain.core.entities.UserPhotoPath;
import com.azat4dev.booking.users.users_commands.domain.core.values.IdempotentOperationId;

import java.util.Optional;

public record UpdatedUserPhoto(
    IdempotentOperationId idempotentOperationId,
    UserId userId,
    UserPhotoPath newPhotoPath,
    Optional<UserPhotoPath> prevPhotoPath
) implements DomainEventPayload {
}