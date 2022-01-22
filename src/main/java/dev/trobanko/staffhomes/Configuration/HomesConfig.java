package dev.trobanko.staffhomes.Configuration;

import dev.trobanko.staffhomes.StaffHomes;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class HomesConfig {

    private static File file; // Creating the file object
    private static FileConfiguration HomesFile; // Creating the FileConfiguration object which allows Bukkit to edit
    StaffHomes plugin; // Instance of the class that extends JavaPlugin

    // Constructor
    public HomesConfig(StaffHomes plugin) {
        this.plugin = plugin;
    }

    // Set up the file
    public static void setup(StaffHomes plugin){
        // Assign file to the yml file in the plugin's data folder
        file = new File(plugin.getDataFolder(), "homes.yml");

        // If the file doesn't exist, create it
        if(!file.exists()){
            try {
                plugin.getDataFolder().mkdirs(); // Make the data folder directory to place the config file
                file.createNewFile(); // Create the file
            } catch (IOException e){
                plugin.getLogger().log(Level.SEVERE, "A problem has occurred while creating homes.yml");
            }
        }
        HomesFile = YamlConfiguration.loadConfiguration(file); // Set the FileConfig object to our file object that was created

    }

    public static FileConfiguration get(){ // Returns the config file
        return HomesFile;
    }

    public static void save(){ // Save the config file
        try {
            HomesFile.save(file);
        }catch (IOException e){
            System.out.println("A problem has occurred while saving homes.yml");
        }
    }

    public static void reload(){ // Reload the config file
        HomesFile = YamlConfiguration.loadConfiguration(file);
    }

    public static boolean configExists(){
        return file.exists() && file.length() != 0;
    }

}
