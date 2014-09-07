/*
 * Copyright 2014 The IDK Project
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.stevewinfield.suja.idk.game.rooms.tasks;

import org.magicwerk.brownies.collections.GapList;
import org.stevewinfield.suja.idk.game.rooms.GameTeam;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomItem;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public abstract class GameTask {
    protected final RoomInstance room;
    protected boolean running;
    protected boolean paused;
    protected boolean ended;
    protected int flexInteger;
    protected final List<RoomItem> gameItems;
    protected final ConcurrentHashMap<Integer, GameTeam> gameTeams;

    public GameTask(final RoomInstance room) {
        this.room = room;
        this.ended = true;
        this.gameItems = new GapList<>();
        this.gameTeams = new ConcurrentHashMap<>();
    }

    public int getFlexInteger() {
        return flexInteger;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isPaused() {
        return paused;
    }

    public GameTeam getTeam(final int id) {
        return this.gameTeams.containsKey(id) ? this.gameTeams.get(id) : null;
    }

    public void addGameItem(final RoomItem item) {
        this.gameItems.add(item);
    }

    public void removeGameItem(final RoomItem item) {
        this.gameItems.remove(item);
    }

    public void addGameTeam(final int teamId, final GameTeam team) {
        this.gameTeams.put(teamId, team);
    }

    public boolean hasEnded() {
        return ended;
    }

    public void onGameEnds(final RoomItem timer) {
        this.room.getWiredHandler().onGameEnds();
    }

    public void onGameStarts(final RoomItem timer) {
        this.room.getWiredHandler().onGameStarts();
    }

    public abstract void onGameRests(RoomItem timer);

    public abstract void onHandle(RoomPlayer player, RoomItem item);
}
