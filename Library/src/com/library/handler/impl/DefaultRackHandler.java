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
    public Rack removeBook(List<Rack> racks, int bookId) throws BookNotAvailableException {
        return racks.stream().filter(rack -> rack.getBookId() == bookId)
                .findFirst().orElseThrow(BookNotAvailableException::new);
    }

    @Override
    public int emptyRackCount(List<Rack> racks) {
        return (int) racks.stream()
                .filter(rack -> rack.getStatus() == RackStatus.EMPTY)
                .count();
    }

    @Override
    public void addBook(List<Rack> racks, int bookId, String bookCopyId) throws RackNotAvailableException {
        Rack emptyRack = findEmptyRack(racks);

        emptyRack.setBookCopyId(bookCopyId);
        emptyRack.setStatus(RackStatus.OCCUPIED);
        emptyRack.setBookId(bookId);
    }

}
