package com.mstiles92.plugins.nodragonportals;

import org.bukkit.plugin.java.JavaPlugin;

public class NoDragonPortals extends JavaPlugin {

	public void onEnable() {
		new PortalCreationListener(this);
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	public void onDisable() {
	}
}
