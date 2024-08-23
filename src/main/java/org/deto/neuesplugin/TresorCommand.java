package org.deto.neuesplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class TresorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Nur Spieler dürfen diesen Befehl verwenden.");

            return true;
        }

        Player player = (Player) sender;

        Inventory tresor = Bukkit.createInventory(player, 9 * 3, ChatColor.GOLD + "Tresor");

        
        return true;
    }
}
