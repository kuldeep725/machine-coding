package com.library;

import com.library.enums.RackStatus;
import com.library.exceptions.RackNotAvailableException;
import com.library.handler.BookHandler;
import com.library.handler.RackHandler;
import com.library.model.Book;
import com.library.model.Rack;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Rack> racks;
    private final RackHandler rackHandler;
    private final BookHandler bookHandler;
    public static final String BOOK_ID = "book_id";
    public static final String AUTHOR_ID = "author_id";

    public Library(int n, RackHandler bookHandler, BookHandler borrowHandler) {
        this.rackHandler = bookHandler;
        this.bookHandler = borrowHandler;
        racks = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            racks.add(new Rack(RackStatus.EMPTY, -1, ""));
        }
    }

    public void addBook(int bookId, java.lang.String title, List<java.lang.String> authors, List<java.lang.String> publishers, List<java.lang.String> bookCopyIds)
            throws RackNotAvailableException {
        if(rackHandler.emptyRackCount(racks) < bookCopyIds.size())
            throw new RackNotAvailableException();

        Book book = new Book(bookId, title, authors, publishers, bookCopyIds);
        bookHandler.addBook(book);

        for (String bookCopyId : bookCopyIds) {
            rackHandler.addBook(racks, bookId, bookCopyId);
        }

    }

    public String borrowBook(int bookId, String userId) {
        Rack rack = rackHandler.removeBook(racks, bookId);

        bookHandler.borrowBookCopy(rack.getBookCopyId(), userId);

        return rack.getBookCopyId();
    }

    public void returnBook(String bookCopyId, String userId) {
        Book book = bookHandler.searchBook(BOOK_ID, bookCopyId);

        bookHandler.returnBook(bookCopyId, userId);
        rackHandler.addBook(racks, book.getBookId(), bookCopyId);
    }

    public List<String> getBorrowedBooksByUser(String userId) {
        return bookHandler.getBorrowedBooksByUser(userId);
    }

    public Book


}
