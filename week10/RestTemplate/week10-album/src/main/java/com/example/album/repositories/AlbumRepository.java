package com.example.album.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.album.models.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> { }