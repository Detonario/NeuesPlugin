package org.deto.neuesplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.deto.neuesplugin.command.*;
import org.deto.neuesplugin.listener.*;
import org.deto.neuesplugin.other.CowSettings;
import org.deto.neuesplugin.other.CustomRecipes;
import org.deto.neuesplugin.runnable.Board;
import org.deto.neuesplugin.runnable.ButterflyTask;
import org.deto.neuesplugin.runnable.LaserPointerTask;

import java.io.File;
import java.io.IOException;

public final class NeuesPlugin extends JavaPlugin {

    private BukkitTask task;
    private BukkitTask task2;
    private BukkitTask task3;

    private TresorCommand tresorCommand;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EntityListener(), this);
        getServer().getPluginManager().registerEvents(new GuiListener(), this);
        getServer().getPluginManager().registerEvents(new MainMenuListener(), this);
        getServer().getPluginManager().registerEvents(new TresorListener(), this);
        getServer().getPluginManager().registerEvents(new LaserPointerListener(), this);

        tresorCommand = TresorCommand.getInstance();

        getCommand("cow").setExecutor(new CowCommand());
        getCommand("minigame").setExecutor(MinigameCommand.getInstance());
        getCommand("butterfly").setExecutor(new ButterflyCommand());
        getCommand("displayentity").setExecutor(new DisplayEntityCommand());
        getCommand("customitem").setExecutor(new CustomItemCommand());
        getCommand("gui").setExecutor(new GuiCommand());
        getCommand("mainmenu").setExecutor(new MainMenuCommand());
        getCommand("tresor").setExecutor(tresorCommand);

        MinigameCommand.bereichSchuetzen();
        CowSettings.getInstance().load();
        CustomRecipes.register();

        task = getServer().getScheduler().runTaskTimer(this, ButterflyTask.getInstance(), 0, 1);
        task2 = getServer().getScheduler().runTaskTimer(this, Board.getInstance(), 0, 20);
        task3 = getServer().getScheduler().runTaskTimer(this, LaserPointerTask.getInstance(), 0, 1);


        new BukkitRunnable() {
            @Override
            public void run() {
                MinigameCommand.removeAllEntities();
            }
        }.runTaskTimer(this, 0, 1);

        File inventoryFile = new File(getDataFolder(), "inventory.yml");
        if (inventoryFile.exists()) {
            try {
                tresorCommand.loadInventory(inventoryFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    @Override
    public void onDisable() {
        if (task != null && !task.isCancelled()) {
            task.cancel();
        }

        if (task2 != null && !task2.isCancelled()) {
            task2.cancel();
        }

        if (task3 != null && !task3.isCancelled()) {
            task3.cancel();
        }


        File inventoryFile = new File(getDataFolder(), "inventory.yml");
        if (tresorCommand.getInventory() != null) {
            try {
                tresorCommand.saveInventory(tresorCommand.getInventory(), inventoryFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static NeuesPlugin getInstance() {
        return getPlugin(NeuesPlugin.class);
    }


}