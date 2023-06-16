package com.example.rutunes.controllers;

import com.example.rutunes.models.Album;
import com.example.rutunes.models.Song;
import com.example.rutunes.models.User;
import com.example.rutunes.services.AlbumService;
import com.example.rutunes.services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class SongController {
    private final AlbumService albumService;
    private final SongService songService;
    @PreAuthorize("hasAuthority('ROLE_ARTIST')")
    @PostMapping("/album/{id}/add")
    public String albumInfo(@PathVariable Long id, Principal principal, Song song) throws IOException {
        song.setAlbumId(id);
        songService.saveSong(principal, song);
        return "redirect:/album/{id}";
    }
}
