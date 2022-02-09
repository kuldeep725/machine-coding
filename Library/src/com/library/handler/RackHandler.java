package com.library.handler;

import com.library.exceptions.BookNotAvailableException;
import com.library.exceptions.RackNotAvailableException;
import com.library.model.Rack;

import java.util.List;

public interface RackHandler {

    Rack findEmptyRack(List<Rack> racks) throws BookNotAvailableException;

    Rack removeBook(List<Rack> racks, int bookId) throws BookNotAvailableException;

    int emptyRackCount(List<Rack> racks);

    void addBook(List<Rack> racks, int bookId, String bookCopyId) throws RackNotAvailableException;
}
