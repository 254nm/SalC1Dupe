package org.l2x9.saldupe;

import org.bukkit.plugin.java.JavaPlugin;
import org.l2x9.saldupe.listeners.DupeEvent;
import org.l2x9.saldupe.listeners.Interact;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Interact(), this);
        saveDefaultConfig();
        if (getConfig().getBoolean("EnableAntiDupeLag")) {
            getServer().getPluginManager().registerEvents(new DupeEvent(), this);
        }
    }
}
