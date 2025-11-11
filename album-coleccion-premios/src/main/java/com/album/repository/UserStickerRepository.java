package com.album.repository;

import com.album.model.UserSticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserStickerRepository extends JpaRepository<UserSticker, Long> {

    int countDistinctByUsuarioIdAndStickerAlbumId(Long usuarioId, Long albumId);

    boolean existsByUsuarioIdAndStickerId(Long usuarioId, Long stickerId);

    @Query("""
        SELECT us.sticker.id
        FROM UserSticker us
        WHERE us.usuario.id = :usuarioId AND us.sticker.album.id = :albumId
        GROUP BY us.sticker.id
        HAVING COUNT(us.sticker.id) > 1
    """)
    List<Long> findDuplicadas(Long usuarioId, Long albumId);
}
