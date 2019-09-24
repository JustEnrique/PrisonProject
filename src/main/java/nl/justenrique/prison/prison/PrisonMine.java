package nl.justenrique.prison.prison;

import nl.justenrique.prison.player.rank.Rank;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enrique on 21-9-2019.
 * No part of this publication may be reproduced, distributed, or transmitted in any form or by any means.
 * Copyright © 2019 by Enrique
 */

public class PrisonMine {

    private Rank rank;
    private Material blockType;
    private Location startPoint;
    private Location endPoint;

    public PrisonMine(Rank rank, Location startPoint, Location endPoint) {
        this.rank = rank;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public List<Block> getMineVolume() {
        if (this.startPoint == null || this.endPoint == null) return null;
        List<Block> blockList = new ArrayList<Block>();
        for(int x = this.startPoint.getBlockX(); x <= this.endPoint.getBlockX(); x++) {
            for(int y = this.startPoint.getBlockY(); y <= this.endPoint.getBlockY(); y++) {
                for(int z = this.startPoint.getBlockZ(); z <= this.endPoint.getBlockZ(); z++) {
                    blockList.add(this.startPoint.getWorld().getBlockAt(new Location(this.startPoint.getWorld(), x, y, z)));
                }
            }
        }
        return blockList;
    }


    public boolean isInside(Location location) {
        return this.getMineVolume().contains(location.getBlock());
    }

    public void reset() {
        for(Block b : getMineVolume()) {
            b.setType(this.blockType);
        }
    }

    public void setBlockType(Material blockType) {
        this.blockType = blockType;
    }

    public Rank getRank() {
        return rank;
    }

    public Material getBlockType() {
        return blockType;
    }

    public Location getStartPoint() {
        return startPoint;
    }

    public Location getEndPoint() {
        return endPoint;
    }
}
