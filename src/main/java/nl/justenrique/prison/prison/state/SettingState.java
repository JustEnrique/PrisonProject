package nl.justenrique.prison.prison.state;

import nl.justenrique.prison.Main;
import nl.justenrique.prison.player.rank.Rank;
import nl.justenrique.prison.prison.PrisonMine;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Created by Enrique on 24-9-2019.
 * No part of this publication may be reproduced, distributed, or transmitted in any form or by any means.
 * Copyright © 2019 by Enrique
 */
public class SettingState {

    private Player player;
    private Location cornerOne;
    private Location cornerTwo;
    private StateType state;
    private Rank rank;
    private Material material;

    public SettingState(Player player) {
        this.state = StateType.SETTING_ONE;
        this.player = player;
    }

    public void finish() {
        Main.getInstance().getPrisonManager().getCachedPrisons().put(this.rank, new PrisonMine(this.rank, this.cornerOne, this.cornerTwo));
    }

    public void setState(StateType state) {
        this.state = state;
    }

    public StateType getState() {
        return state;
    }

    public void setCornerOne(Location cornerOne) {
        this.cornerOne = cornerOne;
    }

    public void setCornerTwo(Location cornerTwo) {
        this.cornerTwo = cornerTwo;
    }

    public Location getCornerTwo() {
        return cornerTwo;
    }

    public Material getMaterial() {
        return material;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

}
