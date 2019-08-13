package me.gmx.epicutil.listener;

import me.gmx.epicutil.EpicUtil;
import me.gmx.epicutil.config.Lang;
import me.gmx.epicutil.config.Settings;
import me.gmx.epicutil.handler.PingHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.projectiles.ProjectileSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PlayerListener implements Listener {
    private EpicUtil ins;
    public PlayerListener(EpicUtil ins){
        this.ins = ins;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if (ins.chatMuted){
            if (!e.getPlayer().hasPermission("epicutil.mutechat.bypass")){
                e.setCancelled(true);
                e.getPlayer().sendMessage(Lang.MSG_CHATMUTED.toMsg());
                return;
            }
        }
            String message = e.getMessage();
        for (Player p : e.getRecipients()){
            if (message.toLowerCase().contains(p.getName().toLowerCase()) && e.getPlayer() != p && PingHandler.isPing(p) && e.getPlayer().hasPermission("epicutil.chatping.recieve")){
                p.sendMessage(Settings.CHATPING_TOP.getString());
                Bukkit.getScheduler().scheduleSyncDelayedTask(ins,()->{
                    p.sendMessage(Settings.CHATPING_BOT.getString());
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);

                },1);
            }
        }



    }




    @EventHandler
    public void inventoryInteract(InventoryClickEvent e){
        if (e.getView().getTitle().equals(Settings.FREEZE_GUI_NAME.getString())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void inventoryClose(InventoryCloseEvent e){
        if (ins.freezeManager.isFrozen(e.getPlayer().getName()) && e.getView().getTitle().equals(Settings.FREEZE_GUI_NAME.getString())){
           Bukkit.getScheduler().scheduleSyncDelayedTask(ins,()->{
               ins.freezeManager.openInventory((Player)e.getPlayer());
           },10);
        }
    }

    @EventHandler
    public void commandProcess(PlayerCommandPreprocessEvent e){
        if (ins.freezeManager.isFrozen(e.getPlayer().getName())){
            e.setCancelled(true);
            e.getPlayer().sendMessage(Settings.FREEZE_MESSAGE.getString());
        }
    }
    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent e){
        if (ins.freezeManager.isFrozen(e.getEntity().getName())){
            e.setCancelled(true);
        }else if (ins.freezeManager.isFrozen(e.getDamager().getName())){
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if (ins.freezeManager.isFrozen(e.getPlayer().getName())){
            e.setCancelled(true);
                e.getPlayer().sendMessage(Settings.FREEZE_MESSAGE.getString());

        }
    }

    @EventHandler
    public void onExit(PlayerQuitEvent e){
        String s = e.getPlayer().getName();
        if (ins.freezeManager.isFrozen(s)){
            for (Player pl : Bukkit.getOnlinePlayers()) {
                if (pl.hasPermission("epicutil.freeze.notify") || pl.isOp()) {
                    pl.sendMessage(Settings.FREEZE_PLAYERLEFT.getString().replace("%target%", s));
                }
            }

            if (Settings.FREEZE_AUTOBAN.getBoolean() == true){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),Settings.FREEZE_BANCOMMAND.getString().replace("%target%",s));
            }
        }

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        PingHandler.addPlayer(e.getPlayer());
    }

}
