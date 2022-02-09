package com.library.model;

import com.library.enums.RackStatus;

public class Rack {
    private RackStatus status;
    private Book book;

    public Rack(RackStatus status, Book book) {
        this.status = status;
        this.book = book;
    }

    public RackStatus getStatus() {
        return status;
    }

    public void setStatus(RackStatus status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
