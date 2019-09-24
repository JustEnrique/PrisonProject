package nl.justenrique.prison.player;

import nl.justenrique.prison.Main;
import nl.justenrique.prison.player.rank.Rank;
import nl.justenrique.prison.util.Utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Enrique on 22-9-2019.
 * No part of this publication may be reproduced, distributed, or transmitted in any form or by any means.
 * Copyright © 2019 by Enrique
 */
public class PlayerManager {

    private HashMap<UUID, PrisonPlayer> cachedPlayers = new HashMap<UUID, PrisonPlayer>();

    public void saveFile() {
        try {
            Main.getInstance().getPlayerConfig().save(Main.getInstance().getPlayerYml());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    players:
      player1-uuid:
        name: Enrique
        rank: a
        prestige
        balance: 5000.0
     */

    public void load() {
        if (Main.getInstance().getPlayerConfig().getConfigurationSection("players") == null) return;
        FileConfiguration fileConfiguration = Main.getInstance().getPlayerConfig();
        for (String s : fileConfiguration.getConfigurationSection("players").getKeys(false)) {
            this.cachedPlayers.put(UUID.fromString(s), new PrisonPlayer(
                    fileConfiguration.getString("players." + s + ".name"),
                    UUID.fromString(s),
                    Rank.valueOf(fileConfiguration.getString("players." + s + ".rank").toUpperCase()),
                    fileConfiguration.getInt("players." + s + ".prestige"),
                    fileConfiguration.getDouble("players." + s + ".balance")));
        }
    }

    public void save() {
        clear();
        for(UUID uuid : this.cachedPlayers.keySet()) {
            PrisonPlayer prisonPlayer = this.cachedPlayers.get(uuid);
            Main.getInstance().getPlayerConfig().set("players." + uuid.toString() + ".name", prisonPlayer.getName());
            Main.getInstance().getPlayerConfig().set("players." + uuid.toString() + ".rank", prisonPlayer.getRank().toString());
            Main.getInstance().getPlayerConfig().set("players." + uuid.toString() + ".prestige", prisonPlayer.getPrestige());
            Main.getInstance().getPlayerConfig().set("players." + uuid.toString() + ".balance", prisonPlayer.getBalance());
        }
        saveFile();
    }

    public void clear() {
        if(Main.getInstance().getPlayerConfig().getConfigurationSection("players") == null) return;
        for (String s : Main.getInstance().getPlayerConfig().getKeys(false)) {
            Main.getInstance().getPlayerConfig().set(s, null);
        }
    }

    public void register(Player player) {
        player.sendMessage(Utils.color("&a&l✔ &7Registered your data, have fun!"));
        this.cachedPlayers.put(player.getUniqueId(), new PrisonPlayer(player.getName(), player.getUniqueId(), Rank.A, 0, 5000.0));
    }

    public HashMap<UUID, PrisonPlayer> getCachedPlayers() {
        return cachedPlayers;
    }
}
