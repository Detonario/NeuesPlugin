package org.deto.neuesplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TresorCommand implements CommandExecutor {

    private static final TresorCommand instance = new TresorCommand();
    private static Inventory tresor;

    private TresorCommand() {
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Nur Spieler dürfen diesen Befehl verwenden.");

            return true;
        }

        Player player = (Player) sender;

        player.getInventory();

        if (tresor == null) {
            tresor = Bukkit.createInventory(player, 9 * 3, ChatColor.GOLD + "Tresor");
        }

        player.openInventory(tresor);
        player.setMetadata("Tresor", new FixedMetadataValue(NeuesPlugin.getInstance(), true));


        return true;
    }


    public void saveInventory(Inventory inventory, File file) throws IOException {
        YamlConfiguration config = new YamlConfiguration();
        config.set("inventory", inventory.getContents());
        config.save(file);
    }

    public Inventory loadInventory(File file) throws IOException {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        List<ItemStack> itemList = (List<ItemStack>) config.get("inventory");
        ItemStack[] items = itemList != null ? itemList.toArray(new ItemStack[0]) : new ItemStack[0];

        tresor = Bukkit.createInventory(null, 9 * 3, ChatColor.GOLD + "Tresor");
        tresor.setContents(items);
        return tresor;
    }

    public static Inventory getInventory() {
        return tresor;
    }

    public static TresorCommand getInstance() {
        return instance;
    }
}
