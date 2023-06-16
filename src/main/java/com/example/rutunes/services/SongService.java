package com.example.rutunes.services;

import com.example.rutunes.models.Album;
import com.example.rutunes.models.Image;
import com.example.rutunes.models.Song;
import com.example.rutunes.models.User;
import com.example.rutunes.repositories.AlbumRepository;
import com.example.rutunes.repositories.SongRepository;
import com.example.rutunes.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final UserRepository userRepository;

    public List<Song> listSongs(String title) {
        if (title != null) return songRepository.findByTitle(title);
        return songRepository.findAll();
    }

    public void saveSong(Principal principal, Song song) throws IOException {
        song.setUser(getUserByPrincipal(principal));
        log.info("Saving new Song. Title: {}; Author email: {}", song.getTitle(),song.getUser().getEmail());
        songRepository.save(song);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void deleteSong(User user, Long id) {
        Song song = songRepository.findById(id)
                .orElse(null);
        if (song != null) {
            if (song.getUser().getId().equals(user.getId())) {
                songRepository.delete(song);
                log.info("Song with id = {} was deleted", id);
            } else {
                log.error("User: {} haven't this song with id = {}", user.getEmail(), id);
            }
        } else {
            log.error("Song with id = {} is not found", id);
        }    }

    public Song getSongById(Long id) {
        return songRepository.findById(id).orElse(null);
    }
    public List<Song> getSongs() {
        return songRepository.findAll();
    }
}
