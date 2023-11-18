package me.knighthat.plugin.quest;

import lombok.Data;
import me.knighthat.plugin.reward.Reward;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class Quest {

    {
        rewards = new ArrayList<>();
    }

    private final @NotNull String         id;
    private final @NotNull String         name;
    private final @NotNull String         description;
    private final @NotNull QuestType      type;
    private final @NotNull QuestObjective objective;
    private final @NotNull List<Reward>   rewards;

    public Quest( @NotNull String id, @NotNull String name, @NotNull String description, @NotNull QuestType type, @NotNull QuestObjective objective ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.objective = objective;
    }

    public void addReward( @NotNull Reward reward ) {this.rewards.add(reward);}
}
