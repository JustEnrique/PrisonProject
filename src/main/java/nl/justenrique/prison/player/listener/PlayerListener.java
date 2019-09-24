package nl.justenrique.prison.player.listener;

import nl.justenrique.prison.Main;
import nl.justenrique.prison.player.rank.Rank;
import nl.justenrique.prison.prison.state.SettingState;
import nl.justenrique.prison.prison.state.StateType;
import nl.justenrique.prison.util.Lang;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Enrique on 23-9-2019.
 * No part of this publication may be reproduced, distributed, or transmitted in any form or by any means.
 * Copyright © 2019 by Enrique
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (Main.getInstance().getPlayerManager().getCachedPlayers().containsKey(event.getPlayer().getUniqueId()))
            return;
        Main.getInstance().getPlayerManager().register(event.getPlayer());
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (!Main.getInstance().getPrisonManager().getPlayerSetState().containsKey(event.getPlayer())) return;
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            SettingState settingState = Main.getInstance().getPrisonManager().getPlayerSetState().get(event.getPlayer());
            switch (settingState.getState()) {
                case SETTING_ONE:
                    settingState.setCornerOne(event.getClickedBlock().getLocation());
                    Lang.SUCCESS_SET_FIRST.sendMessage(event.getPlayer());
                    settingState.setState(StateType.SETTING_TWO);
                    return;
                case SETTING_TWO:
                    if (event.getClickedBlock().getLocation().equals(settingState.getCornerTwo())) {
                        Lang.ERROR_INVALID_MINE_SIZE.sendMessage(event.getPlayer());
                        return;
                    }
                    settingState.setCornerTwo(event.getClickedBlock().getLocation());
                    Lang.LOCATIONS_SET.sendMessage(event.getPlayer());
                    settingState.setState(StateType.SETTING_RANK);
                    return;
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (!Main.getInstance().getPrisonManager().getPlayerSetState().containsKey(event.getPlayer())) return;
        SettingState settingState = Main.getInstance().getPrisonManager().getPlayerSetState().get(event.getPlayer());
        if (settingState.getState().equals(StateType.SETTING_RANK)) {
            try {
                Rank rank = Rank.valueOf(event.getMessage().toUpperCase());
                settingState.setRank(rank);
                Lang.RANK_SET.sendMessage(event.getPlayer());
                settingState.finish();
                event.setCancelled(true);
            } catch (IllegalArgumentException e) {
                Lang.ERROR_INVALID_ARGUMENTS.sendMessage(event.getPlayer());
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        if (!Main.getInstance().getPrisonManager().getPlayerSetState().containsKey(event.getPlayer())) return;
        Main.getInstance().getPrisonManager().getPlayerSetState().remove(event.getPlayer());
    }

}
