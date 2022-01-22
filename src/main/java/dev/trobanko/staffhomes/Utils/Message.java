package dev.trobanko.staffhomes.Utils;

import org.bukkit.entity.Player;

public class Message {

    public static void SEND(Player p){
        p.sendMessage(AddColor.color("&7========&b&lStaff Homes&7========="));
        p.sendMessage(AddColor.color("&6/staffhome set &7- &3Sets your Staff Home to your location"));
        p.sendMessage(AddColor.color("&6/staffhome go &7- &3Go to your Staff Home location"));
        p.sendMessage(AddColor.color("&6/staffhome go <player> &7- &3Go to a certain player's Staff Home"));
        p.sendMessage(AddColor.color("&6/staffhome remove &7- &3Remove your current Staff Home location"));
        p.sendMessage(AddColor.color("&7============================="));
    }

}

