package me.gmx.epicutil.handler;

import me.gmx.epicutil.EpicUtil;
import me.gmx.epicutil.config.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class FreezeManager {
    private EpicUtil ins;
    private ArrayList<String> frozen;
    private Inventory freezeInv;
    public FreezeManager(EpicUtil ins){
        this.ins = ins;
        this.frozen = new ArrayList<String>();
        createInventory();
    }

    private void createInventory(){
        this.freezeInv = Bukkit.createInventory(null,27, Settings.FREEZE_GUI_NAME.getString());

        ItemStack paper = new ItemStack(Material.getMaterial(Settings.FREEZE_GUI_ITEM_ITEM.getString()));
        ItemStack glass = new ItemStack(Material.getMaterial(Settings.FREEZE_GUI_SURROUNDINGITEM_ITEM.getString()));
        ItemMeta paperm = paper.getItemMeta();
        ItemMeta glassm = glass.getItemMeta();
        glassm.setDisplayName( Settings.FREEZE_GUI_SURROUNDINGITEM_NAME.getString());
        glass.setItemMeta(glassm);
        paperm.setDisplayName(Settings.FREEZE_GUI_ITEM_NAME.getString());


        ArrayList<String> papermlore = new ArrayList<>();
        List<String> stringList = Settings.FREEZE_GUI_ITEM_LORE.getStringList();
        IntStream.range(0, stringList.size()).forEach(i -> papermlore.add(stringList.get(i)));
        paperm.setLore(papermlore);

        ArrayList<String> glassmlore = new ArrayList<>();
        List<String> stringList2 = Settings.FREEZE_GUI_SURROUNDINGITEM_LORE.getStringList();
        IntStream.range(0, stringList2.size()).forEach(i -> glassmlore.add(stringList2.get(i)));
        glassm.setLore(glassmlore);

        glass.setItemMeta(glassm);
        paper.setItemMeta(paperm);
        freezeInv.setItem(10, paper);
        freezeInv.setItem(11, paper);
        freezeInv.setItem(12, paper);
        freezeInv.setItem(13, paper);
        freezeInv.setItem(14, paper);
        freezeInv.setItem(15, paper);
        freezeInv.setItem(16, paper);
        for (int i = 0; i < 27; ++i) {
            if (freezeInv.getItem(i) == null) {
                freezeInv.setItem(i, glass);
            }
        }
    }

    public void openInventory(Player p){
        p.openInventory(freezeInv);
    }

    public void addFrozen(String s){
        if (frozen.contains(s)){
            return;
        }
        frozen.add(s);
    }

    public boolean isFrozen(String s){
        return frozen.contains(s) ? true : false;
    }

    public void removeFrozen(String s){
       if (isFrozen(s)){
           frozen.remove(s);
       }
    }




}
