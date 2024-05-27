package com.azat4dev.booking.users.users_commands.data.repositories.dto;

import com.azat4dev.booking.shared.data.DomainEventPayloadDTO;
import com.azat4dev.booking.shared.domain.core.UserId;
import com.azat4dev.booking.users.users_commands.domain.core.commands.UpdateUserPhoto;
import com.azat4dev.booking.users.users_commands.domain.core.values.IdempotentOperationId;

public record UpdateUserPhotoDTO(
    String idempotentOperationId,
    String userId,
    UploadedFileDataDTO uploadedFileData
) implements DomainEventPayloadDTO {

    public static UpdateUserPhotoDTO fromDomain(UpdateUserPhoto dm) {
        return new UpdateUserPhotoDTO(
            dm.idempotentOperationId().toString(),
            dm.userId().toString(),
            UploadedFileDataDTO.fromDomain(dm.uploadedFileData())
        );
    }

    public UpdateUserPhoto toDomain() {
        return new UpdateUserPhoto(
            IdempotentOperationId.dangerouslyMakeFrom(idempotentOperationId),
            UserId.dangerouslyMakeFrom(userId),
            uploadedFileData.toDomain()
        );
    }
}
