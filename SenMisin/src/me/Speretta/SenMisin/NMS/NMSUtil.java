package me.Speretta.SenMisin.NMS;

import org.bukkit.Bukkit;

import me.Speretta.SenMisin.Main;


public class NMSUtil {
	public static NMS nms;
	public static void setupNMS() {
		String version = null;
		try {
			version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
			Bukkit.getConsoleSender().sendMessage("[SenMisin] versiyon " + version);
			Bukkit.broadcastMessage("versiyon "+ version);
		} catch(ArrayIndexOutOfBoundsException e) {
			Bukkit.getConsoleSender().sendMessage("[SenMisin] NMS ayarlanýrken hata oluþtu!");
			Bukkit.getPluginManager().disablePlugin(Main.getInstance());
		}
		if (version.equals("v1_15_R1")) {
			nms = new CB_1_15_R1();
		}
		else {
			Bukkit.getConsoleSender().sendMessage("[SenMisin] NMS ayarlanýrken hata oluþtu!");
			Bukkit.getPluginManager().disablePlugin(Main.getInstance());
		
		}
	}
	public static NMS getNMS() {
		return nms;
	}
}
