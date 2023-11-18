package me.knighthat.plugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class CommandManager implements CommandExecutor {

    // START: Static fields/functions
    public static final @NotNull Set<SubCommand> SUB_COMMANDS;

    static {
        SUB_COMMANDS = new HashSet<>();
        SUB_COMMANDS.add(new ListCommand());
    }
// END: Static fields/functions

    @Override
    public boolean onCommand( @NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings ) {
        return false;
    }
}
