package com.softuni.springintro.repositories;

import com.softuni.springintro.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT b.author FROM Book AS b WHERE b.releaseDate < :localDate")
    List<Author> findAllByBooksReleaseDateBefore(LocalDate localDate);

    @Query("SELECT a FROM Author AS a ORDER BY a.books.size DESC")
    List<Author> findAuthorByCountOfBooks();

    List<Author> findAllByFirstNameEndingWith(String endingWith);

    @Query("SELECT a FROM Author AS a ORDER BY a.books.size DESC")
    List<Author> findAllByBookCopies();

    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);


}
