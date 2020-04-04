package com.softuni.springintro.services.impl;

import com.softuni.springintro.entities.Author;
import com.softuni.springintro.repositories.AuthorRepository;
import com.softuni.springintro.services.AuthorService;
import com.softuni.springintro.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.softuni.springintro.constants.GlobalConstants.*;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }


    @Override
    public void seedAuthors() throws IOException {

        if (this.authorRepository.count() != 0) {
            return;
        }

        List<String> fileContent = this.fileUtil.readFileContent(AUTHOR_FILE_PATH);

        fileContent
                .forEach(r -> {
                    String[] params = r.split("\\s+");
                    Author author = new Author(params[0], params[1]);

                    this.authorRepository.saveAndFlush(author);
                });
    }

    @Override
    public long getAllAuthorsCount() {
        return this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(long id) {
        return this.authorRepository.getOne(id);
    }

    @Override
    public List<Author> getAllAuthorsWithAtLeastOneBookWithReleaseDateBefore1990() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate releaseDate = LocalDate.parse("01/01/1990", formatter);

        return this.authorRepository.findAllByBooksReleaseDateBefore(releaseDate);

    }

    @Override
    public List<Author> getAllAuthorsByBooksCount() {
        return this.authorRepository.findAuthorByCountOfBooks();
    }

    @Override
    public Author getRandomAuthor() {

        Random random = new Random();
        int randomId =
                random.nextInt((int) this.getAllAuthorsCount()) + 1;

        return this.findAuthorById(randomId);
    }

    @Override
    public List<Author> getAllAuthorsByFirstNameEndingWith(String endingWith) {
        return Collections.unmodifiableList(
                this.authorRepository
                        .findAllByFirstNameEndingWith(endingWith)
        );
    }

    @Override
    public List<Author> getAllAuthorsOrderedByBookCopies() {
        return Collections.unmodifiableList(
                this.authorRepository.findAllByBookCopies());
    }

    @Override
    public Author getAuthorByFirstNameAndLastName(String firstName, String lastName) {
        return this.authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName);
    }
}
