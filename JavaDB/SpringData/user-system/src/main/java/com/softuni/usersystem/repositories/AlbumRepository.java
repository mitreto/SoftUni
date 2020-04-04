package com.softuni.usersystem.repositories;

import com.softuni.usersystem.entites.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, String> {
}
