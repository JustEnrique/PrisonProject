package nl.justenrique.prison.prison.manager;

import nl.justenrique.prison.Main;
import nl.justenrique.prison.player.rank.Rank;
import nl.justenrique.prison.prison.PrisonMine;
import nl.justenrique.prison.prison.state.SettingState;
import nl.justenrique.prison.util.LocationSerializer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Enrique on 24-9-2019.
 * No part of this publication may be reproduced, distributed, or transmitted in any form or by any means.
 * Copyright © 2019 by Enrique
 */

public class PrisonManager {

    private HashMap<Player, SettingState> playerSetState = new HashMap<Player, SettingState>();
    private HashMap<Rank, PrisonMine> cachedPrisons = new HashMap<Rank, PrisonMine>();

    /*
    mines:
      a:
        locationone: xxx
        locationtwo: xxx

     */

    public void load() {
        if(Main.getInstance().getMineConfig().getConfigurationSection("mines") == null) return;
        FileConfiguration fileConfiguration = Main.getInstance().getMineConfig();
        for(String s : fileConfiguration.getConfigurationSection("mines").getKeys(false)) {
            this.cachedPrisons.put(Rank.valueOf(s.toUpperCase()), new PrisonMine(Rank.valueOf(s),
                    LocationSerializer.deserialize(fileConfiguration.getString("mines." + s + ".corner-one")),
                    LocationSerializer.deserialize(fileConfiguration.getString("mines." + s + ".corner-two"))));
        }
    }

    public void save() {
        clear();
        for(PrisonMine prisonMine : this.cachedPrisons.values()) {
            Main.getInstance().getMineConfig().set("mines." + prisonMine.getRank().toString() + ".corner-one", LocationSerializer.serialize(prisonMine.getStartPoint()));
            Main.getInstance().getMineConfig().set("mines." + prisonMine.getRank().toString() + ".corner-two", LocationSerializer.serialize(prisonMine.getEndPoint()));
        }
        saveFile();
    }

    public void clear() {
        if(Main.getInstance().getMineConfig().getConfigurationSection("mines") == null) return;
        for (String s : Main.getInstance().getMineConfig().getKeys(false)) {
            Main.getInstance().getMineConfig().set(s, null);
        }
    }

    public void saveFile() {
        try {
            Main.getInstance().getMineConfig().save(Main.getInstance().getMineConfigFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Player, SettingState> getPlayerSetState() {
        return playerSetState;
    }

    public HashMap<Rank, PrisonMine> getCachedPrisons() {
        return cachedPrisons;
    }
}
