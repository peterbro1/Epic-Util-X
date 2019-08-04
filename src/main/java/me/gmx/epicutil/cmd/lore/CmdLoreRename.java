package me.gmx.epicutil.cmd.lore;

import me.gmx.epicutil.config.Lang;
import me.gmx.epicutil.core.BSubCommand;
import me.gmx.epicutil.util.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CmdLoreRename extends BSubCommand {
    public CmdLoreRename(){
        this.aliases.add("rename");
        this.aliases.add("name");
        this.correctUsage = "/lore rename [name]";
        this.permission = "epicutil.lore.rename";
    }
    @Override
    public void execute() {
        String text = buildStringFromArgs(0,args.length - 1);
        ItemStack stack = player.getInventory().getItemInMainHand();
        if (stack.getType().equals(Material.AIR) ){
            msg(Lang.MSG_LORE_NULLITEM.toMsg());
            return;
        }
        if (args.length == 0){
            msg(Lang.MSG_USAGE_LORE.toMsg());
            return;
        }
        ItemMeta meta = ItemUtils.getMeta(stack);
        meta.setDisplayName(ChatColor.RESET + ChatColor.translateAlternateColorCodes('&',text));
        stack.setItemMeta(meta);

        msg(Lang.MSG_LORE_RENAME.toMsg());
    }
}
