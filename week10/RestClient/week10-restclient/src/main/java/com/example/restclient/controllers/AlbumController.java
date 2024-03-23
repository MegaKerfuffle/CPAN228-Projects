package com.example.restclient.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restclient.models.Album;
import com.example.restclient.services.AlbumService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/album/api/v1")
public class AlbumController {

    private final AlbumService albumService;


    /** Injects dependency: `AlbumService` */
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }


    /** Fetches and returns all albums from the external API */
    @GetMapping("/albums")
    public List<Album> getAlbums() {
        return albumService.getAlbums();
    }
    
    /** Fetches and returns a single album from the external API */
    @GetMapping("/albums/{id}")
    public Album getAlbumsById(@PathVariable Integer id) {
        return albumService.getAlbumById(id);
    }
    
    /** Adds a single album to the external API */
    @PostMapping("/albums")
    public Album postAddAlbum(@RequestBody Album album) {
        return albumService.addAlbum(album);
    }
    
    /** Updates a single album in the external API */
    @PutMapping("/albums/{id}")
    public Album putUpdateAlbum(@PathVariable Integer id, @RequestBody Album album) {        
        return albumService.updateAlbum(id, album);
    }

    /** Deletes a single album from the external API */
    @DeleteMapping("/albums/{id}")
    public ResponseEntity<String> deleteAlbum(@PathVariable Integer id) {
        try {
            albumService.deleteAlbum(id);
            return ResponseEntity.ok("Deleted album successfully!");
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().body("that didnt work");
        }
    }

}
