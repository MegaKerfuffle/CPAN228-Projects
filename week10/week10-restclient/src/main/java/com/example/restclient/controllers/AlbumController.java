package com.example.restclient.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restclient.models.Album;
import com.example.restclient.services.AlbumService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/album/api/v1")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/albums")
    public List<Album> getAlbums() {
        return albumService.getAlbums();
    }
    

    @GetMapping("/albums/{id}")
    public Album getAlbumsById(@PathVariable Integer id) {
        return albumService.getAlbumById(id);
    }
    

    @PostMapping("/albums")
    public Album postAlbum(@RequestBody Album album) {
        return albumService.addAlbum(album);
    }
    

    @DeleteMapping("/albums/{id}")
    public void deleteAlbum(@PathVariable Integer id) {
        albumService.deleteAlbum(id);
    }

}
