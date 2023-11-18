package me.knighthat.plugin.file;

import lombok.Getter;
import me.knighthat.plugin.logging.Log;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

public abstract class PluginFile {

    protected final @NotNull JavaPlugin plugin;
    @Getter
    final @NotNull           String     fileName;

    private @Nullable File              file;
    private @Nullable YamlConfiguration yaml;

    public <T extends JavaPlugin> PluginFile( @NotNull T plugin, @NotNull String fileName ) {
        this.plugin = plugin;
        this.fileName = fileName;

        createIfNotExist();
        reload();
    }

    private void createIfNotExist() {
        if ( file == null )
            this.file = new File(plugin.getDataFolder(), fileName);

        if ( !file.exists() ) {
            try {
                if ( !this.file.createNewFile() )
                    throw new IOException();
            } catch ( IOException e ) {
                Log.exception("Failed to create file " + fileName, e, true);
                Log.reportBug();
            }
        }
    }

    public void reload() {
        createIfNotExist();

        assert file != null;
        this.yaml = YamlConfiguration.loadConfiguration(file);
    }

    public @NotNull YamlConfiguration get() {
        if ( yaml == null )
            reload();
        return this.yaml;
    }
}
