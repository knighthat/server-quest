package me.knighthat.plugin;

import me.knighthat.plugin.command.CommandManager;
import me.knighthat.plugin.logging.Log;
import me.knighthat.plugin.quest.Quests;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerQuestMain extends JavaPlugin {

    @Override
    public void onEnable() {
        // Load quests from files
        Quests.loadQuests(this);

        // Load commands
        PluginCommand command = getCommand("serverquest");
        if ( command == null ) {
            Log.err("Failed to load command!");
            Log.reportBug();
        } else
            command.setExecutor(new CommandManager());
    }
}