package org.deto.neuesplugin.runnable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.deto.neuesplugin.NeuesPlugin;

public class Board implements Runnable {

    private static final Board instance = new Board();

    private Board() {
    }


    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getScoreboard() != null && player.getScoreboard().getObjective(NeuesPlugin.getInstance().getName()) != null)
                updateScoreboard(player);
            else
                createNewScoreboard(player);
        }

    }


    private void createNewScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective(NeuesPlugin.getInstance().getName(), "yummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "YALLO MOBILE");


        objective.getScore(ChatColor.WHITE + "Zum Frühstück").setScore(4);
        objective.getScore(ChatColor.WHITE + "gibt es immer einen").setScore(3);
        objective.getScore(ChatColor.WHITE + "Proteinshake!").setScore(2);
        objective.getScore(ChatColor.WHITE + " ").setScore(1);
        //objective.getScore(ChatColor.WHITE + "Walked: 0cm").setScore(0);


        Team team1 = scoreboard.registerNewTeam("team1");
        String teamKey = ChatColor.GOLD.toString();

        team1.addEntry(teamKey);
        team1.setPrefix("Walked: ");
        team1.setSuffix("0cm");

        objective.getScore(teamKey).setScore(0);
        player.setScoreboard(scoreboard);
    }


    private void updateScoreboard(Player player) {
        Scoreboard scoreboard = player.getScoreboard();
        Team team1 = scoreboard.getTeam("team1");

        team1.setSuffix((player.getStatistic(Statistic.WALK_ONE_CM) + player.getStatistic(Statistic.SPRINT_ONE_CM)) + player.getStatistic(Statistic.SNEAK_TIME) + "cm");
    }

    public static Board getInstance() {
        return instance;
    }


}