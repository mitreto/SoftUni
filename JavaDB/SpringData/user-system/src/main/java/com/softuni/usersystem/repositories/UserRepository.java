package com.softuni.usersystem.repositories;

import com.softuni.usersystem.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    //    @Query("SELECT u FROM User AS u WHERE u.email LIKE CONCAT('%', '@', :endingWith)")
    Set<User> getAllByEmailEndingWith(String endingWith);

    @Query("SELECT CONCAT(u.firstName, ' ', u.lastName) AS full_name FROM User AS u WHERE u.id = :firstName")
    void getUserFullNameByFirstName(String firstName);


}
