package nl.justenrique.prison.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

/**
 * Created by Enrique on 24-9-2019.
 * No part of this publication may be reproduced, distributed, or transmitted in any form or by any means.
 * Copyright © 2019 by Enrique
 */
public class LocationSerializer {
    public static String serialize(Location loc) {
        return loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getWorld().getUID();
    }


    public static Location deserialize(String s) {
        String [] parts = s.split(";");
        double x = Double.parseDouble(parts[0]);
        double y = Double.parseDouble(parts[1]);
        double z = Double.parseDouble(parts[2]);
        UUID u = UUID.fromString(parts[3]);
        World w = Bukkit.getServer().getWorld(u);
        return new Location(w, x, y, z);
    }
}
