package org.deto.neuesplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CustomRecipes {

    public static void register() {
        final ItemStack superPaper = new ItemStack(Material.PAPER);
        final ItemMeta superPaperMeta = superPaper.getItemMeta();
        superPaperMeta.setDisplayName(ChatColor.GOLD + "Super Paper");
        superPaperMeta.addEnchant(Enchantment.EFFICIENCY, 1, true);
        superPaperMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        superPaper.setItemMeta(superPaperMeta);

        ShapelessRecipe recipe0 = new ShapelessRecipe(new NamespacedKey(NeuesPlugin.getInstance(), "SuperPaper"), superPaper);
        recipe0.addIngredient(3, Material.BOOK);
        Bukkit.addRecipe(recipe0);
        

        final ItemStack dickSword = new ItemStack(Material.GOLD_INGOT);
        final ItemMeta dickSwordMeta = dickSword.getItemMeta();
        dickSwordMeta.setDisplayName(ChatColor.WHITE + "Dick Gold");
        dickSwordMeta.setLore(Arrays.asList("", ChatColor.GRAY + "Dig more!"));
        dickSword.setItemMeta(dickSwordMeta);

        FurnaceRecipe recipe1 = new FurnaceRecipe(new NamespacedKey(NeuesPlugin.getInstance(), "DickSwordRecipe"),
                dickSword, new RecipeChoice.ExactChoice(superPaper), 100, 20);
        Bukkit.addRecipe(recipe1);


        final ItemStack laserPointer = new ItemStack(Material.NETHER_STAR);
        final ItemMeta laserPointerMeta = laserPointer.getItemMeta();
        laserPointerMeta.setDisplayName(ChatColor.WHITE + "Laser Pointer");
        laserPointer.setItemMeta(laserPointerMeta);

        final ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(NeuesPlugin.getInstance(), "LaserPointerRecipe"), laserPointer);
        recipe2.shape(" D ", "DBD", " D ");

        recipe2.setIngredient('D', dickSword);
        recipe2.setIngredient('B', new ItemStack(Material.BOOK));
        Bukkit.addRecipe(recipe2);

    }
}
