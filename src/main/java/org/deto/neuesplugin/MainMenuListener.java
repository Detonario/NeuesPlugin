package org.deto.neuesplugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class MainMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();


        if (player.hasMetadata("Page1")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getSlot() == 26) {
                MainMenuMethods.onPlayerSelectPage2(player);
            }
        }


        if (player.hasMetadata("Page2")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getSlot() == 18) {
                MainMenuMethods.onPlayerSelectPage1(player);
            } else if (event.getCurrentItem() != null && event.getSlot() == 26) {
                MainMenuMethods.onPlayerSelectPage3(player);
            } else if (event.getCurrentItem() != null && event.getSlot() == 11) {
                player.sendMessage("Klicke auf folgenden Link für Premium: " + ChatColor.AQUA + ChatColor.UNDERLINE + "https://www.youtube.com/@detonario");
            } else if (event.getCurrentItem() != null && event.getSlot() == 13) {
                player.getInventory().addItem(new ItemStack(Material.BAMBOO_BLOCK, 64));

            } else if (event.getCurrentItem() != null && event.getSlot() == 15) {
                player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
            }


        }


        if (player.hasMetadata("Page3")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getSlot() == 18) {
                MainMenuMethods.onPlayerSelectPage2(player);
            } else if (event.getCurrentItem() != null && event.getSlot() == 26) {
                MainMenuMethods.onPlayerSelectPage4(player);
            }
        }


        if (player.hasMetadata("Page4")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getSlot() == 18) {
                MainMenuMethods.onPlayerSelectPage3(player);
            } else if (event.getCurrentItem() != null && event.getSlot() == 26) {
                MainMenuMethods.onPlayerSelectPage5(player);
            }
        }


        if (player.hasMetadata("Page5")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getSlot() == 18) {
                MainMenuMethods.onPlayerSelectPage4(player);
            }
        }


    }


    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (player.hasMetadata("Page1")) {
            player.removeMetadata("Page1", NeuesPlugin.getInstance());
        }

        if (player.hasMetadata("Page2")) {
            player.removeMetadata("Page2", NeuesPlugin.getInstance());
        }

        if (player.hasMetadata("Page3")) {
            player.removeMetadata("Page3", NeuesPlugin.getInstance());
        }

        if (player.hasMetadata("Page4")) {
            player.removeMetadata("Page4", NeuesPlugin.getInstance());
        }

        if (player.hasMetadata("Page5")) {
            player.removeMetadata("Page5", NeuesPlugin.getInstance());
        }


    }
}