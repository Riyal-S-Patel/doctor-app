package com.logicoy.doctorapp.exception;

public class PasswordMismatchException extends Exception{

    public  PasswordMismatchException(String message){
        super(message);
    }

    public  PasswordMismatchException(String message, Throwable throwable){
        super(message, throwable);
    }
}
