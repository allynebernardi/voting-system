package com.sicredi.challenge.exceptions;

public class AgendaException extends RuntimeException {
    public AgendaException(String s, Object...params){
        super(String.format(s,params));
    }
}
