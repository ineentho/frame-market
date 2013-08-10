package com.ineentho.frameMarket;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class MarketListener implements Listener {

    private Plugin plugin;

    public MarketListener(Plugin plugin){
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(HangingBreakEvent e){
        if(e.getEntity().getType() == EntityType.ITEM_FRAME){
            Shop shop = getShop(e.getEntity().getLocation());
            if(shop != null){
                plugin.getLogger().info("Breaking frame market");
                plugin.getDatabase().delete(shop);

                e.setCancelled(true);
                e.getEntity().remove();

                ItemStack marketItem = new ItemStack(Material.ITEM_FRAME);
                ItemMeta meta = marketItem.getItemMeta();
                meta.setDisplayName("Frame Market");
                marketItem.setItemMeta(meta);

                e.getEntity().getLocation().getWorld().dropItem(e.getEntity().getLocation(), marketItem);
            }
        }
    }

    @EventHandler
    public void onFrameInteract(PlayerInteractEntityEvent e){
       if(e.getRightClicked().getType() == EntityType.ITEM_FRAME){
           ItemFrame frame = (ItemFrame) e.getRightClicked();
           frame.setItem(e.getPlayer().getItemInHand());
           e.setCancelled(true);
       }
    }

    @EventHandler
    public void onBlockPlace(HangingPlaceEvent e){
        ItemStack itemInHand = e.getPlayer().getItemInHand();
        if(itemInHand.hasItemMeta() && itemInHand.getItemMeta().getDisplayName().equals("Frame Market")){

            Location loc = e.getEntity().getLocation();

            Shop shop = getShop(loc);

            plugin.getLogger().info("Placing frame market");
            if(shop == null){
                shop = new Shop();
                shop.setLocation(e.getEntity().getLocation());
            }

            shop.setOwner(e.getPlayer().getName());

            plugin.getDatabase().save(shop);
        }
    }

    private Shop getShop(Location loc){
        return plugin.getDatabase().find(Shop.class).where()
                .ieq("x", String.valueOf(loc.getX()))
                .ieq("y", String.valueOf(loc.getY()))
                .ieq("z", String.valueOf(loc.getZ())).findUnique();
    }
}
