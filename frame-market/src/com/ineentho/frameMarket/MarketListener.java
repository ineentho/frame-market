package com.ineentho.frameMarket;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.painting.PaintingBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class MarketListener implements Listener {

    private Plugin plugin;

    public MarketListener(Plugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(HangingBreakEvent e){

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){

    }
}
