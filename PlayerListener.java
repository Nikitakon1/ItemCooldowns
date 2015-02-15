package info.TheMFN.ItemCooldowns;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerListener implements Listener {

	public static Map<UUID, Long> cooldown2 = new HashMap<UUID, Long>();
	public static Map<UUID, Long> cooldown1 = new HashMap<UUID, Long>();
	public static Map<UUID, Long> cooldown1b = new HashMap<UUID, Long>();	
	public static Map<UUID, Long> cooldown2b = new HashMap<UUID, Long>();	

@EventHandler
@SuppressWarnings("deprecation")
public boolean onPlayerRightClick(PlayerInteractEvent event){
		int cooldownTimeEP = (ItemCooldowns.getInstance().getConfig().getInt("cooldowns.enderPearl"));
		final Player player = event.getPlayer();
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			 if(player.getItemInHand().getType().equals(Material.ENDER_PEARL)){
				 if (!player.hasPermission(Perms.epBP.getPerm())){
					if(PlayerListener.cooldown1.containsKey(player.getUniqueId())) {
						long secondsLeft = ((PlayerListener.cooldown1.get(player.getUniqueId())/1000)+cooldownTimeEP) - (System.currentTimeMillis()/1000);
						if(secondsLeft>0){
							if(secondsLeft<=cooldownTimeEP-1){
								event.setCancelled(true);
								event.getPlayer().updateInventory();
								player.sendMessage(ChatColor.DARK_RED +"Please wait " + secondsLeft +" seconds until you can enderpearl again!");
								return true;
							}
							if(secondsLeft>=cooldownTimeEP-1){
								event.setCancelled(true);
								event.getPlayer().updateInventory();
								return true;
						}
						}
					}
					PlayerListener.cooldown1.put(player.getUniqueId(), System.currentTimeMillis());
					event.setCancelled(false);
					player.launchProjectile(EnderPearl.class);
					player.getInventory().remove(Material.ENDER_PEARL);
					return true;
					}
					
				}
			}
		
		return false;
	}

@EventHandler
@SuppressWarnings("deprecation")
public boolean onPlayerConsume(PlayerItemConsumeEvent event){
	
	int cooldownTimeGA = (ItemCooldowns.getInstance().getConfig().getInt("cooldowns.goldenApple"));
	final Player player = event.getPlayer();
		 if(player.getItemInHand().getType().equals(Material.GOLDEN_APPLE)){
			 if (!player.hasPermission(Perms.GABP.getPerm())){
				if(PlayerListener.cooldown2.containsKey(player.getUniqueId())) {
					long secondsLeft = ((PlayerListener.cooldown2.get(player.getUniqueId())/1000)+cooldownTimeGA) - (System.currentTimeMillis()/1000);
					if(secondsLeft>0){
						if(secondsLeft<=cooldownTimeGA-1){
							event.setCancelled(true);
			            	event.getPlayer().updateInventory();
							player.sendMessage(ChatColor.DARK_RED +"Please wait " + secondsLeft +" seconds until you can eat another golden Apple!");
							return true;
						}
						if(secondsLeft>=cooldownTimeGA-1){
							event.setCancelled(true);
			            	event.getPlayer().updateInventory();
							return true;
						}
					}
					}
				}
				PlayerListener.cooldown2.put(player.getUniqueId(), System.currentTimeMillis());
				event.setCancelled(false);
				return true;
				
				}
				
		
	
	return false;
}
}
