package com.softuni.springintro.controllers;

import com.softuni.springintro.entities.Book;
import com.softuni.springintro.services.AuthorService;
import com.softuni.springintro.services.BookService;
import com.softuni.springintro.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }


    @Override
    public void run(String... args) throws Exception {

        // seed data
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

        // Write Queries
        // 1. Get all books after the year 2000. Print only their titles.

        //  List<Book> books = this.bookService.getAllBooksAfter2000();

        // 2. Get all authors with at least one book with release date before 1990. Print their first name and last name.

//        this.authorService
//                .getAllAuthorsWithAtLeastOneBookWithReleaseDateBefore1990()
//                .forEach(a ->
//                        System.out.printf("%s %s%n",
//                                a.getFirstName(),
//                                a.getLastName()));


        // 3. Get all authors, ordered by the number of their books (descending). Print their first name, last name and book count.

//        this.authorService
//                .getAllAuthorsByBooksCount()
//                .forEach(a -> System.out.printf("%s %s %d%n",
//                        a.getFirstName(),
//                        a.getLastName(),
//                        a.getBooks().size()));

        //4. Get all books from author George Powell,
        // ordered by their release date (descending),
        // then by book title (ascending).
        // Print the book's title, release date and copies.

//        this.bookService.findAllByAuthorId()
//        .forEach(b -> {
//            System.out.printf("%s %s %d%n",
//                    b.getTitle(),
//                    b.getReleaseDate(),
//                    b.getCopies());
//        });

        // ADVANCED QUERYING EXERCISES

        // 1. Books Titles by Age Restriction

//        try {
//            System.out.println("Enter age restriction: ");
//            this.bookService
//                    .getAllBooksByAgeRestriction(this.bufferedReader.readLine())
//                    .stream()
//                    .map(Book::getTitle)
//                    .forEach(System.out::println);
//
//        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
//        }

        // 2. Golden Books

//        System.out.println("Choose the edition type by writing it in the console and press enter" +
//                " then choose the copies limit by writing it in the console and press enter:");

//        this.bookService
//                .getAllBooksByEditionTypeAndNumberOfCopies(
//                         "Gold",5000)
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);

        // 3. Books by Price

//        this.bookService
//                .getAllBooksByPriceLessThanAndGreaterThan(new BigDecimal(5), new BigDecimal(40))
//                .forEach(b -> System.out.printf("%s -> $%.2f%n",
//                        b.getTitle(),
//                        b.getPrice()));

        // 4. Not Released Books

//        System.out.println("Enter release date:");
//        this.bookService
//                .getAllBookByReleaseDateNotInYear(
//                        Integer.parseInt(this.bufferedReader.readLine()))
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);

        // 5. Books Released Before Date

//        System.out.println("Enter release date: ");
//        this.bookService
//                .getAllBooksByReleaseDateBefore(this.bufferedReader.readLine())
//                .forEach(b -> System.out.printf("%s %s %.2f%n",
//                        b.getTitle(),
//                        b.getEditionType(),
//                        b.getPrice()));

        // 6. Authors Search

//        System.out.println("Enter ending with: ");
//
//        this.authorService
//                .getAllAuthorsByFirstNameEndingWith(
//                        this.bufferedReader.readLine()
//                )
//                .forEach(a -> System.out.printf("%s %s%n",
//                        a.getFirstName(),
//                        a.getLastName()));

        // 7. Books Search

//        System.out.println("Enter contains");
//        this.bookService
//                .getAllBooksByTitleContains(this.bufferedReader.readLine())
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);

        // 8. Book Titles Search

//        System.out.println("Enter author last name starts with:");
//        this.bookService
//                .getAllByAuthorLastNameStartingWith(this.bufferedReader.readLine())
//                .forEach(b -> System.out.printf("%s (%s %s)%n",
//                        b.getTitle(),
//                        b.getAuthor().getFirstName(),
//                        b.getAuthor().getLastName()));

        // 9. Count Books

//        System.out.println("Enter length:");
//        int length = Integer.parseInt(this.bufferedReader.readLine());
//
//        List<Book> allBooksWithTitleLengthGreaterThen = this.bookService
//                .getAllBooksWithTitleLengthGreaterThen(length);

//        System.out.printf("There are %d books with longer title than %d symbols%n",
//                allBooksWithTitleLengthGreaterThen.size(),
//                length);

        // 10. Total Book Copies

//        this.authorService.getAllAuthorsOrderedByBookCopies()
//                .forEach(a -> System.out.printf("%s %s - %d%n",
//                        a.getFirstName(),
//                        a.getLastName(),
//                        a.getBooks().size()));

        // 11. Reduced Book

//        System.out.println("Enter book title: ");
//
//        Book book = this.bookService.getBookByTitle(this.bufferedReader.readLine());
//
//        System.out.printf("%s %s %s %.2f%n",
//                book.getTitle(),
//                book.getEditionType(),
//                book.getAgeRestriction(),
//                book.getPrice());

        // 12. Increase Book Copies

//        System.out.println("Enter release date:");
//        String releaseDate = this.bufferedReader.readLine();
//        System.out.println("Enter copies:");
//        int copies = Integer.parseInt(this.bufferedReader.readLine());
//
//        int totalCopies =
//                this.bookService
//                        .updateBookCopiesByReleaseDateAfter(releaseDate, copies) * copies;
//
//        System.out.println(totalCopies);
//
//        this.bookService.updateBookCopiesByReleaseDateAfter(releaseDate, copies);
//
//        List<Book> books = this.bookService.getAllBooksByReleaseDateAfter(releaseDate, "dd MMM yyyy");
//
//        System.out.printf("%d books are released after %s, so total of %s book copies were added%n",
//                books.size(),
//                releaseDate,
//                books.size() * copies);

        // 13. Remove Books

//        System.out.println("Enter book copies: ");
//
//        int copiesLessThan = Integer.parseInt(this.bufferedReader.readLine());
//
//        List<Book> deletedBooks = new ArrayList<>(this.bookService
//                .getAllBooksWithCopiesLessThan(copiesLessThan));
//
//        deletedBooks.forEach(b -> System.out.println(b.getTitle()));
//        System.out.println("Total deleted books: " + deletedBooks.size());
//
//        this.bookService.deleteAllBooksWithCopiesLessThan(copiesLessThan);
//
//        System.out.println("Successfully executed");


        // 14. Stored Procedure

//        System.out.print("Enter author full name: ");
//        String[] authorsFullName = bufferedReader.readLine().split("\\s+");
//        String firstName = authorsFullName[0];
//        String lastName = authorsFullName[1];
//
//        int booksCount = this.bookService.getAllBooksCountWrittenByAuthor(firstName, lastName);
//
//        System.out.printf("%s %s has written %d books%n",
//                firstName,
//                lastName,
//                booksCount);

    }
}
