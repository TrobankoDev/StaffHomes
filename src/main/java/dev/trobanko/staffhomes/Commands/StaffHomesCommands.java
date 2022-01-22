package dev.trobanko.staffhomes.Commands;

import dev.trobanko.staffhomes.Managers.StaffHomesManager;
import dev.trobanko.staffhomes.Utils.AddColor;
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

        if(!p.hasPermission("staffhomes.use") && (!p.hasPermission("staffhomes.admin"))){
            p.sendMessage(AddColor.color("&cYou do not have permissions to use this command!"));
            return true;
        }

        if(args.length < 1 || args.length > 2){
            p.sendMessage(AddColor.color("&7========&b&lStaff Homes&7========="));
            p.sendMessage(AddColor.color("&6/staffhome set &7- &3Sets your Staff Home to your location"));
            p.sendMessage(AddColor.color("&6/staffhome go &7- &3Go to your Staff Home location"));
            p.sendMessage(AddColor.color("&6/staffhome player <player> &7- &3Go to another player's Staff Home"));
            p.sendMessage(AddColor.color("&6/staffhome remove &7- &3Remove your current Staff Home location"));
            p.sendMessage(AddColor.color("&7============================="));
            return true;

        }

        if(args[0].equalsIgnoreCase("set")){
            StaffHomesManager.addStaffHome(p, p.getLocation());
            p.sendMessage(AddColor.color("&aSuccessfully set your staff home to this location!"));
            p.sendMessage(AddColor.color("&7(Use &6'/staffhome go' &7to teleport to this location)"));
            return true;
        }
        else if (args[0].equalsIgnoreCase("go")){
            if(!StaffHomesManager.hasStaffHome(p)){
                p.sendMessage(AddColor.color("&cYou do not have a staff home set"));
                p.sendMessage(AddColor.color("&7(Use &6'/staffhome set' &7to set a staff home)"));
                return true;
            }
            p.teleport(StaffHomesManager.getStaffHome(p));
            p.sendMessage(AddColor.color("&aTeleporting to your staff home..."));
            p.sendMessage(AddColor.color("&7(Use &6'/staffhome remove' &7to remove this staff home)"));
            return true;
        }
        else if (args[0].equalsIgnoreCase("remove")){
            if(!StaffHomesManager.hasStaffHome(p)){
                p.sendMessage(AddColor.color("&cYou do not have a staff home set"));
                p.sendMessage(AddColor.color("&7(Use &6'/staffhome set' &7to set a staff home)"));
                return true;
            }
            StaffHomesManager.removeStaffHome(p);
            p.sendMessage(AddColor.color("&aSuccessfully deleted your staff home"));
            return true;
        }
        else if (args[0].equalsIgnoreCase("player")){
            if(!p.hasPermission("staffhomes.admin")){
                p.sendMessage(AddColor.color("&cYou do not have the required permission to teleport to other player's staff home"));
                return true;
            }
            if(args[1] == null){
                p.sendMessage(AddColor.color("&cIncorrect Usage!"));
                p.sendMessage(AddColor.color("&7(Use &6'/staffhome player <player name>' &7to go to a player's staff home)"));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if(target == null){
                p.sendMessage(AddColor.color("&c" + args[1] + " is not a valid player name!"));
                return true;
            }
            if(!StaffHomesManager.hasStaffHome(target)){
                p.sendMessage(AddColor.color("&c" + target.getDisplayName() +" does not have a staff home set!"));
                return true;
            }
            p.teleport(StaffHomesManager.getStaffHome(target));
            p.sendMessage(AddColor.color("&aTeleporting to " + target.getDisplayName() + "'s staff home..."));
        }
        else {
            p.sendMessage(AddColor.color("&7========&b&lStaff Homes&7========="));
            p.sendMessage(AddColor.color("&6/staffhome set &7- &3Sets your Staff Home to your location"));
            p.sendMessage(AddColor.color("&6/staffhome go &7- &3Go to your Staff Home location"));
            p.sendMessage(AddColor.color("&6/staffhome player <player> &7- &3Go to a certain player's Staff Home"));
            p.sendMessage(AddColor.color("&6/staffhome remove &7- &3Remove your current Staff Home location"));
            p.sendMessage(AddColor.color("&7============================="));
        }
        return true;
    }
}
