package me.knighthat.plugin.logging;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    // START: Static fields/functions
    private static final Logger LOGGER = LoggerFactory.getLogger( "Server Quest" );

    public static void info( @NotNull String s ) { LOGGER.info( s ); }

    public static void warn( @NotNull String s ) { LOGGER.warn( s ); }

    public static void err( @NotNull String s ) { LOGGER.error( s ); }

    public static void exception( @Nullable String s, @NotNull Throwable throwable, boolean printStackTrace ) {
        if ( s != null && !s.isBlank() )
            err( s );

        String reason = throwable.getMessage();
        if ( reason != null && !reason.isBlank() )
            err( "Reason: " + throwable.getMessage() );

        Throwable cause = throwable.getCause();
        if ( cause != null && cause.getMessage() != null && !cause.getMessage().isBlank() )
            err( "Caused by: " + cause.getMessage() + " from " );

        if ( printStackTrace )
            throwable.printStackTrace();
    }

    public static void wexception( @Nullable String s, @NotNull Throwable throwable ) {
        if ( s != null && !s.isBlank() )
            warn( s );

        String reason = throwable.getMessage();
        if ( reason != null && !reason.isBlank() )
            warn( "Reason: " + throwable.getMessage() );

        Throwable cause = throwable.getCause();
        if ( cause != null && cause.getMessage() != null && !cause.getMessage().isBlank() )
            warn( "Caused by: " + cause.getMessage() + " from " );
    }

    public static void reportBug() {
        err( "Unexpected error occurs! Please report to:" );
        err( "https://github.com/knighthat/server-quest/issues" );
    }
    // END: Static fields/functions
}
