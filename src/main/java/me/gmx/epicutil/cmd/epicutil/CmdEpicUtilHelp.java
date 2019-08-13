package me.gmx.epicutil.cmd.epicutil;

import me.gmx.epicutil.config.Lang;
import me.gmx.epicutil.core.BSubCommand;

public class CmdEpicUtilHelp extends BSubCommand {

    public CmdEpicUtilHelp(){
        this.aliases.add("help");
        this.correctUsage = "/epicutil help";
        this.permission = "epicutil.help";
    }
    @Override
    public void execute() {
        //msg(Lang.MSG_EPICUTIL_HELP.toMsg());
        msg("&2Epic Util&3-------------" + "\n" +
                "&dEpic Util is a small package of ultra-useful commands for all server owners."  + "\n" +
                "&4&l-----Commands-----" + "\n" +
                "&3/epicutil reload - " + "&9Reloads config." + "\n" +
                "&4-----Sub-Commands-----" + "\n" +
                "&3/lore - " + "&9Manipulate item properties."  + "\n" +
                "&3/mutechat - " + "&9Mutes chat to quiet things down." + "\n" +
                "&3/ss - " + "&9Freezes a player" + "\n" +
                "&3/clearchat - " + "&9Clears chat to remove links or other bad things." + "\n" +
                "&3/chatping - " + "&9Toggles whether or not you recieve pings from chat messages." + "\n");
    }
}
