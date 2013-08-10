package com.ineentho.frameMarket;


import com.avaje.ebean.validation.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name="hb_home")
public class Shop {
    @Id
    private int id;

    @NotNull
    private double x;

    @NotNull
    private double y;

    @NotNull
    private double z;

    @NotNull
    private String world;

    @NotNull
    private String owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public void setLocation(Location location){
        world = location.getWorld().getName();

        x = location.getX();
        y = location.getY();
        z = location.getZ();
    }

    public Location getLocation(){
        World w = Bukkit.getServer().getWorld(world);
        return new Location(w, x, y, z);
    }
}
