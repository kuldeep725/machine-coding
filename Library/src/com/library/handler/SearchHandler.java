package com.library.handler;

import com.library.exceptions.BookNotAvailableException;
import com.library.model.Book;
import com.library.model.Rack;

import java.util.List;

public class SearchHandler {

    public static Book searchByBookId(List<Rack> racks, int value) {
        return books.stream().filter(book -> book.getBookId() == value)
                .findFirst().orElseThrow(BookNotAvailableException::new);
    }

    public static Book searchByAuthorId(List<Rack> racks, String value) {
        return books.stream().filter(book -> book.getAuthors().contains(value))
                .findFirst().orElseThrow(BookNotAvailableException::new);
    }

    public static Book searchByBookCopyId(List<Rack> racks, String value) {
        return books.stream().filter(book -> book.getBookCopyIds().contains(value))
                .findFirst().orElseThrow(BookNotAvailableException::new);
    }

    public static Book searchByTitle(List<Rack> racks, String value) {
        return books.stream().filter(book -> book.getTitle().equals(value))
                .findFirst().orElseThrow(BookNotAvailableException::new);
    }

    public static Book searchByPublisher(List<Rack> racks, String value) {
        return books.stream().filter(book -> book.getPublishers().contains(value))
                .findFirst().orElseThrow(BookNotAvailableException::new);
    }
}
