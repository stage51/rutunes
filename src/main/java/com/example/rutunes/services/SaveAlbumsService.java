package com.example.rutunes.services;

import com.example.rutunes.models.Album;
import com.example.rutunes.models.SaveAlbums;
import com.example.rutunes.models.User;
import com.example.rutunes.repositories.SaveAlbumsRepository;
import com.example.rutunes.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveAlbumsService {
    private final SaveAlbumsRepository saveAlbumsRepository;
    private final UserRepository userRepository;

    public List<SaveAlbums> listSaveAlbums(String title) {
        if (title != null) return saveAlbumsRepository.findByTitle(title);
        return saveAlbumsRepository.findAll();
    }

    public void saveSaveAlbums(Principal principal, SaveAlbums saveAlbums) throws IOException {
        saveAlbums.setUser(getUserByPrincipal(principal));
        log.info("Saving new SaveAlbums. Title: {}; Author email: {}", saveAlbums.getAlbum().getGroup().getName(),saveAlbums.getUser().getEmail());
        saveAlbumsRepository.save(saveAlbums);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void deleteSaveAlbums(User user, Long id) {
        SaveAlbums saveAlbums = saveAlbumsRepository.findById(id)
                .orElse(null);
        if (saveAlbums != null) {
            if (saveAlbums.getUser().getId().equals(user.getId())) {
                saveAlbumsRepository.delete(saveAlbums);
                log.info("SaveAlbums with id = {} was deleted", id);
            } else {
                log.error("User: {} haven't this SaveAlbums with id = {}", user.getEmail(), id);
            }
        } else {
            log.error("SaveAlbums with id = {} is not found", id);
        }    }
    public SaveAlbums getSaveAlbumsById(Long id) {
        return saveAlbumsRepository.findById(id).orElse(null);
    }
    public List<SaveAlbums> getSaveAlbums() {
        return saveAlbumsRepository.findAll();
    }
    public List<SaveAlbums> getSaveAlbumsByUser(User user) {
        return saveAlbumsRepository.findByUser(user);
    }
}
