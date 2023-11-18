package me.knighthat.plugin.file;

import me.knighthat.plugin.exception.InvalidValueTypeException;
import me.knighthat.plugin.exception.MissingKeyException;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestFile extends YamlFile {

    // START: Static fields/functions
    private static final String[] REQUIRED_KEYS = { "id", "name", "quest.type", "quest.objective", "quest.amount", "rewards" };
    // END: Static fields/functions

    public <T extends JavaPlugin> QuestFile( @NotNull T plugin, @NotNull String fileName ) {
        super(plugin, fileName);
        verifyKeys();
    }

    private void verifyKeys() {
        ArrayList<String> missingKeys = new ArrayList<>();
        for ( String key : REQUIRED_KEYS )
            if ( !contains(key) )
                missingKeys.add(key);

        if ( missingKeys.isEmpty() ) {

            if ( !isInt("amount") )
                throw new InvalidValueTypeException("value of \"amount\" is not an integer");
            if ( !isList("rewards.items") )
                throw new InvalidValueTypeException("value of \"rewards\" is not a list");

        } else {

            String message = Arrays.toString(missingKeys.toArray());
            if ( missingKeys.size() == 1 )
                message += " is";
            else
                message += " are";

            throw new MissingKeyException(message + " missing from file " + fileName);
        }
    }
}
