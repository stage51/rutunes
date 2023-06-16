package com.example.rutunes.services;

import com.example.rutunes.models.Album;
import com.example.rutunes.models.Group;
import com.example.rutunes.models.Image;
import com.example.rutunes.models.User;
import com.example.rutunes.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final SaveAlbumsRepository saveAlbumsRepository;
    private final UserBasketRepository userBasketRepository;
    private final UserRepository userRepository;
    private final GroupService groupService;

    public List<Album> listAlbums(String title) {
        if (title != null) return albumRepository.findByTitle(title);
        return albumRepository.findAll();
    }
    public List<Album> listAlbumsByGroup(String name) {
        Group group = groupService.getGroupByName(name);
        return albumRepository.findByGroup(group);
    }
    public List<Album> listAlbumsByTitleAndGroup(String title, String name) {
        Group group = groupService.getGroupByName(name);
        return albumRepository.findByTitleAndGroup(title, group);
    }

    public void saveAlbum(Principal principal, Album album, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        album.setUser(getUserByPrincipal(principal));
        album.setGroup(getUserByPrincipal(principal).getGroup());
        album.setTitle(album.getGroup().getName());
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            album.addImageToAlbum(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            album.addImageToAlbum(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            album.addImageToAlbum(image3);
        }
        log.info("Saving new Album. Title: {}; Author email: {}", album.getTitle(), album.getUser().getEmail());
        Album albumFromDb = albumRepository.save(album);
        albumFromDb.setPreviewImageId(albumFromDb.getImages().get(0).getId());
        albumRepository.save(album);
    }
    public void updateAlbum(Album album){
        albumRepository.save(album);
    }
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteAlbum(Long id) {
        saveAlbumsRepository.deleteAllByAlbum(getAlbumById(id));
        userBasketRepository.deleteAllByAlbum(getAlbumById(id));
        albumRepository.deleteById(id);
    }

    public Album getAlbumById(Long id) {
        return albumRepository.findById(id).orElse(null);
    }
}
