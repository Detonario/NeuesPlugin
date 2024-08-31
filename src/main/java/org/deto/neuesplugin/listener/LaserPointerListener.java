package org.deto.neuesplugin.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;

public class LaserPointerListener implements Listener {

    @EventHandler
    public void onClick(final PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND || event.getAction() != Action.RIGHT_CLICK_AIR)
            return;

        Player player = event.getPlayer();
        ItemStack hand = player.getItemInHand();
        int distance = 100;

        if (hand.hasItemMeta() && hand.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Laser Pointer")) {
            RayTraceResult result = player.rayTraceBlocks(distance);

            if (result != null && result.getHitBlock() != null && result.getHitBlock().isSolid())
                player.getWorld().createExplosion(result.getHitBlock().getLocation(), 5F, true, true);
            else
                player.sendMessage(ChatColor.LIGHT_PURPLE + "[Laser]" + ChatColor.WHITE + " Ziel ist zu weit oder kein gültiger Block!");
        }
    }

}
