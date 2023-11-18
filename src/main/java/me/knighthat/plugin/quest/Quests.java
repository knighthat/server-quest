package me.knighthat.plugin.quest;

import me.knighthat.plugin.exception.InvalidValueTypeException;
import me.knighthat.plugin.exception.MissingKeyException;
import me.knighthat.plugin.file.QuestFile;
import me.knighthat.plugin.logging.Log;
import me.knighthat.plugin.reward.Reward;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.*;

public class Quests {

    // START: Static fields/functions
    public static final @NotNull Map<String, Quest> QUESTS = new HashMap<>();

    /**
     * Accesses and reads all files inside directory "quests".<br>
     * If the folder does not exist, one will be created with default-quest.yml inside
     * before the reading process starts.
     *
     * @param plugin this plugin, it is required to ensure the folder is correctly accessed
     *
     * @return a set contains non-duplicated quest files from "quests" folder. Set is empty if no file is found in the "quests" directory
     *
     * @see QuestFile
     */
    private static @NotNull Set<QuestFile> getQuestFiles( @NotNull JavaPlugin plugin ) {
        File questFolder = new File(plugin.getDataFolder(), "quests");
        if ( !questFolder.exists() )
            plugin.saveResource("quests", false);


        File[] files = questFolder.listFiles();
        if ( files == null ) {
            Log.warn("No quest found in \"quests\" folder!");
            return new HashSet<>(0);
        }

        Set<QuestFile> questFiles = new HashSet<>(files.length);
        for ( File file : files )
            try {
                questFiles.add(new QuestFile(plugin, file.getName()));
            } catch ( InvalidValueTypeException | MissingKeyException e ) {
                Log.wexception("Failed to load " + file.getName(), e);
            }

        return questFiles;
    }

    private static @Nullable Enum<?> getObjectiveType( @NotNull QuestType questType, @NotNull String value ) {
        if ( questType == QuestType.KILL_ENTITY )
            return EntityType.valueOf(value);

        return null;
    }

    /**
     * Creates a {@link Quest} based on the provided {@link QuestFile}.
     *
     * @param file contains components to create a {@link Quest}
     *
     * @return an instance of {@link Quest}
     *
     * @throws IllegalArgumentException if the value of "quest.type" or "quest.objective" is invalid
     */
    private static @NotNull Quest create( @NotNull QuestFile file ) {
        String typeString = file.getString("quest.type");
        QuestType type = QuestType.valueOf(typeString);

        String objectiveString = file.getString("quest.objective");
        Enum<?> objectiveType = getObjectiveType(type, objectiveString);
        if ( objectiveType == null )
            throw new IllegalArgumentException(objectiveString + " is not a valid quest type!");

        int amount = file.getInt("quest.amount");

        Quest quest = new Quest(
                file.getString("id"),
                file.getString("name"),
                file.getString("description"),
                type,
                new QuestObjective(objectiveType, amount)
        );

        List<String> rewardString = file.getStringList("rewards.items");
        Reward reward = Reward.from(rewardString);
        quest.addReward(reward);

        return quest;
    }

    public static void loadQuests( @NotNull JavaPlugin plugin ) {
        for ( QuestFile file : getQuestFiles(plugin) ) {
            Log.info("Loading " + file.getFileName());

            try {
                Quest quest = create(file);
                QUESTS.put(quest.getId(), quest);

                Log.info("Loaded quest: " + quest.getName());
            } catch ( IllegalArgumentException e ) {
                Log.wexception("Failed to create quest from file " + file.getFileName(), e);
            }
        }
    }
// END: Static fields/functions
}
