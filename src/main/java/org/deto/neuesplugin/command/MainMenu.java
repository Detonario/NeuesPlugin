package org.deto.neuesplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.deto.neuesplugin.MainMenuMethods;
import org.jetbrains.annotations.NotNull;

public class MainMenu implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Nur Spieler dürfen diesen Befehl verwenden.");

            return true;
        }

        Player player = (Player) sender;

        MainMenuMethods.ausnahme(player);


        return true;
    }
}