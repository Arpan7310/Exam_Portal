package com.exam.helper;

public class UserFoundException extends Exception{

    public UserFoundException () {
        super ("USer with this name is already there in db");
    }


    public UserFoundException(String msg) {
        super (msg);
    }
}
