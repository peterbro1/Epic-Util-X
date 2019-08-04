package me.gmx.epicutil.listener;

import me.gmx.epicutil.EpicUtil;
import me.gmx.epicutil.config.Lang;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

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
            }
        }
    }
}
