package com.azat4dev.booking.users.users_queries.data.repositories;

import com.azat4dev.booking.users.users_queries.data.dao.records.UserRecord;
import com.azat4dev.booking.users.users_queries.domain.entities.UserPhoto;
import com.azat4dev.booking.users.users_queries.domain.interfaces.repositories.MapToUserPhoto;
import com.azat4dev.booking.users.users_queries.domain.values.BaseUrl;
import lombok.RequiredArgsConstructor;

import java.net.MalformedURLException;

@RequiredArgsConstructor
public final class MapToUserPhotoImpl implements MapToUserPhoto {

    private final BaseUrl baseUrl;

    @Override
    public UserPhoto map(UserRecord.PhotoPath photoPath) {
        try {
            return new UserPhoto(
                baseUrl.urlWithPath(photoPath.bucketName() + "/" + photoPath.objectName())
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}