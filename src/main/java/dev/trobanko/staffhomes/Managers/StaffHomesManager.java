package dev.trobanko.staffhomes.Managers;

import dev.trobanko.staffhomes.Configuration.HomesConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaffHomesManager {

    private static HashMap<String, Location> playerToLocation = new HashMap<>();

    public void addStaffHome(Player p, Location loc){
        playerToLocation.put(p.getUniqueId().toString(), loc);
    }

    public Location getStaffHome(Player p){
        return playerToLocation.get(p.getUniqueId().toString());
    }

    public void removeStaffHome(Player p){
        playerToLocation.remove(p.getUniqueId().toString());
    }

    public boolean hasStaffHome(Player p){
        return playerToLocation.containsKey(p.getUniqueId().toString());
    }

    public void mapToConfig(){
        for(Map.Entry<String, Location> e : playerToLocation.entrySet()){
            String[] coordinates = {String.valueOf(e.getValue().getX()), String.valueOf(e.getValue().getY()), String.valueOf(e.getValue().getZ()), e.getValue().getWorld().getName()};
            HomesConfig.get().set("staff-homes.players." + e.getKey(), coordinates);
        }
        HomesConfig.save();
    }

    public void configToMap(){
        HomesConfig.get().getConfigurationSection("staff-homes.players").getKeys(false).forEach(key -> {
            @SuppressWarnings("unchecked")
            List<String> coordinates = (List<String>) HomesConfig.get().getList("staff-homes.players." + key);
            playerToLocation.put(key, new Location(Bukkit.getWorld(coordinates.get(3)), Double.parseDouble(coordinates.get(0)), Double.parseDouble(coordinates.get(1)), Double.parseDouble(coordinates.get(2))));
        });
    }

    public void deleteHomes(){
        HomesConfig.get().set("staff-homes.players", null);
        HomesConfig.save();
    }
}
