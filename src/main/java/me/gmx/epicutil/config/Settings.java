package me.gmx.epicutil.config;

import me.gmx.epicutil.core.BConfig;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public enum Settings {

    CLEARCHAT_SHOWNAME("true"),
    FREEZE_AUTOBAN("true"),
    FREEZE_BANCOMMAND("ban %target% logging out while frozen -s"),
    FREEZE_GUI_ENABLED("true"),
    FREEZE_GUI_NAME("&2You are frozen!!!"),
    FREEZE_GUI_ITEM_ITEM("PAPER"),
    FREEZE_GUI_ITEM_NAME("&c&lYOU HAVE BEEN FROZEN!"),
    FREEZE_GUI_ITEM_LORE("&5Join our discord server:/&d&l&nwww.discord.purpleprison.net/&C&lLOGGING OUT WILL BE AN AUTOMATIC BAN!"),
    FREEZE_GUI_SURROUNDINGITEM_ITEM("STAINED_GLASS"),
    FREEZE_GUI_SURROUNDINGITEM_NAME("&c&lWARNING!"),
    FREEZE_GUI_SURROUNDINGITEM_LORE("&c&lYOU HAVE BEEN FROZEN!/&4&lDO NOT LOG OUT! OR YOU WILL BE AUTO BANNED!"),
    FREEZE_MESSAGE("&4You are frozen! You cannot move!"),
    FREEZE_PLAYERLEFT("&6&lWARNING: &e%target% &cleft the server while frozen!"),
    CHATPING_TOP("&6&l=--------------="),
    CHATPING_BOT("&6&l=--------------=");



    private String defaultValue;
    private static BConfig config;

    Settings(String str) {
        defaultValue = str;
    }

    public String getPath() { return name().replace("_", "."); }

    public String getDefaultValue() { return this.defaultValue; }

    public String getString(){
        return color(config.getConfig().getString(getPath()));
    }

    public static void setConfig(BConfig paramBConfig) {
        config = paramBConfig;
        load();
    }

    public List<String> getStringList(){
        return Arrays.asList(getString().split("/"));
    }

    public int getNumber() {
        return Integer.parseInt(config.getConfig().getString(getPath()));
    }

    public boolean getBoolean() {

        try {
            return Boolean.valueOf(config.getConfig().getString(getPath()));
        }catch(NullPointerException e) {
            return false;
        }

    }

    private static void load() {
        for (Settings lang : values()) {
            if (config.getConfig().getString(lang.getPath()) == null) {
                config.getConfig().set(lang.getPath(), lang.getDefaultValue());
            }
        }
        config.save();
    }

    private String color(String s){
        return ChatColor.translateAlternateColorCodes('&',s);
    }
}
