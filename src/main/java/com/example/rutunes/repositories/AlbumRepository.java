package com.example.rutunes.repositories;

import com.example.rutunes.models.Album;
import com.example.rutunes.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByTitle(String title);
    @Transactional
    List<Album> findByGroup(Group group);
    List<Album> findByTitleAndGroup(String title, Group group);
}
