package com.example.rutunes.repositories;

import com.example.rutunes.models.Album;
import com.example.rutunes.models.SaveAlbums;
import com.example.rutunes.models.User;
import com.example.rutunes.models.UserBasket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserBasketRepository extends JpaRepository<UserBasket, Long> {
    List<UserBasket> findByTitle(String title);
    List<UserBasket> findByUser(User user);
    @Transactional
    void deleteAllByAlbum(Album album);
}

