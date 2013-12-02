package com.mstiles92.plugins.nodragonportals;

import org.bukkit.Material;
import org.bukkit.PortalType;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PortalCreationListener implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	public void onEntityCreatePortalEvent(EntityCreatePortalEvent event) {
        FileConfiguration config = NoDragonPortals.getPlugin().getConfig();

		if ((event.getEntityType() == EntityType.ENDER_DRAGON) && (event.getPortalType() == PortalType.ENDER) && (config.getBoolean("DisablePortals"))) {
			event.setCancelled(true);
		}

		// Remove egg from top of portal if portals are not disabled and giving egg to player
		if ((!config.getBoolean("DisablePortals")) && (config.getBoolean("GiveEggToPlayer"))) {
			for (BlockState state : event.getBlocks()) {
				if (state.getType() == Material.DRAGON_EGG) {
					state.setType(Material.AIR);
					state.update();
				}
			}
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onEntityDeathEvent(EntityDeathEvent event) {
        FileConfiguration config = NoDragonPortals.getPlugin().getConfig();

		if ((event.getEntityType() == EntityType.ENDER_DRAGON) && (config.getBoolean("GiveEggToPlayer"))) {
			Player player = event.getEntity().getKiller();
			ItemStack item = new ItemStack(Material.DRAGON_EGG, 1);
			player.getInventory().addItem(item);
		}
	}
}
