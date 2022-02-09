package com.library.handler.impl;

import com.library.model.Book;
import com.library.model.Rack;
import com.library.enums.RackStatus;
import com.library.exceptions.BookNotAvailableException;
import com.library.exceptions.RackNotAvailableException;
import com.library.handler.RackHandler;

import java.util.List;

public class DefaultRackHandler implements RackHandler {

    @Override
    public Rack findEmptyRack(List<Rack> racks) throws RackNotAvailableException {

        return racks.stream().filter(rack -> rack.getStatus() == RackStatus.EMPTY)
                .findFirst().orElseThrow(RackNotAvailableException::new);
    }

    @Override
    public Book takeBook(List<Rack> racks, int bookId) throws BookNotAvailableException {
        Rack rack = racks.stream().filter(rck -> rck.getBook().getBookId() == bookId)
                .findFirst().orElseThrow(BookNotAvailableException::new);
        rack.setStatus(RackStatus.EMPTY);
        rack.setBook(Book.EMPTY);

        return rack.getBook();
    }

    @Override
    public Book removeBook(List<Rack> racks, String bookCopyId) throws BookNotAvailableException {
        Rack rack = racks.stream().filter(rck -> rck.getBook().getBookCopyId().equals(bookCopyId))
                .findFirst().orElseThrow(BookNotAvailableException::new);
        rack.setStatus(RackStatus.EMPTY);
        rack.setBook(Book.EMPTY);

        return rack.getBook();
    }

    @Override
    public int emptyRackCount(List<Rack> racks) {
        return (int) racks.stream()
                .filter(rack -> rack.getStatus() == RackStatus.EMPTY)
                .count();
    }

    @Override
    public void addBook(List<Rack> racks, Book book) throws RackNotAvailableException {
        Rack emptyRack = findEmptyRack(racks);

        emptyRack.setBook(book);
        emptyRack.setStatus(RackStatus.OCCUPIED);
    }

}
