package me.gmx.epicutil.cmd.epicutil;

import me.gmx.epicutil.config.Lang;
import me.gmx.epicutil.core.BSubCommand;

public class CmdEpicUtilReload extends BSubCommand {
    public CmdEpicUtilReload(){
        this.aliases.add("reload");
        this.correctUsage = "/epicutil reload";
        this.permission = "epicutil.reload";
    }
    @Override
    public void execute() {
        this.main.reload();
        msg(Lang.MSG_RELOADED.toMsg());
    }
}
