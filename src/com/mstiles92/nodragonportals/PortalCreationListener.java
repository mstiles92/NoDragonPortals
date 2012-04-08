package com.mstiles92.nodragonportals;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.PortalType;
import org.bukkit.block.BlockState;
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
		// Remove egg from top of portal if portals are not disabled and giving egg to player
		if ((!plugin.getConfig().getBoolean("DisablePortals")) && (plugin.getConfig().getBoolean("GiveEggToPlayer"))) {
			List<BlockState> blocks = event.getBlocks();
			for (BlockState bs : blocks) {
				if (bs.getType() == Material.DRAGON_EGG) {
					bs.setType(Material.AIR);
					bs.update();
				}
			}
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
