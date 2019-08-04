package me.gmx.epicutil.config;

import me.gmx.epicutil.core.BConfig;

public enum Settings {

    SHOW_WHO_CLEARED_CHAT("true");


    private String defaultValue;
    private static BConfig config;

    Settings(String str) {
        defaultValue = str;
    }

    public String getPath() { return name().replace("_", "."); }

    public String getDefaultValue() { return this.defaultValue; }


    public static void setConfig(BConfig paramBConfig) {
        config = paramBConfig;
        load();
    }
    public int getNumber() {
        return Integer.parseInt(config.getConfig().getString(getPath()));
    }

    public boolean getBoolean() throws Exception{

        try {
            return Boolean.valueOf(config.getConfig().getString(getPath()));
        }catch(NullPointerException e) {
            throw new Exception("Value could not be converted to a boolean");
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
}
