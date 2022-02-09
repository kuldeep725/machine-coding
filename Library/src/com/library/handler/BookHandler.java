package com.library.handler;

import com.library.exceptions.BookNotAvailableException;
import com.library.model.Book;
import com.library.model.Rack;

import java.util.List;

public interface BookHandler {

//    UUID borrowBook(int bookId, String userId);

    void borrowBookCopy(String bookCopyId, String userId);

    List<String> getBorrowedBooksByUser(String userId);

//    void addBook(Book book);

    void returnBook(String bookCopyId, String userId);

    Book searchBook(List<Rack> racks, String searchType, String value) throws BookNotAvailableException;
}
