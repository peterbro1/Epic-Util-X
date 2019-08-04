package me.gmx.epicutil.cmd;

import me.gmx.epicutil.EpicUtil;
import me.gmx.epicutil.cmd.lore.*;
import me.gmx.epicutil.config.Lang;
import me.gmx.epicutil.core.BCommand;
import me.gmx.epicutil.core.BSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdLore extends BCommand implements CommandExecutor {

    public CmdLore(EpicUtil ins){
        super(ins);
        this.subcommands.add(new CmdLoreRename());
        this.subcommands.add(new CmdLoreHelp());
        this.subcommands.add(new CmdLoreSet());
        this.subcommands.add(new CmdLoreAdd());
        this.subcommands.add(new CmdLoreClear());
        this.subcommands.add(new CmdLoreUnbreak());

    }

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        if (arg3.length < 1) {
            arg0.sendMessage(Lang.MSG_USAGE_LORE.toMsg());
            return true;
        }



        for (BSubCommand cmd : this.subcommands) {
            if (cmd.aliases.contains(arg3[0])) {
                cmd.execute(arg0,arg3);
                return true;
            }
        }
        arg0.sendMessage(Lang.MSG_USAGE_LORE.toMsg());

        return true;
    }

}

