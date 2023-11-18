package me.knighthat.plugin.file;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class YamlFile extends PluginFile {

    public <T extends JavaPlugin> YamlFile( @NotNull T plugin, @NotNull String fileName ) {
        super(plugin, fileName);
    }

    public boolean contains( String @NotNull ... keys ) {
        for ( String key : keys )
            if ( !get().contains(key) )
                return false;

        return true;
    }

    public boolean isInt( @NotNull String path ) {return get().isInt(path);}

    public boolean isString( @NotNull String path ) {return get().isString(path);}

    public boolean isBoolean( @NotNull String path ) {return get().isString(path);}

    public boolean isList( @NotNull String path ) {return get().isList(path);}

    public boolean isSection( @NotNull String path ) {return get().isConfigurationSection(path);}

    /**
     * @param path where to find the string
     *
     * @return non-null string queried from file, defaults to empty string if none found.
     */
    public @NotNull String getString( @NotNull String path ) {return get().getString(path, "");}

    /**
     * @param path where to find the integer
     *
     * @return integer queried from file, defaults to 1 if none found.
     */
    public int getInt( @NotNull String path ) {return get().getInt(path, 1);}

    /**
     * @param path where to find the list
     *
     * @return list of string from file, empty if none found.
     */
    public @NotNull List<String> getStringList( @NotNull String path ) {return get().getStringList(path);}
}
