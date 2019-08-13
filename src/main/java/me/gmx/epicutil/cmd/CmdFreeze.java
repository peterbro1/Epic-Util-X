package me.gmx.epicutil.cmd;

import me.gmx.epicutil.EpicUtil;
import me.gmx.epicutil.cmd.epicutil.CmdEpicUtilHelp;
import me.gmx.epicutil.cmd.epicutil.CmdEpicUtilReload;
import me.gmx.epicutil.cmd.freeze.CmdFreezeHelp;
import me.gmx.epicutil.cmd.lore.CmdLoreRename;
import me.gmx.epicutil.config.Lang;
import me.gmx.epicutil.config.Settings;
import me.gmx.epicutil.core.BCommand;
import me.gmx.epicutil.core.BSubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdFreeze extends BCommand implements CommandExecutor {

    public CmdFreeze(EpicUtil ins){
        super(ins);
        this.subcommands.add(new CmdFreezeHelp());
    }

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
       // Player p = (Player)arg0;
       // Bukkit.broadcastMessage(p.getInventory().getItemInMainHand().getType().name());
        if (!arg0.hasPermission("epicutil.freeze")){
            arg0.sendMessage(Lang.MSG_NOACCESS.toMsg());
        }


        if (arg3.length < 1) {
            arg0.sendMessage(Lang.MSG_USAGE_FREEZE.toMsg());
            return true;
        }


        for (BSubCommand cmd : this.subcommands) {
            if (cmd.aliases.contains(arg3[0])) {
                cmd.execute(arg0,arg3);
                return true;
            }
        }

        if (Bukkit.getPlayer(arg3[0]) == null){
            arg0.sendMessage(Lang.MSG_NULLPlAYER.toMsg());
            return true;
        }
        if (EpicUtil.getInstance().freezeManager.isFrozen(arg3[0])){
            arg0.sendMessage(Lang.PREFIX + Lang.fixColors("&3Player has been unfrozen."));
            Bukkit.getPlayer(arg3[0]).sendMessage(Lang.MSG_UNFROZEN.toMsg());
            EpicUtil.getInstance().freezeManager.removeFrozen(arg3[0]);
            return true;
        }else{
            if (Bukkit.getPlayer(arg3[0]).hasPermission("epicutil.freeze.override")){
                arg0.sendMessage(Lang.PREFIX + Lang.fixColors("&4You cannot freeze this player."));
                return true;
            }
            if (Settings.FREEZE_GUI_ENABLED.getBoolean() == true) {
                EpicUtil.getInstance().freezeManager.openInventory(Bukkit.getPlayer(arg3[0]));
            }
                arg0.sendMessage(Lang.PREFIX +  Lang.fixColors("&3Player has been frozen."));
            EpicUtil.getInstance().freezeManager.addFrozen(arg3[0]);
            return true;
        }

    }

}

