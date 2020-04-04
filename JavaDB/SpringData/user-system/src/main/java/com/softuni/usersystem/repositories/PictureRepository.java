package com.softuni.usersystem.repositories;

import com.softuni.usersystem.entites.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PictureRepository extends JpaRepository<Picture, String> {
}
