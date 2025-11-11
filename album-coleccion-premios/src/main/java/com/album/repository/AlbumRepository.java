package com.album.repository;
import com.album.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AlbumRepository extends JpaRepository<Album, Long> { }