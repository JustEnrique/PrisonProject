package nl.justenrique.prison.prison.commands;

import nl.justenrique.prison.Main;
import nl.justenrique.prison.player.perm.PlayerPermission;
import nl.justenrique.prison.prison.state.SettingState;
import nl.justenrique.prison.util.Lang;
import nl.justenrique.prison.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Enrique on 24-9-2019.
 * No part of this publication may be reproduced, distributed, or transmitted in any form or by any means.
 * Copyright © 2019 by Enrique
 */
public class CmdPrison implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Utils.color("&c&lOops! &7Invalid arguments."));
            return true;
        }
        if(args[0].equalsIgnoreCase("addmine")) {
            if(!(sender instanceof Player)) {
                Lang.ERROR_PLAYER_ONLY.sendMessage(sender);
                return true;
            }
            Player player = (Player) sender;
            /*if(!player.hasPermission(PlayerPermission.addmine)) {
                Lang.ERROR_INVALID_PERMISSION.sendMessage(player);
                return true;
            }*/
            Main.getInstance().getPrisonManager().getPlayerSetState().put(player, new SettingState(player));
            Lang.SUCCESS_ENTERED_SETTING_STATE.sendMessage(player);

        }
        return false;
    }
}
