package com.example.dashboard.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dashboard.models.Album;
import com.example.dashboard.services.AlbumService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/dashboard")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }


    @PostMapping("/save-album")
    public ResponseEntity<String> postSaveAlbum(@RequestBody List<Album> albums) {
        albumService.saveAlbums(albums);
        return ResponseEntity.ok("yup!");
    }
}