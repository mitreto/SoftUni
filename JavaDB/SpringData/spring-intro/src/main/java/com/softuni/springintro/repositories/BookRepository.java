package com.softuni.springintro.repositories;

import com.softuni.springintro.entities.AgeRestriction;
import com.softuni.springintro.entities.Author;
import com.softuni.springintro.entities.Book;
import com.softuni.springintro.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate localDate);

    @Query("SELECT b FROM Book AS b WHERE b.author.id = ?1 ORDER BY b.releaseDate DESC, b.title")
    List<Book> findAllByAuthor(long Id);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);


//    @Query("SELECT b FROM Book AS b WHERE b.price < ?1 OR b.price > ?2")
    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lessThan, BigDecimal greaterThan);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> findAllByReleaseDateBefore(LocalDate localDate);

    List<Book> findAllByTitleContains(String contains);

    List<Book> findAllByAuthorLastNameStartingWith(String startingWith);

    @Query("SELECT b FROM Book AS b WHERE LENGTH(b.title) > :length")
    List<Book> findAllByTitleLengthGreaterThen(int length);

    Book findBookByTitle(String title);

    @Modifying
    @Query("UPDATE Book AS b SET b.copies = b.copies + :copies WHERE b.releaseDate > :date")
    int addCopiesToBooksReleasedAfter(@Param("date") LocalDate releaseDate, @Param("copies") int copies);

    @Query(value = "CALL GET_ALL_BOOKS_WRITTEN_BY_AUTHOR(:firstName, :lastName)", nativeQuery = true)
//    @Procedure(name = "Book.getBooksCountWrittenByAuthor")
    int findAllBooksCountWrittenByAuthor(@Param("firstName") String firstName, @Param("lastName") String lastName);

    List<Book> findBooksByCopiesLessThan(int lessThan);

    @Modifying
    @Query("DELETE FROM Book AS b WHERE b.copies < :lessThan")
    void deleteBooksByCopiesLessThan(int lessThan);

   }
