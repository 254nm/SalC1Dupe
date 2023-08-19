package org.l2x9.saldupe.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.l2x9.saldupe.api.PlayerDupeEvent;

public class Interact implements Listener {
    @EventHandler
    public void onVehicleEnter(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked() instanceof ChestedHorse)) return;
        Player player = event.getPlayer();

        if (player.getInventory().getItemInMainHand().getType() != Material.CHEST && player.getInventory().getItemInOffHand().getType() != Material.CHEST) return;

        if (!player.hasPermission("dupe.use")) {
            sendMessage(player, "&6You do not have permission to dupe");
            return;
        }

        ChestedHorse horse = (ChestedHorse) event.getRightClicked();
        if (!horse.getPassengers().contains(player)) horse.addPassenger(player);
        PlayerDupeEvent playerDupeEvent = new PlayerDupeEvent(player, horse.getLocation().getChunk());
        Bukkit.getServer().getPluginManager().callEvent(playerDupeEvent);

        if (playerDupeEvent.isCancelled()) return;

        event.setCancelled(true);
        for (ItemStack item : horse.getInventory().getContents()) {
            if (item == null || item.getType() == Material.SADDLE) continue;
            horse.getWorld().dropItemNaturally(horse.getLocation(), item);
        }
        horse.setCarryingChest(false);
        sendMessage(player, "&cChests on llamas, donkeys, mules, Etc is currently disabled, this is to facilitate the SalC1 TreeMC dupe");
    }

    private void sendMessage(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
