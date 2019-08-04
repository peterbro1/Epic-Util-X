package me.gmx.epicutil.cmd;

import me.gmx.epicutil.config.Settings;
import me.gmx.epicutil.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdClearChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equals("clearchat")){
            return false;
        }
        if (args.length > 0){
            sender.sendMessage(Lang.MSG_CLEARCHAT_INVALID.toMsg());
            return true;
        }
        if (!sender.hasPermission("epicutil.clearchat")){
            sender.sendMessage(Lang.MSG_NOACCESS.toMsg());
            return true;
        }
        for (int i = 0;i<25;i++){
            Bukkit.broadcastMessage("");
        }
        try {
            if (Settings.SHOW_WHO_CLEARED_CHAT.getBoolean()) {
                Bukkit.broadcastMessage(Lang.MSG_CLEARCHAT_NAME.toMsg().replace("%name%",sender.getName()));
            }
        }catch(Exception e){
            sender.sendMessage(Lang.PREFIX.toString() + ChatColor.DARK_RED + "An oopsie occurred! Please contact GMX.");
            return true;
        }

        return true;
    }
}
