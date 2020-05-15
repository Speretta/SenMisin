package me.Speretta.SenMisin.GirisAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.Speretta.SenMisin.Main;


public class Veri {
    public static String kayitli_degil;
    public static String gec_kaldin;
    public static String sifre_yanlis;
	public Veri() {
        for(Object s:Main.getInstance().getConfig().getList("kayitli-degil")) {
        	kayitli_degil += s+"\n";
        }
        for(Object s:Main.getInstance().getConfig().getList("gec-kaldin")) {
        	gec_kaldin += s+"\n";
        }
        for(Object s:Main.getInstance().getConfig().getList("sifre-yanlis")) {
        	sifre_yanlis += s+"\n";
        }
       
	}
	public static HashMap<UUID, String> oyuncudurum = new HashMap<UUID,String>();
	public static HashMap<UUID, Integer> oyuncuhata = new HashMap<UUID, Integer>();
	
	public static String get(Player p){
		String kod = "Hata!";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://2.59.118.83/arvalexc_thenish", "arvalexc_protechtheyka", "@Butalibsiriyev200311");
			Statement statement = con.createStatement(); 
			ResultSet result = statement.executeQuery("SELECT * FROM Accounts WHERE realname='" + p.getName() + "'");
			if (result.next()==true) {
				kod = result.getString("password");
			}

		} catch (ClassNotFoundException e) {
			Bukkit.getConsoleSender().sendMessage("Mysql sınıfı çekerken hata oluştu: "+e.getMessage());
		}
		catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage("Mysql bağlanırken hata oluştu: "+e.getMessage());
			
		}
        return kod;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	/*public static boolean etKayit(Player p, String s) {
		File playerfile = new File(playerfiles.getPath(),p.getName()+".yml");
		if(!playerfile.exists())
			try {
				playerfile.createNewFile();
				FileConfiguration playercfg = YamlConfiguration.loadConfiguration(playerfile);
				playercfg.set("�ifre", Sifrele.encryptThisString(s));
				playercfg.save(playerfiles);
				return true;
				
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage("[Sen Misin]" + p.getName() + " adl� oyuncunun dosyas� olu�turulurken hata olu�tu!");
				return false;
			}
		return false;
	}*/
}
