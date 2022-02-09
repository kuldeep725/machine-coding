package com.library.handler;

import com.library.exceptions.BookNotAvailableException;
import com.library.model.Book;
import com.library.model.Rack;

import java.util.List;

public class SearchHandler {

    public static final String BOOK_ID = "book_id";
    public static final String AUTHOR_ID = "author_id";
    public static final String BOOK_COPY_ID = "book_copy_id";
    public static final String TITLE = "title";
    public static final String PUBLISHER = "publisher";

    public static Book searchByBookId(List<Rack> racks, int value) {
        return racks.stream()
                .map(Rack::getBook)
                .filter(book -> book.getBookId() == value)
                .findFirst()
                .orElseThrow(BookNotAvailableException::new);
    }

    public static Book searchByAuthorId(List<Rack> racks, String value) {
        return racks.stream()
                .map(Rack::getBook)
                .filter(book -> book.getAuthors().contains(value))
                .findFirst()
                .orElseThrow(BookNotAvailableException::new);
    }

    public static Book searchByBookCopyId(List<Rack> racks, String value) {
        return racks.stream()
                .map(Rack::getBook)
                .filter(book -> book.getBookCopyId().equals(value))
                .findFirst()
                .orElseThrow(BookNotAvailableException::new);
    }

    public static Book searchByTitle(List<Rack> racks, String value) {
        return racks.stream()
                .map(Rack::getBook)
                .filter(book -> book.getTitle().equals(value))
                .findFirst()
                .orElseThrow(BookNotAvailableException::new);
    }

    public static Book searchByPublisher(List<Rack> racks, String value) {
        return racks.stream()
                .map(Rack::getBook)
                .filter(book -> book.getPublishers().contains(value))
                .findFirst()
                .orElseThrow(BookNotAvailableException::new);
    }

    public static Book searchBook(List<Rack> racks, String searchType, String value) {
        return switch (searchType) {
            case BOOK_ID -> searchByBookId(racks, Integer.parseInt(value));
            case AUTHOR_ID -> searchByAuthorId(racks, value);
            case BOOK_COPY_ID -> searchByBookCopyId(racks, value);
            case TITLE -> searchByTitle(racks, value);
            case PUBLISHER -> searchByPublisher(racks, value);
            default -> Book.EMPTY;
        };
    }
}
