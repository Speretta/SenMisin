package me.Speretta.SenMisin.NMS;

import org.bukkit.entity.Player;

public interface NMS {
	
	
	public interface SignListener {
        public void onDone(Player player, String[] lines);
    }
	public void showSign(Player p, String[] text, SignListener listener);
	public void showTitle(Player p, String title, String subtitle, int[] a);
}
