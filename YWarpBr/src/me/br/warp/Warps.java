package me.br.warp;

import org.bukkit.plugin.java.JavaPlugin;

public class Warps extends JavaPlugin {
	
	public void onEnable() {
		registerConfig();
		registerCommands();
	}
	
	public void onDisable() {

	}
	
	public void registerConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public void registerCommands() {
		getCommand("setwarp").setExecutor(new setWarp(this));
		getCommand("delwarp").setExecutor(new RemoveWarpCommand(this));
		getCommand("warp").setExecutor(new WarpCommand(this));
	}

}
