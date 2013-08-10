package com.ineentho.frameMarket;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class FrameMarket extends JavaPlugin {
    public void onEnable(){
        getLogger().info("Frame Market Enabled");

        ItemStack marketItem = new ItemStack(Material.ITEM_FRAME);
        ItemMeta meta = marketItem.getItemMeta();
        meta.setDisplayName("Frame Market");
        marketItem.setItemMeta(meta);
        ShapelessRecipe marketRecipe = new ShapelessRecipe(marketItem);
        marketRecipe.addIngredient(Material.ITEM_FRAME);
        marketRecipe.addIngredient(Material.CHEST);
        getServer().addRecipe(marketRecipe);

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new MarketListener(this), this);

        setupDatabase();
    }

    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        list.add(Shop.class);
        return list;
    }

    private void setupDatabase() {
        try {
            getDatabase().find(Shop.class).findRowCount();
        } catch (PersistenceException ex) {
            getLogger().info("Installing database..");
            installDDL();
        }
    }

    public void onDisable(){
        getLogger().info("Frame Market Disabled");
    }
}