package nl.justenrique.prison.player;

import nl.justenrique.prison.Main;
import nl.justenrique.prison.player.rank.Rank;
import nl.justenrique.prison.util.Utils;
import org.bukkit.Bukkit;

import java.util.UUID;

/**
 * Created by Enrique on 22-9-2019.
 * No part of this publication may be reproduced, distributed, or transmitted in any form or by any means.
 * Copyright © 2019 by Enrique
 */

public class PrisonPlayer {

    private String name;
    private UUID uuid;
    private double balance;
    private Rank rank;
    private int prestige;

    public PrisonPlayer(String name, UUID uuid, Rank rank, int prestige, double balance ) {
        this.name = name;
        this.uuid = uuid;
        this.rank = rank;
        this.prestige = prestige;
        this.balance = balance;
    }

    public void sendMessage(String msg) {
        Bukkit.getPlayer(this.uuid).sendMessage(Utils.color(msg));
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public void removeBalance(double amount) {
        this.balance -= amount;
    }

    public boolean hasEnoughMoney(double amount) {
        return this.balance >= amount;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public double getBalance() {
        return balance;
    }

    public Rank getRank() {
        return rank;
    }

    public int getPrestige() {
        return prestige;
    }
}
