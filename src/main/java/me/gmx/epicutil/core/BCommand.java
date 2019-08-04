package me.gmx.epicutil.core;

import me.gmx.epicutil.EpicUtil;

import java.util.ArrayList;

public class BCommand {
    public EpicUtil main;
    public ArrayList<BSubCommand> subcommands;

    public BCommand(EpicUtil ins){
        this.main = ins;
        subcommands = new ArrayList<BSubCommand>();
    }
}
