package me.gmx.epicutil.cmd;

import me.gmx.epicutil.EpicUtil;
import me.gmx.epicutil.config.Lang;
import me.gmx.epicutil.config.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdMuteChat implements CommandExecutor {
    private EpicUtil ins;
    public CmdMuteChat(EpicUtil ins){
        this.ins = ins;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equals("mutechat")){
            return false;
        }
        if (args.length > 0){
            sender.sendMessage(Lang.MSG_MUTECHAT_INVALID.toMsg());
            return true;
        }
        if (!sender.hasPermission("epicutil.mutechat")){
            sender.sendMessage(Lang.MSG_NOACCESS.toMsg());
            return true;
        }
        ins.chatMuted = !ins.chatMuted;
        sender.sendMessage(Lang.MSG_CHATMUTE_SUCCESS.toMsg());

        return true;
    }
}
