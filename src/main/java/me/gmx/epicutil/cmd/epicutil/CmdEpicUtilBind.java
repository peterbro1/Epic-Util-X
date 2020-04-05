package me.gmx.epicutil.cmd.epicutil;

import me.gmx.epicutil.core.BSubCommand;
import me.gmx.epicutil.util.ItemMetadata;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;



public class CmdEpicUtilTest
        extends BSubCommand
        implements Listener
{
    private ItemStack stack;

    public CmdEpicUtilTest() {
        this.aliases.add("bind");
        this.permission = "epicutil.bind";
        this.correctUsage = "/epicutil bind (optional:Player)";
    }

    public void execute() {
        ItemStack stack = ((Player)this.sender).getInventory().getItemInMainHand().clone();
        if (stack.getType() == Material.AIR) {
            return;
        }
        if (this.args.length == 0) {

            if (ItemMetadata.hasNBTDataString(stack, "only")) {
                this.player.getInventory().remove(this.player.getInventory().getItemInMainHand());
                this.player.getInventory().addItem(new ItemStack[] { ItemMetadata.newWithRemovedNBTData(stack, "only") });
                msg("Item binding removed.");
            } else {
                this.player.getInventory().remove(this.player.getInventory().getItemInMainHand());
                this.player.getInventory().addItem(new ItemStack[] { ItemMetadata.newWithNBTData(stack, "only", "GMX") });
                msg("Item binding added!");
            }
        } else if (this.args.length == 1) {

            if (Bukkit.getPlayer(this.args[false]) != null) {
                if (ItemMetadata.hasNBTDataString(stack, "only")) {
                    this.player.getInventory().remove(this.player.getInventory().getItemInMainHand());
                    this.player.getInventory().addItem(new ItemStack[] { ItemMetadata.newWithRemovedNBTData(stack, "only") });
                    msg("Item binding removed.");
                } else {

                    this.player.getInventory().remove(this.player.getInventory().getItemInMainHand());
                    this.player.getInventory().addItem(new ItemStack[] { ItemMetadata.newWithNBTData(stack, "only", this.args[0]) });
                    msg("Item binding for " + this.args[0] + " added!");
                }
            }
        } else {

            sendCorrectUsage();
        }
    }
}
