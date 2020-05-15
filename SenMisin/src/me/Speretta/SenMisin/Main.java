package me.Speretta.SenMisin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.Speretta.SenMisin.GirisAPI.API;
import me.Speretta.SenMisin.NMS.NMSUtil;

public class Main extends JavaPlugin{
	
	private static Main instance;
	public static Main getInstance() {
		return instance;
	}
	public NMSUtil nmsutil;
	public Event event;
	public API api;
	@Override
	public void onEnable() {
		instance = this;
		event = new Event();
		nmsutil = new NMSUtil();
		api = new API();
		Main.getInstance().saveDefaultConfig();
		NMSUtil.setupNMS();
		Bukkit.getPluginManager().registerEvents(event, this);
	}
	@Override
	public void onDisable() {
		
	}

}
