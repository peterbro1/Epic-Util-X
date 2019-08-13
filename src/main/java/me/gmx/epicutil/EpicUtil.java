package me.gmx.epicutil;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.gmx.epicutil.cmd.*;
import me.gmx.epicutil.config.Lang;
import me.gmx.epicutil.config.Settings;
import me.gmx.epicutil.core.BConfig;
import me.gmx.epicutil.handler.FreezeManager;
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
    public FreezeManager freezeManager;

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
        freezeManager = new FreezeManager(getInstance());

    }


    public static EpicUtil getInstance(){
        return ins;
    }


    public void registerCommands(){
        getCommand("epicutil").setExecutor(new CmdEpicUtil(getInstance()));
        getCommand("lore").setExecutor(new CmdLore(getInstance()));
        getCommand("clearchat").setExecutor(new CmdClearChat());
        getCommand("mutechat").setExecutor(new CmdMuteChat(getInstance()));
        getCommand("freeze").setExecutor(new CmdFreeze(getInstance()));
        getCommand("chatping").setExecutor(new CmdChatPing(getInstance()));

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
