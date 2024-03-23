package com.example.dashboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dashboard.models.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer>{

}
