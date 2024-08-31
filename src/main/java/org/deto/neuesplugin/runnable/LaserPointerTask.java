package org.deto.neuesplugin.runnable;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class LaserPointerTask implements Runnable {

    private static final LaserPointerTask instance = new LaserPointerTask();

    private LaserPointerTask() {
    }

    @Override
    public void run() {
        int length = 20;
        double particleDistance = 2;

        for (Player online : Bukkit.getOnlinePlayers()) {
            ItemStack hand = online.getItemInHand();

            if (hand.hasItemMeta() && hand.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Laser Pointer")) {
                Location location = online.getLocation().add(0, 1, 0);

                for (double waypoint = 1; waypoint < length; waypoint += particleDistance) {

                    Vector vector = location.getDirection().multiply(waypoint);
                    location.add(vector);

                    if (location.getBlock().getType() != Material.AIR)
                        break;

                    try {
                        Particle particle;

                        try {
                            particle = Particle.DUST;

                        } catch (final Throwable t) { // Spigot 1.20.5+ changed names
                            particle = Particle.valueOf("REDSTONE");
                        }

                        location.getWorld().spawnParticle(particle, location, 1, new Particle.DustOptions(Color.YELLOW, 0.75F));

                    } catch (final Throwable t) {
                        // Unsupported
                    }

                }

            }
        }
    }


    public static LaserPointerTask getInstance() {
        return instance;
    }
}
