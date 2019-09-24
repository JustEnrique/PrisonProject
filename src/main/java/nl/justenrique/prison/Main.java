package nl.justenrique.prison;

import nl.justenrique.prison.player.PlayerManager;
import nl.justenrique.prison.player.listener.PlayerListener;
import nl.justenrique.prison.prison.PrisonMine;
import nl.justenrique.prison.prison.commands.CmdPrison;
import nl.justenrique.prison.prison.manager.PrisonManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by Enrique on 22-9-2019.
 * No part of this publication may be reproduced, distributed, or transmitted in any form or by any means.
 * Copyright © 2019 by Enrique
 */
public class Main extends JavaPlugin {

    public static Main instance;

    private PlayerManager playerManager;
    private PrisonManager prisonManager;

    private File playerConfig;
    private File mineConfig;
    private FileConfiguration playerFileConfiguration;
    private FileConfiguration mineFileConfiguration;

    @Override
    public void onEnable() {
        instance = this;
        playerManager = new PlayerManager();
        prisonManager = new PrisonManager();
        loadFiles();
        load();
        getCommand("prison").setExecutor(new CmdPrison());
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        save();
    }

    public void save() {
        getPlayerManager().save();
        getPrisonManager().save();
    }

    public void load() {
        getPlayerManager().load();
        getPrisonManager().load();
    }

    public void loadFiles() {
        if(!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
        mineConfig = new File(getDataFolder(), "mines.yml");
        if(this.mineConfig.exists()) {
            try {
                mineConfig.createNewFile();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        mineFileConfiguration = YamlConfiguration.loadConfiguration(mineConfig);

        playerConfig = new File(getDataFolder(), "players.yml");
        if(!this.playerConfig.exists()) {
            try {
                playerConfig.createNewFile();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        playerFileConfiguration = YamlConfiguration.loadConfiguration(playerConfig);
    }

    public static Main getInstance() {
        return instance;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public File getPlayerYml() {
        return playerConfig;
    }

    public FileConfiguration getPlayerConfig() {
        return playerFileConfiguration;
    }

    public PrisonManager getPrisonManager() {
        return prisonManager;
    }

    public File getMineConfigFile() {
        return mineConfig;
    }

    public FileConfiguration getMineConfig() {
        return mineFileConfiguration;
    }
}
