package org.deto.neuesplugin;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.UUID;

public class EntityListener implements Listener {

    Player player;
    Entity entity;

    /*private Map<UUID, PermissionAttachment> permissions = new HashMap<>();*/

    @EventHandler
    public void onEntityRightClick(PlayerInteractEntityEvent event) {

        player = event.getPlayer();
        entity = event.getRightClicked();


        /*for (PermissionAttachmentInfo permission : player.getEffectivePermissions()) {
            PermissionAttachment attachment = permission.getAttachment();

            System.out.println("Permission: " + permission.getPermission() + " from " + (attachment == null ? "default" : attachment.getPlugin().getName()));

        }*/


        /*if (player.isFlying()) {
            player.addAttachment(NeuesPlugin.getInstance(), "neuesplugin.command.cow", true, 20 * 5);
        }*/


        /*if (permissions.containsKey(player.getUniqueId())) {
            PermissionAttachment permission = permissions.remove(player.getUniqueId());
            player.removeAttachment(permission);

            player.sendMessage("Du hast keine Berechtigung mehr. Soi!");


        } else {
            PermissionAttachment permission = player.addAttachment(NeuesPlugin.getInstance(), "funky.demo.test", true);
            permissions.put(player.getUniqueId(), permission);

            player.sendMessage("Jetzt hast du wieder Berechtigung!");
        }*/


        if (player.getItemInHand().getItemMeta() != null) {
            PersistentDataContainer entityContainer = entity.getPersistentDataContainer();
            PersistentDataContainer handItemContainer = player.getItemInHand().getItemMeta().getPersistentDataContainer();

            if (entity.getType() == CowSettings.getInstance().getExplodingType() && entityContainer.has(Keys.CUSTOM_COW) && handItemContainer.has(Keys.CUSTOM_BUCKET)) {
                /*if (!player.hasPermission("neuesplugin.command.cow")) {
                    player.sendMessage("You don't have permission to milk cows!");

                    return;
                }*/

                entity.getWorld().createExplosion(entity.getLocation(), 25);
            }

        }


    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (MinigameCommand.istGeschuetzterBlock(block.getLocation())) {
            event.setCancelled(true);
            player.sendMessage("Du darfst diesen Block nicht zerstören.");
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (MinigameCommand.istGeschuetzterBlock(block.getLocation())) {
            event.setCancelled(true);
            player.sendMessage("Du darfst diesen Block nicht platzieren.");
        }

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if (entity.getType() == EntityType.ZOMBIE) {
            Zombie zombie = (Zombie) entity;
            UUID zombieID = zombie.getUniqueId();

            MinigameCommand.zombieMap.remove(zombieID);
        }
    }


}