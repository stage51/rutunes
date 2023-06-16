package com.example.rutunes.repositories;

import com.example.rutunes.models.Album;
import com.example.rutunes.models.SaveAlbums;
import com.example.rutunes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SaveAlbumsRepository extends JpaRepository<SaveAlbums, Long> {
    List<SaveAlbums> findByTitle(String title);

    List<SaveAlbums> findByUser(User user);
    @Transactional
    void deleteAllByAlbum(Album album);
}