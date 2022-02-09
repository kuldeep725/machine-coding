package com.library.handler.impl;

import com.library.exceptions.BookNotAvailableException;
import com.library.exceptions.UnexpectedStateException;
import com.library.handler.BookHandler;
import com.library.handler.SearchHandler;
import com.library.model.Book;
import com.library.model.Rack;

import java.util.*;

public class DefaultBookHandler implements BookHandler {

    private final Map<String, List<String>> userMap;

    public DefaultBookHandler() {
        userMap = new HashMap<>();
    }

    @Override
    public void borrowBookCopy(String bookCopyId, String userId) {
        if(!userMap.containsKey(userId))
            userMap.put(userId, new ArrayList<>());

        List<String> borrowedBooks = userMap.get(userId);
        if(borrowedBooks.contains(bookCopyId))
            throw new UnexpectedStateException("User has already borrowed the book " + bookCopyId);

        borrowedBooks.add(bookCopyId);
    }

    @Override
    public List<String> getBorrowedBooksByUser(String userId) {
        return userMap.get(userId);
    }

//    @Override
//    public void addBook(Book book) {
//        books.add(book);
//    }

    @Override
    public void returnBook(String bookCopyId, String userId) {
        if(!userMap.containsKey(userId))
            throw new UnexpectedStateException("User has not borrowed any book");

        List<String> borrowedBooks = userMap.get(userId);
        borrowedBooks.remove(bookCopyId);
    }

    @Override
    public Book searchBook(List<Rack> racks, String searchType, String value) throws BookNotAvailableException {
        return switch (searchType) {
            case "book_id" -> SearchHandler.searchByBookId(racks, Integer.parseInt(value));
            case "author_id" -> SearchHandler.searchByAuthorId(racks, value);
            case "book_copy_id" -> SearchHandler.searchByBookCopyId(racks, value);
            case "title" -> SearchHandler.searchByTitle(racks, value);
            case "publisher" -> SearchHandler.searchByPublisher(racks, value);
            default -> Book.EMPTY;
        };

    }


}
