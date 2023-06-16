package com.example.rutunes.controllers;

import com.example.rutunes.models.*;
import com.example.rutunes.repositories.UserRepository;
import com.example.rutunes.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AlbumController {
    private int condition = 0;
    private final AlbumService albumService;
    private final SongService songService;
    private final SaveAlbumsService saveAlbumsService;
    private final UserBasketService userBasketService;
    private final UserService userService;
    @GetMapping("/")
    public String hello(Model model, Principal principal){
        model.addAttribute("user", albumService.getUserByPrincipal(principal));
        return "hello";
    }
    @GetMapping("/album")
    public String albums(@RequestParam(name = "searchWord", required = false) String title,
                         @RequestParam(name = "searchGroup", required = false) String name,
                         Principal principal, Model model) {
        if (title == null && name == null) {
            model.addAttribute("albums", albumService.listAlbums(null));
        } else if (name == null) {
            model.addAttribute("albums", albumService.listAlbums(title));
        } else if (title == null) {
            model.addAttribute("albums", albumService.listAlbumsByGroup(name));
        } else {
            model.addAttribute("albums", albumService.listAlbumsByTitleAndGroup(title, name));
        }
        model.addAttribute("user", albumService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", title);
        model.addAttribute("searchGroup", name);
        return "albums";
    }

    @GetMapping("/album/{id}")
    public String albumInfo(@PathVariable Long id, Model model, Principal principal) {
        Album album = albumService.getAlbumById(id);
        User user = albumService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("album", album);
        model.addAttribute("images", album.getImages());
        model.addAttribute("authorAlbum", album.getUser());
        model.addAttribute("albums", user.getAlbums());
        model.addAttribute("songs", songService.getSongs());
        model.addAttribute("con", condition);
        condition = 0;
        return "album-info";
    }
    @PreAuthorize("hasAuthority('ROLE_ARTIST')")
    @PostMapping("/album/create")
    public String createAlbum(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3, Album album, Principal principal) throws IOException {
        albumService.saveAlbum(principal, album, file1, file2, file3);
        return "redirect:/my/albums";
    }
    @PreAuthorize("hasAuthority('ROLE_ARTIST')")
    @GetMapping("/album/delete/{id}")
    public String deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return "redirect:/my/albums";
    }
    @PreAuthorize("hasAuthority('ROLE_ARTIST')")
    @GetMapping("/my/albums")
    public String userProducts(Principal principal, Model model) {
        User user = albumService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("albums", user.getAlbums());
        return "my-albums";
    }
    @GetMapping("/album/{id}/buy")
    public String albumSave(@PathVariable Long id, Model model, Principal principal) throws IOException {
        Album album = albumService.getAlbumById(id);
        if (album.getQuantity() <= 0){
            condition = 2;
            return "redirect:/album/{id}";
        }
        album.setQuantity(album.getQuantity() - 1);
        User user = albumService.getUserByPrincipal(principal);
        if (user.getBalance() < album.getPrice()){
            condition = 1;
            return "redirect:/album/{id}";
        }
        user.setBalance(user.getBalance() - album.getPrice());
        userService.updateUser(user);
        SaveAlbums saveAlbums = new SaveAlbums(user, album);
        saveAlbumsService.saveSaveAlbums(principal, saveAlbums);
        albumService.updateAlbum(album);
        model.addAttribute("user", user);
        model.addAttribute("album", album);
        condition = 3;
        return "redirect:/album/{id}";
    }
    @GetMapping("/album/{id}/basket")
    public String albumBasket(@PathVariable Long id, Model model, Principal principal) throws IOException {
        Album album = albumService.getAlbumById(id);
        if (album.getQuantity() <= 0){
            condition = 2;
            return "redirect:/album/{id}";
        }
        User user = albumService.getUserByPrincipal(principal);
        UserBasket userBasket = new UserBasket(user, album);
        userBasketService.saveUserBasket(principal, userBasket);
        model.addAttribute("user", user);
        model.addAttribute("album", album);
        condition = 4;
        return "redirect:/album/{id}";
    }
    @GetMapping("/basket")
    public String displayBasket(Principal principal, Model model) {
        User user = albumService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("userBaskets", userBasketService.getUserBasketByUser(user));
        return "basket";
    }
    @GetMapping("/saved")
    public String displaySaved(Principal principal, Model model) {
        User user = albumService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("savedAlbums", saveAlbumsService.getSaveAlbumsByUser(user));
        return "saved";
    }
    @GetMapping("/basket/delete/{id}")
    public String deleteBasket(@PathVariable Long id, Principal principal) {
        User user = userBasketService.getUserByPrincipal(principal);
        userBasketService.deleteUserBasket(user, id);
        return "redirect:/basket";
    }
    @GetMapping("/saved/delete/{id}")
    public String deleteSaved(@PathVariable Long id, Principal principal) {
        User user = saveAlbumsService.getUserByPrincipal(principal);
        saveAlbumsService.deleteSaveAlbums(user, id);
        return "redirect:/saved";
    }

}
