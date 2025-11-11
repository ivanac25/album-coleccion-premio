package com.album.service.events;

public interface AlbumObserver {
    void onAlbumCompleted(Long userId, Long albumId);
}
