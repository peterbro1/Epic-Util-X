package me.gmx.epicutil.config;

import me.gmx.epicutil.core.BConfig;
import org.bukkit.ChatColor;

public enum Lang {

    PREFIX("&4[&2Epic&3Util&4]&r "),
    MSG_NOACCESS("&4You do not have access to this command!"),
    MSG_RELOADED("&3Config reloaded."),
    MSG_PLAYERONLY("Only players can use this command!"),
    MSG_USAGE_SUBCOMMAND("Invalid Command usage. Correct usage: %usage%"),
    MSG_USAGE_LORE("Incorrect usage. Please check /lore help"),
    MSG_USAGE_EPICUTIL("Invalid Command usage. Please check /epicutil help."),
    MSG_LORE_NULLITEM("&4You must hold an item for this command"),
    MSG_LORE_RENAME("&2Your item has been renamed!"),
    MSG_LORE_SET("&2Your item's lore has been changed!"),
    MSG_LORE_ADD("&2String appended to your item's lore."),
    MSG_LORE_CLEAR("&2Your item's lore has been cleared!"),
    MSG_LORE_UNBREAK("&2Unbreakability toggled!"),
    MSG_CLEARCHAT_INVALID("Invalid command usage. Usage is just " + ChatColor.GREEN + "/clearchat"),
    MSG_MUTECHAT_INVALID("Invalid command usage. Usage is just " + ChatColor.GREEN + "/mutechat"),
    MSG_CLEARCHAT_NAME("&3&l%name% &2has cleared the chat."),
    MSG_CHATMUTED("&4Chat is currently muted! Please wait for an admin to unmute it."),
    MSG_CHATMUTE_SUCCESS("&3Chat mute has been toggled."),
    LANG_CONSOLE("The console cannot use this command.");/*

    MSG_LORE_HELP("&2Lore&3-------------" + "\n" +
            "&3/lore rename [name] -" + "&9 Changes item name." + "\n" +
            "&3/lore set [string...] - " + "&9Sets item lore (description)." + "\n" +
            "&3/lore add [string...] - " + "&9Appends a string to existing item lore" + "\n" +
            "&3/lore clear - " + "&9Clears item lore." + "\n" +
            "&3/lore unbreak - " + "&9Toggles item unbreakability"),

    MSG_EPICUTIL_HELP("&2Epic Util&3-------------" + "\n" +
           "&3Epic Util is a small package of ultra-useful commands for all server owners."  + "\n" +
            "4&l-----Commands-----" + "\n" +
            "&3/lore - " + "&9Manipulate item properties."  + "\n" +
            "&3/mutechat - " + "&9Mutes chat to quiet things down." + "\n" +
            "&3/clearchat - " + "&9Clears chat to remove links or other bad things.");*/



    private String defaultValue;
    private static BConfig config;

    Lang(String str){
        defaultValue = str;
    }
    public String getPath() { return name().replace("_", "."); }

    public String getDefaultValue() { return this.defaultValue; }

    public String toString() { return fixColors(config.getConfig().getString(getPath())); }

    public static void setConfig(BConfig paramBConfig) {
        config = paramBConfig;
        load();
    }

    public String toMsg() {
        boolean bool = true;
        if (bool) {
            return fixColors(config.getConfig().getString(PREFIX.getPath()) + config.getConfig()
                    .getString(getPath()));
        }
        return fixColors(config.getConfig().getString(getPath()));
    }

    private static void load() {
        for (Lang lang : values()) {
            if (config.getConfig().getString(lang.getPath()) == null) {
                config.getConfig().set(lang.getPath(), lang.getDefaultValue());
            }
        }
        config.save();
    }


    private String fixColors(String paramString) {
        if (paramString == null)
            return "";
        return ChatColor.translateAlternateColorCodes('&', paramString);
    }

}
