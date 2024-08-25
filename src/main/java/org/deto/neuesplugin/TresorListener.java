package org.deto.neuesplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class TresorListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (player.hasMetadata("Tresor")) {
            if (event.getInventory().equals(TresorCommand.getInventory())) {
                int slot = event.getSlot();
                if (slot >= 18 && slot <= 26) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (player.hasMetadata("Tresor")) {
            player.removeMetadata("Tresor", NeuesPlugin.getInstance());
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (player.hasMetadata("Tresor")) {
            event.setCancelled(true);
        }
    }
}
