package com.example.restclient.services;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.restclient.models.Album;

@Service
public class AlbumService {

    private final RestClient restClient;

    
    public AlbumService() {
        // No need for dependency injection - just build the client
        // with a base URL (the API we're hitting)
        this.restClient = RestClient.builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .build();
    }


    /**
     * Retrieves all albums from the API
     */
    public List<Album> getAlbums() {
        return restClient
            .get()
            .uri("/albums")
            .retrieve()
            // `ParameterizedTypeReference<>` tells the RestClient what type
            // we want our data to be deserialized into. In this case, we want
            // a `List<Album>`. 
            .body(new ParameterizedTypeReference<List<Album>>() {});   
    }

    /**
     * Retrieves a single album from the API, by its ID
     */
    public Album getAlbumById(Integer id) {
        return restClient
            .get()
            .uri("/albums/" + id)
            .retrieve()
            // `ParameterizedTypeReference<>` isn't needed for non-generic types
            .body(Album.class);
    }

    /**
     * Posts a single album to the API, and returns an updated version
     * of the album with its new ID. 
     */
    public Album addAlbum(Album album) {
        return restClient
            .post()
            .uri("/albums")
            .body(album)
            .retrieve()
            .body(Album.class);
    } 

    /**
     * Update a single album in the API by ID. Returns the updated
     * album with its new ID.
     */
    public Album updateAlbum(Integer id, Album album) {
        return restClient
            .put()
            .uri("/albums/" + id)
            .body(album)
            .retrieve()
            .body(Album.class);
    }

    /**
     * Deletes a single album from the API, by ID.
     */
    public void deleteAlbum(Integer id) {
        restClient
            .delete()
            .uri("/albums/" + id)
            .retrieve()
            .toBodilessEntity();
    }
}