package org.deto.neuesplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MainMenu implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Nur Spieler dürfen diesen Befehl verwenden.");

            return true;
        }

        Player player = (Player) sender;

        MainMenuMethods.onPlayerSelectPage1(player);


        return true;
    }
}