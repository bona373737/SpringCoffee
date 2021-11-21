package com.kosa.springcoffee.base.exception;

public class NoStockException extends RuntimeException{
    public NoStockException(String message){
        super(message);
    }
}
