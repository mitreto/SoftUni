package com.softuni.springintro.services;

import com.softuni.springintro.entities.Book;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {

    void seedBooks() throws IOException;

    List<Book> getAllBooksByReleaseDateAfter(String date, String formatter);

    List<Book> findAllByAuthorId();

    List<Book> getAllBooksByAgeRestriction(String ageRestriction) throws IOException;

    List<Book> getAllBooksByEditionTypeAndNumberOfCopies(String editionType, int copies);

    List<Book> getAllBooksByPriceLessThanAndGreaterThan(BigDecimal lessThan, BigDecimal greaterThan);

    List<Book> getAllBookByReleaseDateNotInYear(int year);

    List<Book> getAllBooksByReleaseDateBefore(String localDate);

    List<Book> getAllBooksByTitleContains(String contains);

    List<Book> getAllByAuthorLastNameStartingWith(String startingWith);

    List<Book> getAllBooksWithTitleLengthGreaterThen(int graterThen);

    Book getBookByTitle(String title);

    int updateBookCopiesByReleaseDateAfter(String date, int copies);

    int getAllBooksCountWrittenByAuthor(String firstName, String lastName);

    List<Book> getAllBooksWithCopiesLessThan(int lessThan);

    void deleteAllBooksWithCopiesLessThan(int lessThan);
}
