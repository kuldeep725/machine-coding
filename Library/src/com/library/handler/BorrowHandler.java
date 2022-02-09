package com.library.handler;

import com.library.exceptions.BookNotAvailableException;
import com.library.model.Book;
import com.library.model.Rack;

import java.util.List;

public interface BorrowHandler {

    void borrowBookCopy(String bookCopyId, String userId);

    List<String> getBorrowedBooksByUser(String userId);

    void returnBook(String bookCopyId, String userId);

}
