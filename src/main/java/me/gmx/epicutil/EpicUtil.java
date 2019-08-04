package me.gmx.epicutil;

import me.gmx.epicutil.cmd.CmdClearChat;
import me.gmx.epicutil.cmd.CmdEpicUtil;
import me.gmx.epicutil.cmd.CmdLore;
import me.gmx.epicutil.cmd.CmdMuteChat;
import me.gmx.epicutil.config.Lang;
import me.gmx.epicutil.config.Settings;
import me.gmx.epicutil.core.BConfig;
import me.gmx.epicutil.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EpicUtil extends JavaPlugin {

    private static EpicUtil ins;
    public BConfig mainConfig;
    public BConfig langConfig;
    Logger logger;
    public boolean magicnessCompat;
    public boolean chatMuted;

    @Override
    public void onEnable(){
        chatMuted = false;
        //magicnessCompat = Bukkit.getPluginManager().getPlugin("Magicness") == null ? false : true;
        ins = this;
        this.logger = getLogger();
        this.mainConfig = new BConfig(this,"config.yml");

        this.langConfig = new BConfig(this,"Lang.yml");
        this.logger.log(Level.INFO, String.format("[%s] Successfully enabled version %s!", new Object[] { getDescription().getName(), getDescription().getVersion() }));
        Lang.setConfig(this.langConfig);
        Settings.setConfig(this.mainConfig);
        registerCommands();
        registerListeners();
    }


    public static EpicUtil getInstance(){
        return ins;
    }
    public void registerCommands(){
        getCommand("epicutil").setExecutor(new CmdEpicUtil(getInstance()));
        getCommand("lore").setExecutor(new CmdLore(getInstance()));
        getCommand("clearchat").setExecutor(new CmdClearChat());
        getCommand("mutechat").setExecutor(new CmdMuteChat(getInstance()));

    }
    public void registerListeners(){
        getServer().getPluginManager().registerEvents(new PlayerListener(getInstance()),getInstance());
    }
    public void reload() {
        this.mainConfig = new BConfig(this, "config.yml");


        this.langConfig = new BConfig(this,"Lang.yml");
        Settings.setConfig(this.mainConfig);
        Lang.setConfig(this.langConfig);
    }
}
