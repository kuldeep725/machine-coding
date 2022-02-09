package com.library.handler;

import com.library.exceptions.BookNotAvailableException;
import com.library.exceptions.RackNotAvailableException;
import com.library.model.Book;
import com.library.model.Rack;

import java.util.List;

public interface RackHandler {

    Rack findEmptyRack(List<Rack> racks) throws BookNotAvailableException;

    Book takeBook(List<Rack> racks, int bookId) throws BookNotAvailableException;

    Book removeBook(List<Rack> racks, String bookCopyId) throws BookNotAvailableException;

    int emptyRackCount(List<Rack> racks);

    void addBook(List<Rack> racks, Book book) throws RackNotAvailableException;
}
