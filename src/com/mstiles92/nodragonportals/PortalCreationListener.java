package com.mstiles92.nodragonportals;

import org.bukkit.Material;
import org.bukkit.PortalType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PortalCreationListener implements Listener {

	private final NoDragonPortals plugin;
	
	public PortalCreationListener(NoDragonPortals plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityCreatePortalEvent(EntityCreatePortalEvent event) {
		if ((event.getEntityType() == EntityType.ENDER_DRAGON) && (event.getPortalType() == PortalType.ENDER) && (plugin.getConfig().getBoolean("DisablePortals"))) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDeathEvent(EntityDeathEvent event) {
		if ((event.getEntityType() == EntityType.ENDER_DRAGON) && (plugin.getConfig().getBoolean("GiveEggToPlayer"))) {
			Player p = event.getEntity().getKiller();
			ItemStack i = new ItemStack(Material.DRAGON_EGG, 1);
			p.getInventory().addItem(i);
		}
	}
}
