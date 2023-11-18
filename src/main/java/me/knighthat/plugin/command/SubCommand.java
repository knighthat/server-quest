package me.knighthat.plugin.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public interface SubCommand {

    @NotNull String getName();

    void execute( @NotNull CommandSender sender, @NotNull String label, String @NotNull [] args );
}
