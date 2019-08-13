package me.gmx.epicutil.cmd.lore;

import me.gmx.epicutil.config.Lang;
import me.gmx.epicutil.core.BSubCommand;
import me.gmx.epicutil.util.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CmdLoreAdd extends BSubCommand {
    public CmdLoreAdd(){
        this.aliases.add("add");
        this.aliases.add("addlore");
        this.correctUsage = "/lore addlore [string...]";
        this.permission = "epicutil.lore.add";
        this.senderMustBePlayer=true;
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
        ItemUtils.appendLore(stack,text);
        msg(Lang.MSG_LORE_ADD.toMsg());
    }
}
