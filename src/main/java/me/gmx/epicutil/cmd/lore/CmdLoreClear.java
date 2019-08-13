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

public class CmdLoreClear extends BSubCommand {
    public CmdLoreClear(){
        this.aliases.add("clear");
        this.aliases.add("clearlore");
        this.correctUsage = "/lore clear";
        this.permission = "epicutil.lore.clear";
        this.senderMustBePlayer=true;
    }
    @Override
    public void execute() {
        ItemStack stack = player.getInventory().getItemInMainHand();
        if (stack.getType().equals(Material.AIR) ){
            msg(Lang.MSG_LORE_NULLITEM.toMsg());
            return;
        }
        if (args.length > 0){
            msg(Lang.MSG_USAGE_LORE.toMsg());
            return;
        }
        ItemMeta meta = ItemUtils.getMeta(stack);
        if (meta.hasLore())
            meta.setLore(new ArrayList<String>());

        stack.setItemMeta(meta);

        msg(Lang.MSG_LORE_CLEAR.toMsg());
    }
}
