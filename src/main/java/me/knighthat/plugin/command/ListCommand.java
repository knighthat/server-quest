package me.knighthat.plugin.command;

import me.knighthat.plugin.quest.Quest;
import me.knighthat.plugin.quest.Quests;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ListCommand implements SubCommand {

    @Override
    public @NotNull String getName() {return "list";}

    @Override
    public void execute( @NotNull CommandSender sender, @NotNull String label, String @NotNull [] args ) {
        for ( Quest quest : Quests.QUESTS.values() )
            sender.sendMessage(
                    " ",
                    "Quest " + quest.getId() + ":",
                    "- Name: " + quest.getName(),
                    "- Description: " + quest.getDescription(),
                    "- Type: " + quest.getType(),
                    "- Objective: " + quest.getObjective(),
                    "- Number of reward(s): " + quest.getRewards().size()
            );
    }
}
