package info.TheMFN.ItemCooldowns;

import org.bukkit.plugin.java.JavaPlugin;

public class ItemCooldowns extends JavaPlugin{
	
	public static ItemCooldowns instance;
	
	@Override
	public void onEnable(){
		getConfig().options().copyDefaults(true);
		saveConfig();
		instance = this;
		getLogger().info("ItemCooldowns Has Started");
		getServer().getPluginManager().registerEvents(new PlayerListener(), getInstance());
	}

	
	@Override
	public void onDisable(){
		instance = null;
		getLogger().info("ItemCooldowns Has Stopped");
		
	}
	
	public static ItemCooldowns getInstance(){
		return instance;
	}
}
