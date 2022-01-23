package dev.trobanko.staffhomes.Commands;

import dev.trobanko.staffhomes.Managers.StaffHomesManager;
import dev.trobanko.staffhomes.Utils.AddColor;
import dev.trobanko.staffhomes.Utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffHomesCommands implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            System.out.println("The console cannot execute this command!");
            return true;
        }

        Player p = (Player) sender;
        StaffHomesManager manager = new StaffHomesManager();

        if(!p.hasPermission("staffhomes.use") && (!p.hasPermission("staffhomes.admin"))){
            p.sendMessage(AddColor.color("&cYou do not have permissions to use this command!"));
            return true;
        }

        if(args.length < 1 || args.length > 2){
            Message.SEND(p);
            return true;

        }

        if(args[0].equalsIgnoreCase("set")){
            manager.addStaffHome(p, p.getLocation());
            p.sendMessage(AddColor.color("&aSuccessfully set your staff home to this location!"));
            p.sendMessage(AddColor.color("&7(Use &6'/staffhome go' &7to teleport to this location)"));
            return true;
        }
        else if (args[0].equalsIgnoreCase("go") && args.length == 1){
            if(!manager.hasStaffHome(p)){
                p.sendMessage(AddColor.color("&cYou do not have a staff home set"));
                p.sendMessage(AddColor.color("&7(Use &6'/staffhome set' &7to set a staff home)"));
                return true;
            }
            p.teleport(manager.getStaffHome(p));
            p.sendMessage(AddColor.color("&aTeleporting to your staff home..."));
            p.sendMessage(AddColor.color("&7(Use &6'/staffhome remove' &7to remove this staff home)"));
            return true;
        }
        else if (args[0].equalsIgnoreCase("remove")){
            if(!manager.hasStaffHome(p)){
                p.sendMessage(AddColor.color("&cYou do not have a staff home set"));
                p.sendMessage(AddColor.color("&7(Use &6'/staffhome set' &7to set a staff home)"));
                return true;
            }
            manager.removeStaffHome(p);
            p.sendMessage(AddColor.color("&aSuccessfully deleted your staff home"));
            return true;
        }
        else if (args[0].equalsIgnoreCase("go") && args.length == 2){

            if(!p.hasPermission("staffhomes.admin")){
                p.sendMessage(AddColor.color("&cYou do not have the required permission to teleport to other player's staff home"));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if(target == null){
                p.sendMessage(AddColor.color("&c" + args[1] + " is not a valid player name!"));
                p.sendMessage(AddColor.color("&7(Use &6'/staffhome go [<player>]' &7to go to a player's staff home)"));
                return true;
            }
            if(!manager.hasStaffHome(target)){
                p.sendMessage(AddColor.color("&c" + target.getDisplayName() +" does not have a staff home set!"));
                return true;
            }
            p.teleport(manager.getStaffHome(target));
            p.sendMessage(AddColor.color("&aTeleporting to " + target.getDisplayName() + "'s staff home..."));

        }
        else {
            Message.SEND(p);
        }
        return true;
    }
}
