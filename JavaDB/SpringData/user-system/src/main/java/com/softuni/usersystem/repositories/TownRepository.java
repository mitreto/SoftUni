package com.softuni.usersystem.repositories;

import com.softuni.usersystem.entites.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TownRepository extends JpaRepository<Town, String> {
}
