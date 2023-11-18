package me.knighthat.plugin.exception;

import java.io.Serial;

public class MissingKeyException extends RuntimeException {

    // START: Static fields/functions
    @Serial
    private static final long serialVersionUID = -1542632157486254987L;
    // END: Static fields/functions

    public MissingKeyException( String message ) {super(message);}
}
