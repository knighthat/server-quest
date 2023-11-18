package me.knighthat.plugin.reward;

import me.knighthat.plugin.logging.Log;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@FunctionalInterface
public interface Reward {

    // START: Static fields/functions

    /**
     * Parses a list of strings that follow for mat {@link Material}:{@link Integer}.<br>
     * If an invalid material is provided, the reward will throw a warning and skip the reward.<br>
     * If the amount is invalid or undefined, 1 is the default value and will be set for the reward.
     *
     * @param rewardStrings a collection of rewards in string format {@link Material}:{@link Integer}
     *
     * @return an instance of {@link Reward} that contains the instructions of how to give player(s) rewards
     */
    static @NotNull Reward from( @NotNull Collection<String> rewardStrings ) {
        List<ItemStack> rewards = new ArrayList<>();

        for ( String rewardStr : rewardStrings ) {

            Material material = Material.AIR;
            int amount = 1;

            String[] split = rewardStr.split(":", 1);
            try {
                material = Material.valueOf(split[0]);
                amount = Integer.parseInt(split[1]);
            } catch ( NumberFormatException e ) {
                Log.warn(split[1] + " is not a valid integer! Defaults to 1.");
            } catch ( IllegalArgumentException e ) {
                Log.warn("There's no material called " + split[0]);
            } catch ( IndexOutOfBoundsException ignored ) {
            }

            if ( material == Material.AIR )
                continue;

            rewards.add(new ItemStack(material, amount));
        }

        ItemStack[] items = rewards.toArray(ItemStack[]::new);
        return recipient -> recipient.getInventory().addItem(items);
    }
    // END: Static fields/functions

    void reward( @NotNull Player recipient );
}
