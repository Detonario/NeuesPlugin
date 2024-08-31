package org.deto.neuesplugin.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.deto.neuesplugin.NeuesPlugin;
import org.jetbrains.annotations.NotNull;

public class MainMenuCommand implements CommandExecutor {

    private static final ItemStack pageButton1;
    private static final ItemStack pageButton2;
    private static final ItemStack pageButton3;
    private static final ItemStack pageButton4;
    private static final ItemStack pageButton5;

    private static final ItemStack getPremiumButton;
    private static final ItemStack get64Blocks;
    private static final ItemStack getPig;

    private static Inventory inventory1;
    private static Inventory inventory2;
    private static Inventory inventory3;
    private static Inventory inventory4;
    private static Inventory inventory5;


    static {
        pageButton1 = new ItemStack(Material.ARROW);
        ItemMeta meta1 = pageButton1.getItemMeta();
        meta1.setDisplayName(ChatColor.GRAY + "Seite 1");
        pageButton1.setItemMeta(meta1);

        pageButton2 = new ItemStack(Material.ARROW);
        ItemMeta meta2 = pageButton2.getItemMeta();
        meta2.setDisplayName(ChatColor.GRAY + "Seite 2");
        pageButton2.setItemMeta(meta2);

        pageButton3 = new ItemStack(Material.ARROW);
        ItemMeta meta3 = pageButton3.getItemMeta();
        meta3.setDisplayName(ChatColor.GRAY + "Seite 3");
        pageButton3.setItemMeta(meta3);

        pageButton4 = new ItemStack(Material.ARROW);
        ItemMeta meta4 = pageButton4.getItemMeta();
        meta4.setDisplayName(ChatColor.GRAY + "Seite 4");
        pageButton4.setItemMeta(meta4);

        pageButton5 = new ItemStack(Material.ARROW);
        ItemMeta meta5 = pageButton5.getItemMeta();
        meta5.setDisplayName(ChatColor.GRAY + "Seite 5");
        pageButton5.setItemMeta(meta5);


        getPremiumButton = new ItemStack(Material.DIAMOND);
        ItemMeta diamondMeta = getPremiumButton.getItemMeta();
        diamondMeta.setDisplayName(ChatColor.AQUA + "Premium-Mitgliedschaft");
        getPremiumButton.setItemMeta(diamondMeta);

        get64Blocks = new ItemStack(Material.BAMBOO_BLOCK);
        ItemMeta blocksMeta = get64Blocks.getItemMeta();
        blocksMeta.setDisplayName(ChatColor.DARK_GREEN + "Erhalte 64 Bamboo-Blöcke");
        get64Blocks.setItemMeta(blocksMeta);

        getPig = new ItemStack(Material.PIG_SPAWN_EGG);
        ItemMeta pigMeta = getPig.getItemMeta();
        pigMeta.setDisplayName(ChatColor.DARK_PURPLE + "Spawne ein Schwein");
        getPig.setItemMeta(pigMeta);
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Nur Spieler dürfen diesen Befehl verwenden.");

            return true;
        }

        Player player = (Player) sender;

        ausnahme(player);


        return true;
    }


    public static void ausnahme(Player player) {
        inventory1 = Bukkit.createInventory(player, 9 * 3, ChatColor.DARK_BLUE + "Menü (Seite 1)");
        inventory2 = Bukkit.createInventory(player, 9 * 3, ChatColor.DARK_BLUE + "Menü (Seite 2)");
        inventory3 = Bukkit.createInventory(player, 9 * 3, ChatColor.DARK_BLUE + "Menü (Seite 3)");
        inventory4 = Bukkit.createInventory(player, 9 * 3, ChatColor.DARK_BLUE + "Menü (Seite 4)");
        inventory5 = Bukkit.createInventory(player, 9 * 3, ChatColor.DARK_BLUE + "Menü (Seite 5)");

        onPlayerSelectPage1(player);
    }

    public static void onPlayerSelectPage1(Player player) {
        inventory2.clear();

        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);

        for (int i = 0; i < inventory1.getSize(); i++) {

            if (i != 26) {
                inventory1.setItem(i, pane);
            } else {
                inventory1.setItem(i, pageButton2);
            }
        }

        player.openInventory(inventory1);
        player.setMetadata("Page1", new FixedMetadataValue(NeuesPlugin.getInstance(), true));
    }

    public static void onPlayerSelectPage2(Player player) {
        inventory1.clear();
        inventory3.clear();

        inventory2.setItem(11, getPremiumButton);
        inventory2.setItem(13, get64Blocks);
        inventory2.setItem(15, getPig);


        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);

        for (int i = 0; i < inventory2.getSize(); i++) {

            if (i == 18) {
                inventory2.setItem(i, pageButton1);
            }

            if (i == 26) {
                inventory2.setItem(i, pageButton3);
            }

            if (i == 11) {
                inventory2.setItem(i, getPremiumButton);
            }

            if (i == 13) {
                inventory2.setItem(i, get64Blocks);
            }

            if (i == 15) {
                inventory2.setItem(i, getPig);
            }

            if (i != 18 && i != 26 && i != 11 && i != 13 && i != 15) {
                inventory2.setItem(i, pane);
            }

        }

        player.openInventory(inventory2);
        player.setMetadata("Page2", new FixedMetadataValue(NeuesPlugin.getInstance(), true));
    }

    public static void onPlayerSelectPage3(Player player) {
        inventory2.clear();
        inventory4.clear();

        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);

        for (int i = 0; i < inventory3.getSize(); i++) {

            if (i == 18) {
                inventory3.setItem(i, pageButton2);
            } else if (i == 26) {
                inventory3.setItem(i, pageButton4);
            } else {
                inventory3.setItem(i, pane);
            }
        }

        player.openInventory(inventory3);
        player.setMetadata("Page3", new FixedMetadataValue(NeuesPlugin.getInstance(), true));
    }


    public static void onPlayerSelectPage4(Player player) {
        inventory3.clear();
        inventory5.clear();

        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);

        for (int i = 0; i < inventory4.getSize(); i++) {

            if (i == 18) {
                inventory4.setItem(i, pageButton3);
            } else if (i == 26) {
                inventory4.setItem(i, pageButton5);
            } else {
                inventory4.setItem(i, pane);
            }
        }

        player.openInventory(inventory4);
        player.setMetadata("Page4", new FixedMetadataValue(NeuesPlugin.getInstance(), true));
    }

    public static void onPlayerSelectPage5(Player player) {
        inventory4.clear();

        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);

        for (int i = 0; i < inventory5.getSize(); i++) {

            if (i == 18) {
                inventory5.setItem(i, pageButton4);
            } else {
                inventory5.setItem(i, pane);
            }
        }

        player.openInventory(inventory5);
        player.setMetadata("Page5", new FixedMetadataValue(NeuesPlugin.getInstance(), true));
    }
}