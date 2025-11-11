package com.album.repository;

import com.album.model.Sticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StickerRepository extends JpaRepository<Sticker, Long> {
    int countByAlbumId(Long albumId);

    @Query("SELECT s.id FROM Sticker s WHERE s.album.id = :albumId")
    List<Long> findIdsByAlbumId(Long albumId);
}
