package com.example.album.services;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.album.models.Album;
import com.example.album.repositories.AlbumRepository;

@Service
public class AlbumService {

    private final AlbumRepository albumRepo;

    private final RestTemplate restTemplate;

    private final String externalAlbumAPI = "https://jsonplaceholder.typicode.com";

    private final String internalDashboardAPI = "http://localhost:8081/dashboard";


    public AlbumService(AlbumRepository albumRepo, RestTemplate restTemplate) {
        this.albumRepo = albumRepo;
        this.restTemplate = restTemplate;
    }


    /** Call the external API to retrieve albums */
    public List<Album> getAlbums() {
        ResponseEntity<List<Album>> response = restTemplate
            .exchange(externalAlbumAPI + "/albums", HttpMethod.GET, null, new ParameterizedTypeReference<List<Album>>() {});

        // Save response to database
        List<Album> albums = response.getBody();
        albumRepo.saveAll(albums);

        // Make POST request to Dashboard microservice
        sendAlbumsToDashboard(albums);

        return albums;
    }


    private void sendAlbumsToDashboard(List<Album> albums) {
        ResponseEntity<String> response = restTemplate.postForEntity(internalDashboardAPI + "/save-albums", albums, String.class);

        // Check API status code
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Albums saved to Dashboard");
        }
    }
}
