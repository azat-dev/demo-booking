package com.azat4dev.booking.users.commands.application.handlers.photo;

import com.azat4dev.booking.shared.domain.DomainException;
import com.azat4dev.booking.users.commands.application.commands.photo.GenerateUserPhotoUploadUrl;
import com.azat4dev.booking.users.commands.domain.core.events.GeneratedUserPhotoUploadUrl;

public interface GenerateUserPhotoUploadUrlHandler {

    GeneratedUserPhotoUploadUrl handle(GenerateUserPhotoUploadUrl command) throws Exception.FailedGenerateUserPhotoUploadUrl;

    abstract class Exception extends DomainException {
        protected Exception(String message) {
            super(message);
        }

        public static final class FailedGenerateUserPhotoUploadUrl extends Exception {
            public FailedGenerateUserPhotoUploadUrl() {
                super("Failed to generate presigned URL for uploading user photo");
            }
        }
    }
}
