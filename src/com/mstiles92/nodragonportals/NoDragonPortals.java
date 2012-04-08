package com.mstiles92.nodragonportals;

import org.bukkit.plugin.java.JavaPlugin;

public class NoDragonPortals extends JavaPlugin {

	public void onEnable() {
		new PortalCreationListener(this);
		this.getConfig().options().copyDefaults(true);
	}
	
	public void onDisable() {
	}
}
