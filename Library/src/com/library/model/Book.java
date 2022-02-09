package com.library.model;

import java.util.List;

public class Book {
    public static final Book EMPTY = new Book(-1, "", null, null, null);
    private final List<String> bookCopyIds;
    private final String title;
    private final List<String> authors;
    private final List<String> publishers;
    private final int bookId;

    public Book(int bookId, String title, List<String> authors, List<String> publishers, List<String> bookCopyIds) {
        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
        this.publishers = publishers;
        this.bookCopyIds = bookCopyIds;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public List<String> getBookCopyIds() {
        return bookCopyIds;
    }

}
