package com.softuni.springintro.services.impl;

import com.softuni.springintro.entities.*;
import com.softuni.springintro.repositories.BookRepository;
import com.softuni.springintro.services.AuthorService;
import com.softuni.springintro.services.BookService;
import com.softuni.springintro.services.CategoryService;
import com.softuni.springintro.utils.ConsoleReader;
import com.softuni.springintro.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.softuni.springintro.constants.GlobalConstants.*;
import static com.softuni.springintro.entities.EditionType.*;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final FileUtil fileUtil;
    private final ConsoleReader reader;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository, FileUtil fileUtil, AuthorService authorService, CategoryService categoryService, ConsoleReader reader) {
        this.bookRepository = bookRepository;
        this.fileUtil = fileUtil;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.reader = reader;
    }


    @Override
    public void seedBooks() throws IOException {

        if (this.bookRepository.count() != 0) {
            return;
        }

        List<String> fileContent = this.fileUtil
                .readFileContent(BOOKS_FILE_PATH);

        fileContent
                .forEach(r -> {
                    String[] params = r.split("\\s+");

                    Author author = this.authorService.getRandomAuthor();

                    EditionType editionType =
                            values()[Integer.parseInt(params[0])];

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                    LocalDate releaseDate = LocalDate.parse(params[1], formatter);

                    int copies = Integer.parseInt(params[2]);

                    BigDecimal price = new BigDecimal(params[3]);

                    AgeRestriction ageRestriction =
                            AgeRestriction.values()[Integer.parseInt(params[4])];

                    String title = this.getTitle(params);

                    Set<Category> categories = this.categoryService.getRandomCategories();

                    Book book = new Book();
                    book.setAuthor(author);
                    book.setEditionType(editionType);
                    book.setReleaseDate(releaseDate);
                    book.setCopies(copies);
                    book.setPrice(price);
                    book.setAgeRestriction(ageRestriction);
                    book.setTitle(title);
                    book.setCategories(categories);

                    this.bookRepository.saveAndFlush(book);

                });
    }

    @Override
    public List<Book> getAllBooksByReleaseDateAfter(String date, String formatter) {

        LocalDate releaseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(formatter));

        return this.bookRepository.findAllByReleaseDateAfter(releaseDate);
    }

    @Override
    public List<Book> findAllByAuthorId() {
        return this.bookRepository.findAllByAuthor(4);
    }

    @Override
    public List<Book> getAllBooksByAgeRestriction(String ageRestriction) throws IOException {

        List<Book> booksByAgeRestriction = bookRepository.
                findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()));

        if (booksByAgeRestriction.isEmpty()) {
            throw new IllegalArgumentException("No books found with the age restriction entered.");
        }
        return Collections.unmodifiableList(booksByAgeRestriction);
    }

    @Override
    public List<Book> getAllBooksByEditionTypeAndNumberOfCopies(String editionType, int copies) {

        return Collections.unmodifiableList(
                this.bookRepository
                        .findAllByEditionTypeAndCopiesLessThan(
                                EditionType.valueOf(editionType.toUpperCase()),
                                copies));
    }

    @Override
    public List<Book> getAllBooksByPriceLessThanAndGreaterThan(BigDecimal lessThan, BigDecimal greaterThan) {

        return Collections.unmodifiableList(this.bookRepository
                .findAllByPriceLessThanOrPriceGreaterThan(lessThan, greaterThan));
    }

    @Override
    public List<Book> getAllBookByReleaseDateNotInYear(int year) {
        LocalDate before = LocalDate.of(year, 1, 1);
        LocalDate after = LocalDate.of(year, 12, 31);

        return Collections.unmodifiableList(this.bookRepository
                .findAllByReleaseDateBeforeOrReleaseDateAfter(before, after));
    }

    @Override
    public List<Book> getAllBooksByReleaseDateBefore(String localDate) {

        LocalDate releaseDate =
                LocalDate.parse(localDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        return Collections.unmodifiableList(
                this.bookRepository
                        .findAllByReleaseDateBefore(releaseDate)
        );
    }

    @Override
    public List<Book> getAllBooksByTitleContains(String contains) {
        return Collections.unmodifiableList(
                this.bookRepository
                        .findAllByTitleContains(contains)
        );
    }

    @Override
    public List<Book> getAllByAuthorLastNameStartingWith(String startingWith) {
        return Collections.unmodifiableList(
                this.bookRepository.findAllByAuthorLastNameStartingWith(startingWith)
        );
    }

    @Override
    public List<Book> getAllBooksWithTitleLengthGreaterThen(int graterThen) {
        return Collections.unmodifiableList(
                this.bookRepository.findAllByTitleLengthGreaterThen(graterThen)
        );
    }

    @Override
    public Book getBookByTitle(String title) {
        return this.bookRepository.findBookByTitle(title);
    }

    @Override
    public int updateBookCopiesByReleaseDateAfter(String date, int copies) {

        LocalDate releaseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return this.bookRepository.addCopiesToBooksReleasedAfter(releaseDate, copies);

    }

    @Override
    public int getAllBooksCountWrittenByAuthor(String firstName, String lastName) {

        return this.bookRepository.findAllBooksCountWrittenByAuthor(firstName, lastName);
    }

    @Override
    public List<Book> getAllBooksWithCopiesLessThan(int lessThan) {
        return Collections.unmodifiableList(
                this.bookRepository.findBooksByCopiesLessThan(lessThan)
        );
    }

    @Override
    public void deleteAllBooksWithCopiesLessThan(int lessThan) {
        this.bookRepository.deleteBooksByCopiesLessThan(lessThan);
    }

    private String getTitle(String[] params) {

        StringBuilder titleBuilder = new StringBuilder();

        for (int i = 5; i < params.length; i++) {

            titleBuilder.append(params[i]).append(" ");
        }

        return titleBuilder.toString().trim();
    }

}
