package com.mstiles92.plugins.nodragonportals;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class NoDragonPortals extends JavaPlugin {
    private static Plugin plugin;

	public void onEnable() {
        plugin = this;

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PortalCreationListener(), this);
	}
	
	public static Plugin getPlugin() {
        return plugin;
    }
}
