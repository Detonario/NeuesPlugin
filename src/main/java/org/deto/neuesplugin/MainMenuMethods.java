package org.deto.neuesplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class MainMenuMethods {

    private static ItemStack pageButton1;
    private static ItemStack pageButton2;
    private static ItemStack pageButton3;

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
    }


    public static void onPlayerSelectPage1(Player player) {
        Inventory inventory1 = Bukkit.createInventory(player, 9 * 3, ChatColor.DARK_BLUE + "Menü (Seite 1)");

        inventory1.setItem(26, pageButton2);

        player.openInventory(inventory1);
        player.setMetadata("Page1", new FixedMetadataValue(NeuesPlugin.getInstance(), true));
    }

    public static void onPlayerSelectPage2(Player player) {
        Inventory inventory2 = Bukkit.createInventory(player, 9 * 3, ChatColor.DARK_BLUE + "Menü (Seite 2)");

        inventory2.setItem(18, pageButton1);
        inventory2.setItem(26, pageButton3);

        player.openInventory(inventory2);
        player.setMetadata("Page2", new FixedMetadataValue(NeuesPlugin.getInstance(), true));
    }

    public static void onPlayerSelectPage3(Player player) {
        Inventory inventory3 = Bukkit.createInventory(player, 9 * 3, ChatColor.DARK_BLUE + "Menü (Seite 3)");

        inventory3.setItem(18, pageButton2);

        player.openInventory(inventory3);
        player.setMetadata("Page3", new FixedMetadataValue(NeuesPlugin.getInstance(), true));
    }


    public static ItemStack getButtonNr(int number) {
        if (number == 1) {
            return pageButton1;
        } else if (number == 2) {
            return pageButton2;
        } else if (number == 3) {
            return pageButton3;
        } else {
            return null;
        }
    }


}