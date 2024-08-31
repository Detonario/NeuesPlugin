package org.deto.neuesplugin.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GiveBlockCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        ItemStack item123 = new ItemStack(Material.DIAMOND_BLOCK, 4);
        player.getInventory().addItem(item123);
        player.sendMessage("Du hast Diamantblock erhalten");


        return true;
    }
}