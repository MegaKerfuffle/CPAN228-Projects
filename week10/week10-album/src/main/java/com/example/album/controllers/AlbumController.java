package com.example.album.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.album.models.Album;
import com.example.album.services.AlbumService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService albumService;
    
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/albums")
    public List<Album> getAlbums() {
        return albumService.getAlbums();
    }
    

}
