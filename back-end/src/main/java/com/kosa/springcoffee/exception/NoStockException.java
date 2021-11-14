package com.kosa.springcoffee.exception;

public class NoStockException extends RuntimeException{
    public NoStockException(String message){
        super(message);
    }
}
