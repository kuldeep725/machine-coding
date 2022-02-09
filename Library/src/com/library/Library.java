package com.library;

import com.library.enums.RackStatus;
import com.library.exceptions.RackNotAvailableException;
import com.library.handler.BorrowHandler;
import com.library.handler.RackHandler;
import com.library.handler.SearchHandler;
import com.library.model.Book;
import com.library.model.Rack;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Rack> racks;
    private final RackHandler rackHandler;
    private final BorrowHandler borrowHandler;

    public Library(int n, RackHandler rackHandler, BorrowHandler borrowHandler) {
        this.rackHandler = rackHandler;
        this.borrowHandler = borrowHandler;
        racks = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            racks.add(new Rack(RackStatus.EMPTY, Book.EMPTY));
        }
    }

    public void addBook(int bookId, java.lang.String title, List<java.lang.String> authors, List<java.lang.String> publishers, List<java.lang.String> bookCopyIds)
            throws RackNotAvailableException {
        if(rackHandler.emptyRackCount(racks) < bookCopyIds.size())
            throw new RackNotAvailableException();

        for (String bookCopyId : bookCopyIds) {
            Book book = new Book(bookId, title, authors, publishers, bookCopyId);

            rackHandler.addBook(racks, book);
        }

    }

    public void removeBookCopy(String bookCopyId) {
        rackHandler.removeBook(racks, bookCopyId);
    }

    public String borrowBook(int bookId, String userId) {
        Book book = rackHandler.takeBook(racks, bookId);

        borrowHandler.borrowBookCopy(book.getBookCopyId(), userId);

        return book.getBookCopyId();
    }

    public void returnBook(String bookCopyId, String userId) {
        Book book = SearchHandler.searchBook(racks, SearchHandler.BOOK_ID, bookCopyId);

        borrowHandler.returnBook(bookCopyId, userId);
        rackHandler.addBook(racks, book);
    }

    public List<String> getBorrowedBooksByUser(String userId) {
        return borrowHandler.getBorrowedBooksByUser(userId);
    }

    public Book searchBook(String searchType, String value) {
        return SearchHandler.searchBook(racks, searchType, value);
    }

}
