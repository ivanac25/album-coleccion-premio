package com.album.repository;
import com.album.model.UserReward;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRewardRepository extends JpaRepository<UserReward, Long> {
    Optional<UserReward> findByUsuarioIdAndAlbumId(Long userId, Long albumId);
}