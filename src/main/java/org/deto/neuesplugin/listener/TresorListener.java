package org.deto.neuesplugin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.deto.neuesplugin.NeuesPlugin;
import org.deto.neuesplugin.command.TresorCommand;

import java.util.Map;

public class TresorListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();

        if (player.hasMetadata("Tresor") && clickedInventory != null) {
            Inventory tresorInventory = TresorCommand.getInventory();

            if (clickedInventory.equals(tresorInventory)) {
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
        Inventory tresorInventory = TresorCommand.getInventory();

        if (player.hasMetadata("Tresor")) {
            for (Map.Entry<Integer, ItemStack> entry : event.getNewItems().entrySet()) {
                int slot = entry.getKey();
                Inventory draggedInventory = event.getInventory();

                if (draggedInventory.equals(tresorInventory) && slot >= 18 && slot <= 26) {
                    event.setCancelled(true);
                    break;
                }
            }
        }
    }
}