package com.azat4dev.demobooking.users.users_commands.presentation.api.rest.authentication.entities;

public record UploadedFileDataDTO(
    String url,
    String bucketName,
    String objectName
) {
}