package com.example.dashboard.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dashboard.models.Album;
import com.example.dashboard.repositories.AlbumRepository;

@Service
public class AlbumService {
    
    private final AlbumRepository albumRepo;
    
    /** Injects dependency: `AlbumRepository` */
    public AlbumService(AlbumRepository albumRepo) {
        this.albumRepo = albumRepo;
    }


    /** Save the given albums to the repository */
    public void saveAlbums(List<Album> albums) {
        albumRepo.saveAll(albums);
    }
}
