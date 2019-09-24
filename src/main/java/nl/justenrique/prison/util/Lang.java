package nl.justenrique.prison.util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Enrique on 24-9-2019.
 * No part of this publication may be reproduced, distributed, or transmitted in any form or by any means.
 * Copyright © 2019 by Enrique
 */
public enum Lang {

    //Error Messages
    ERROR_PLAYER_ONLY("&cError! &7Only players can execute this command."),
    ERROR_INVALID_PERMISSION("&c&lOops! &7You don't have the right permissions to perform that action."),
    ERROR_INVALID_ARGUMENTS("&c&lHmmm.. &7Looks like you've entered invalid arguments to that command."),
    ERROR_MINES_OVERLAP("&c&lError! &7A mine is already overlapping that region"),
    ERROR_MINE_EXISTS("&c&lError! &7The mine %s already exists"),
    ERROR_MINE_DOESNT_EXIST("&c&lError! &7That mine does not exist."),
    ERROR_INVALID_MINE_SIZE("&c&lError! &7The mine size that you've selected isn't valid."),

    //Success Messages
    SUCCES_MINE_DELETED("&a&lSuccess! &7You've deleted the mine %s."),

    SUCCESS_ENTERED_SETTING_STATE("&a&lOkay... &7You've entered the mine setting state, please left click on the block that you'd like to use as the first corner of your mine."),
    SUCCESS_SET_FIRST("&a&lOkay... &7Please left click on a block that you'd like to use as the second corner of your mine. &c&lNOTE: &7This can't be the same block as your first one."),

    MATERIAL_SET("&a&lSuccess! &7Material has been set"),
    RANK_SET("&a&lSuccess! &7Rank has been set, the mine is created."),
    LOCATIONS_SET("&a&lSuccess! &7Your locations have been set. Now please enter the rank you'd like to match to this mine (If you'd like to assign this mine to rank &nA&7, type &nA&7 in the chat."),

    //Broadcasts
    MINE_RESETTING("&eMine %s will reset in %s."),
    MINE_RESET("&eMine %s has reset.");

    private String msg;

    Lang(String msg) {
        this.msg = msg;
    }

    public void sendMessage(CommandSender sender) {
        sender.sendMessage(Utils.color(this.msg));
    }

    public void sendMessage(CommandSender sender, String... replacements) {
        sender.sendMessage(String.format(Utils.color(this.msg), replacements));
    }

    public String getMsg() {
        return msg;
    }
}
