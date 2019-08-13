package me.gmx.epicutil.cmd.lore;

import com.google.common.collect.Lists;
import me.gmx.epicutil.config.Lang;
import me.gmx.epicutil.core.BSubCommand;
import me.gmx.epicutil.util.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class CmdLoreSet extends BSubCommand {
    public CmdLoreSet(){
        this.aliases.add("set");
        this.aliases.add("setlore");
        this.correctUsage = "/lore set [string...]";
        this.permission = "epicutil.lore.set";
        this.senderMustBePlayer = true;
    }
    @Override
    public void execute() {
        ItemStack stack = player.getInventory().getItemInMainHand();
        if (stack.getType().equals(Material.AIR) ){
            msg(Lang.MSG_LORE_NULLITEM.toMsg());
            return;
        }
        if (args.length == 0){
            msg(Lang.MSG_USAGE_LORE.toMsg());
            return;
        }
        String text = ChatColor.translateAlternateColorCodes('&',buildStringFromArgs(0,args.length - 1));
        ItemUtils.setLore(stack,text);
        msg(Lang.MSG_LORE_SET.toMsg());
    }
}
