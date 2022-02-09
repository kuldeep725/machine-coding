package com.library.handler.impl;

import com.library.exceptions.UnexpectedStateException;
import com.library.handler.BorrowHandler;

import java.util.*;

public class DefaultBorrowHandler implements BorrowHandler {

    private final Map<String, List<String>> userMap;

    public DefaultBorrowHandler() {
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

    @Override
    public void returnBook(String bookCopyId, String userId) {
        if(!userMap.containsKey(userId))
            throw new UnexpectedStateException("User has not borrowed any book");

        List<String> borrowedBooks = userMap.get(userId);
        borrowedBooks.remove(bookCopyId);
    }


}
