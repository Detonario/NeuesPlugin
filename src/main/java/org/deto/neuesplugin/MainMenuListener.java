package org.deto.neuesplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class MainMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (player.hasMetadata("Page1") && event.getCurrentItem() != null && event.getCurrentItem().isSimilar(MainMenuMethods.getButtonNr(2))) {
            player.closeInventory();
            MainMenuMethods.onPlayerSelectPage2(player);
        }

        if (player.hasMetadata("Page2") && event.getCurrentItem() != null && event.getCurrentItem().isSimilar(MainMenuMethods.getButtonNr(1))) {
            player.closeInventory();
            MainMenuMethods.onPlayerSelectPage1(player);
        } else if (player.hasMetadata("Page2") && event.getCurrentItem() != null && event.getCurrentItem().isSimilar(MainMenuMethods.getButtonNr(3))) {
            player.closeInventory();
            MainMenuMethods.onPlayerSelectPage3(player);
        }

        if (player.hasMetadata("Page3") && event.getCurrentItem() != null && event.getCurrentItem().isSimilar(MainMenuMethods.getButtonNr(2))) {
            player.closeInventory();
            MainMenuMethods.onPlayerSelectPage2(player);
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


    }
}
