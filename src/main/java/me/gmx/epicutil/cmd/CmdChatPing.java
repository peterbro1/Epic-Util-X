package me.gmx.epicutil.cmd;

import me.gmx.epicutil.EpicUtil;
import me.gmx.epicutil.config.Lang;
import me.gmx.epicutil.config.Settings;
import me.gmx.epicutil.handler.PingHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdChatPing implements CommandExecutor {
    private EpicUtil ins;
    public CmdChatPing(EpicUtil ins){
        this.ins = ins;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 0){
            sender.sendMessage(Lang.MSG_MUTECHAT_INVALID.toMsg());
            return true;
        }
        if (!sender.hasPermission("epicutil.chatping.toggle")){
            sender.sendMessage(Lang.MSG_NOACCESS.toMsg());
            return true;
        }
        ChatColor color;
        if (PingHandler.isPing((Player)sender)){
            PingHandler.removePlayer((Player)sender);
            color = ChatColor.DARK_RED;
        }else{
            PingHandler.addPlayer((Player)sender);
            color = ChatColor.GREEN;
        }

        sender.sendMessage(color + Lang.MSG_CHATPING_TOGGLE.toMsg());
        return true;
    }
}
