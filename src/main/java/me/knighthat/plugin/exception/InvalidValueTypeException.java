package me.knighthat.plugin.exception;

import java.io.Serial;

public class InvalidValueTypeException extends RuntimeException {

    // START: Static fields/functions
    @Serial
    private static final long serialVersionUID = 5321645875215469352L;
    // END: Static fields/functions

    public InvalidValueTypeException( String s ) {super(s);}
}
