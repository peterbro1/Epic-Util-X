package me.gmx.epicutil.cmd.lore;

import me.gmx.epicutil.config.Lang;
import me.gmx.epicutil.core.BSubCommand;

public class CmdLoreHelp extends BSubCommand {

    public CmdLoreHelp(){
        this.aliases.add("help");
        this.correctUsage = "/lore help";
        this.permission = "epicutil.lore.help";
    }

    @Override
    public void execute() {
        msg("&2Lore&3-------------" + "\n" +
                "&3/lore rename [name] -" + "&9 Changes item name." + "\n" +
                "&3/lore set [string...] - " + "&9Sets item lore (description)." + "\n" +
                "&3/lore add [string...] - " + "&9Appends a string to existing item lore" + "\n" +
                "&3/lore clear - " + "&9Clears item lore." + "\n" +
                "&3/lore unbreak - " + "&9Toggles item unbreakability");
    }
}
