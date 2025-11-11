package com.album.repository;
import com.album.model.Reward;
import com.album.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface RewardRepository extends JpaRepository<Reward, Long> {
    Optional<Reward> findByAlbum(Album album);
}