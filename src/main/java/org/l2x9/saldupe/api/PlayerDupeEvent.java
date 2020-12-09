package org.l2x9.saldupe.api;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class PlayerDupeEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    Player player;
    Chunk chunk;

    public Player getPlayer() {
        return player;
    }

    public Chunk getChunk() {
        return chunk;
    }

    private boolean cancelled;

    public PlayerDupeEvent(Player player, Chunk chunk) {
       this.player = player;
       this.chunk = chunk;
    }
    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}