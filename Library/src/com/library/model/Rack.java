package com.library.model;

import com.library.enums.RackStatus;

public class Rack {
    private RackStatus status;
    private String bookCopyId;
    private int bookId;


    public Rack(RackStatus status, int bookId, String bookCopyId) {
        this.status = status;
        this.bookId = bookId;
        this.bookCopyId = bookCopyId;
    }

    public RackStatus getStatus() {
        return status;
    }

    public void setStatus(RackStatus status) {
        this.status = status;
    }

    public String getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(String bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
