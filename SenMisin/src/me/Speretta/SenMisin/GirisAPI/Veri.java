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
    public static String kayitli_degil = new String();
    public static String gec_kaldin = new String();
    public static String sifre_yanlis = new String();
    public static String host,db,user,pass,table;
    public static String title,subtitle;
    public static String kickresult;
	public Veri() {
		host= Main.getInstance().getConfig().getString("mysql.Host");
		db= Main.getInstance().getConfig().getString("mysql.DB");
		user= Main.getInstance().getConfig().getString("mysql.User");
		pass= Main.getInstance().getConfig().getString("mysql.Password");
		table= Main.getInstance().getConfig().getString("mysql.Table");
		title= Main.getInstance().getConfig().getString("title.title");
		subtitle= Main.getInstance().getConfig().getString("title.subtitle");
		kickresult= Main.getInstance().getConfig().getString("kick-result");
        for(Object s:Main.getInstance().getConfig().getList("kayitli-degil")) {
        	kayitli_degil += String.valueOf(s)+"\n";
        }
        for(Object s:Main.getInstance().getConfig().getList("gec-kaldin")) {
        	gec_kaldin += String.valueOf(s)+"\n";
        }
        for(Object s:Main.getInstance().getConfig().getList("sifre-yanlis")) {
        	sifre_yanlis += String.valueOf(s)+"\n";
        }
      
       
	}
	public static HashMap<UUID, String> oyuncudurum = new HashMap<UUID,String>();
	public static HashMap<UUID, Integer> oyuncuhata = new HashMap<UUID, Integer>();
	
	public static String get(Player p){
		String kod = "Hata!";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+db, user, pass);
			Statement statement = con.createStatement(); 
			ResultSet result = statement.executeQuery("SELECT * FROM "+table+" WHERE realname='" + p.getName() + "'");
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
