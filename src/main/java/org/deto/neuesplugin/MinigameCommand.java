package org.deto.neuesplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static org.bukkit.Bukkit.getServer;

public class MinigameCommand implements CommandExecutor {

    private static final MinigameCommand instance = new MinigameCommand();

    private MinigameCommand() {
    }

    public static List<Location> geschuetzteBloecke = new ArrayList<>();
    public static Location minEcke;
    public static Location maxEcke;

    World world = Bukkit.getWorld("world");
    Location location = new Location(world, 495, 100, 495);

    World world2 = Bukkit.getWorld("world");
    Location mainHub = new Location(world2, 100, 75, 100);

    public static final Map<UUID, Zombie> zombieMap = new HashMap<>();

    public static Player player;

    private static boolean isMinigameRunning = false;


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (isMinigameRunning) {
            sender.sendMessage("Minigame läuft bereits.");
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("Du bist kein Spieler.");
            return true;
        }

        player = (Player) sender;

        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack iron_sword = new ItemStack(Material.IRON_SWORD);


        if (((Player) sender).getInventory().contains(sword)) {
            sender.sendMessage("Keine Diamantschwerter erlaubt!");
            return true;
        }


        player.getInventory().clear();
        player.getInventory().addItem(iron_sword);
        player.teleport(location);

        Bukkit.getWorld("world").setTime(14000);

        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            int x = random.nextInt(98) - 49;
            int y = random.nextInt(98) - 49;
            Location randomPosition = new Location(world, 500 + x, 100, 500 + y);
            Zombie zombie = (Zombie) player.getWorld().spawnEntity(randomPosition, EntityType.ZOMBIE);
            zombieMap.put(zombie.getUniqueId(), zombie);
            zombie.setTarget(player);
        }

        isMinigameRunning = true;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (zombieMap.isEmpty() && MinigameCommand.player != null) {
                    player.sendMessage("Du hast gewonnen!");
                    player.teleport(mainHub);
                    setBoolean(false);
                    cancel();
                }
            }
        }.runTaskTimer(NeuesPlugin.getInstance(), 60, 1);


        return true;
    }


    public static void removeAllEntities() {

        World world = minEcke.getWorld();
        List<Entity> entities = world.getEntities();

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity && !(entity instanceof Player)) {
                UUID entityId = entity.getUniqueId();
                if (!zombieMap.containsKey(entityId)) {
                    Location entityLocation = entity.getLocation();
                    int x = entityLocation.getBlockX();
                    int y = entityLocation.getBlockY();
                    int z = entityLocation.getBlockZ();

                    if (x >= minEcke.getBlockX() && x <= maxEcke.getBlockX() &&
                            y >= minEcke.getBlockY() && y <= maxEcke.getBlockY() &&
                            z >= minEcke.getBlockZ() && z <= maxEcke.getBlockZ()) {
                        entity.remove();

                    }
                }
            }
        }





        /*zombieMap.entrySet().removeIf(entry -> {
            Entity entity = Bukkit.getEntity(entry.getKey());
            if (entity == null || !entity.isValid()) {
                return true; // Entferne den Eintrag aus der Map
            }
            return false; // Behalte den Eintrag
        });

        // Wenn keine Zombies mehr übrig sind, setze das Minigame zurück
        if (zombieMap.isEmpty() && player != null) {
            player.sendMessage("GEWINNER!!!");
            System.out.println("WIN WIN WIN WIN WIN WIN");
            isMinigameRunning = false;
        }*/
    }


    public static void bereichSchuetzen() {
        org.deto.neuesplugin.MinigameCommand.minEcke = new Location(getServer().getWorld("world"), 450, 99, 450);
        org.deto.neuesplugin.MinigameCommand.maxEcke = new Location(getServer().getWorld("world"), 550, 116, 550);

        for (int x = org.deto.neuesplugin.MinigameCommand.minEcke.getBlockX(); x <= org.deto.neuesplugin.MinigameCommand.maxEcke.getBlockX(); x++) {
            for (int y = org.deto.neuesplugin.MinigameCommand.minEcke.getBlockY(); y <= org.deto.neuesplugin.MinigameCommand.maxEcke.getBlockY(); y++) {
                for (int z = org.deto.neuesplugin.MinigameCommand.minEcke.getBlockZ(); z <= org.deto.neuesplugin.MinigameCommand.maxEcke.getBlockZ(); z++) {
                    org.deto.neuesplugin.MinigameCommand.geschuetzteBloecke.add(new Location(getServer().getWorld("world"), x, y, z));
                }
            }
        }
    }


    /*public static boolean istSpielerImBereich() {
        Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
        for (Player player : players) {
            Location loc = player.getLocation();
            if (loc.getWorld().equals(minEcke.getWorld()) &&
                    loc.getBlockX() >= minEcke.getBlockX() && loc.getBlockX() <= maxEcke.getBlockX() &&
                    loc.getBlockY() >= minEcke.getBlockY() && loc.getBlockY() <= maxEcke.getBlockY() &&
                    loc.getBlockZ() >= minEcke.getBlockZ() && loc.getBlockZ() <= maxEcke.getBlockZ()) {
                return true;
            }
        }

        return false;

    }*/


    public static boolean istGeschuetzterBlock(Location location) {
        return geschuetzteBloecke.contains(location);
    }


    public static MinigameCommand getInstance() {
        return instance;
    }


    public static void setBoolean(boolean running) {
        isMinigameRunning = running;
    }


}