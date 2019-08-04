package me.gmx.epicutil.cmd.lore;

import me.gmx.epicutil.config.Lang;
import me.gmx.epicutil.core.BSubCommand;
import me.gmx.epicutil.util.ItemUtils;
import me.gmx.epicutil.util.ReflectionUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CmdLoreUnbreak extends BSubCommand {

    public CmdLoreUnbreak(){
        this.aliases.add("unbreak");
        this.aliases.add("unbreaking");
        this.aliases.add("unbreakable");
        this.correctUsage = "/lore unbreak";
        this.permission = "epicutil.lore.unbreak";
        this.senderMustBePlayer = true;
    }
    @Override
    public void execute(){
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
        meta.setUnbreakable(!meta.isUnbreakable());
        stack.setItemMeta(meta);
        msg(Lang.MSG_LORE_UNBREAK.toMsg());

    }
}
