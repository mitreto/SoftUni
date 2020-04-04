package com.softuni.springintro.services;

import com.softuni.springintro.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

   void seedAuthors() throws IOException;

   long getAllAuthorsCount();

   Author findAuthorById(long id);

   List<Author> getAllAuthorsWithAtLeastOneBookWithReleaseDateBefore1990();

   List<Author> getAllAuthorsByBooksCount();

   Author getRandomAuthor();

   List<Author> getAllAuthorsByFirstNameEndingWith(String endingWith);

   List<Author> getAllAuthorsOrderedByBookCopies();

   Author getAuthorByFirstNameAndLastName(String firstName, String lastName);

}
