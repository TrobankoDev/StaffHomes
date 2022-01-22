package dev.trobanko.staffhomes;

import dev.trobanko.staffhomes.Commands.StaffHomesCommands;
import dev.trobanko.staffhomes.Configuration.HomesConfig;
import dev.trobanko.staffhomes.Managers.StaffHomesManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaffHomes extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("staffhome").setExecutor(new StaffHomesCommands());

        HomesConfig.setup(this);
        HomesConfig.save();

        if(HomesConfig.configExists()){
            StaffHomesManager.configToMap();
            StaffHomesManager.deleteHomes();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        StaffHomesManager.mapToConfig();
    }
}
