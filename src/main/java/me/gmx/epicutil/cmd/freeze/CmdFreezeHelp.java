package me.gmx.epicutil.cmd.freeze;

import me.gmx.epicutil.config.Lang;
import me.gmx.epicutil.core.BSubCommand;

public class CmdFreezeHelp extends BSubCommand {

    public CmdFreezeHelp(){
        this.aliases.add("help");
        this.correctUsage = "/freeze help";
        this.permission = "epicutil.freeze.help";
    }
    @Override
    public void execute() {
        //msg(Lang.MSG_EPICUTIL_HELP.toMsg());
        msg("&2Screenshare&3-------------" + "\n" +
                "&dFreezes players for screensharing and hack checks."  + "\n" +
                "&4&l-----Commands-----" + "\n" +
                "&3/freeze player - " + "&9Toggles a player's freeze." + "\n");
    }
}
