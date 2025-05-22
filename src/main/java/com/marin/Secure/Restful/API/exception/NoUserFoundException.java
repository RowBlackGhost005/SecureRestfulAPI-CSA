package com.marin.Secure.Restful.API.exception;

/**
 * Exception to be thrown when trying to access a non-registered user of this API by its ID
 */
public class NoUserFoundException extends Exception{

    public NoUserFoundException(String message){
        super(message);
    }
}
