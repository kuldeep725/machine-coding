package com.library.exceptions;

public class UnexpectedStateException extends RuntimeException{
    public UnexpectedStateException(String s) {
        super(s);
    }
}
