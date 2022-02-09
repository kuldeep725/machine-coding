package com.library.model;

import java.util.List;

public class Book {
    public static final Book EMPTY = new Book(-1, "", null, null, null);
    private final String bookCopyId;
    private final String title;
    private final List<String> authors;
    private final List<String> publishers;
    private final int bookId;

    public Book(int bookId, String title, List<String> authors, List<String> publishers, String bookCopyId) {
        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
        this.publishers = publishers;
        this.bookCopyId = bookCopyId;
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

    public String getBookCopyId() {
        return bookCopyId;
    }

}
