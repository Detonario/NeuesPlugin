package org.deto.neuesplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public final class NeuesPlugin extends JavaPlugin {

    private BukkitTask task;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EntityListener(), this);
        getServer().getPluginManager().registerEvents(new GuiListener(), this);

        getCommand("cow").setExecutor(new CowCommand());
        getCommand("dias").setExecutor(new GiveBlockCommand());
        getCommand("minigame").setExecutor(MinigameCommand.getInstance());
        getCommand("butterfly").setExecutor(new ButterflyCommand());
        getCommand("displayentity").setExecutor(new DisplayEntityCommand());
        getCommand("customitem").setExecutor(new CustomItemCommand());
        getCommand("gui").setExecutor(new GuiCommand());

        MinigameCommand.bereichSchuetzen();
        CowSettings.getInstance().load();

        task = getServer().getScheduler().runTaskTimer(this, ButterflyTask.getInstance(), 0, 1);


        new BukkitRunnable() {
            @Override
            public void run() {
                MinigameCommand.removeAllEntities();
            }
        }.runTaskTimer(this, 0, 1);
    }


    @Override
    public void onDisable() {
        if (task != null && !task.isCancelled()) {
            task.cancel();
        }
    }

    public static NeuesPlugin getInstance() {
        return getPlugin(NeuesPlugin.class);
    }


    /*@EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getHand() == EquipmentSlot.HAND && event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (player.getInventory().getItemInMainHand().getType() == Material.STICK) {
                MinigameCommand.removeAllEntities();
                player.sendMessage("Alle Entitäten im Bereich wurden entfernt.");
            }
        }
    }*/


    /*@EventHandler
    public void onPlayerJoin(PlayerJoinEvent doener) {
        for (Player onlineP : Bukkit.getServer().getOnlinePlayers()) {
            onlineP.sendMessage("MOIN!");
        }
    }*/


    /*@EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.STONE) {
            event.getPlayer().sendMessage("Du hast Stein abgebaut.");
        }
    }*/

    /*@EventHandler
    public void onBlockBreak2(BlockBreakEvent event2) {
        if (event2.getBlock().getType() == Material.DIAMOND_ORE) {
            event2.getPlayer().sendMessage("Du hast Diamanterz abgebaut.");
            event2.setExpToDrop(1000);
        }
    }*/

    /*@EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        if (event.getEntered() instanceof Player) {
            Player player = (Player) event.getEntered();
            if (player.getInventory().contains(Material.DIAMOND_AXE)) {
                player.sendMessage("Leider..");
                event.setCancelled(true);
                player.getInventory().clear();
            }
        }
    }*/

    /*@EventHandler
    public void checkAllPlayersForDiamondAxe(VehicleEnterEvent event) {
        if (event.getEntered() instanceof Player) {
            Player player = (Player) event.getEntered();
            Inventory inventory = player.getInventory();

            if (inventory.contains(Material.DIAMOND_AXE)) {
                player.sendMessage("Leider..");
                event.setCancelled(true);  // Verhindert das Einsteigen in das Fahrzeug
            }
        }


    }*/

}