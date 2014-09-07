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
package org.stevewinfield.suja.idk.game.rooms;

import java.util.concurrent.ConcurrentHashMap;

public class GameTeam {

    public ConcurrentHashMap<Integer, RoomPlayer> getPlayers() {
        return players;
    }

    public RoomItem getGate() {
        return gate;
    }

    public int getPoints() {
        return points;
    }

    public int getId() {
        return id;
    }

    public RoomItem getScoreboard() {
        return scoreBoard;
    }

    public GameTeam(final int id, final RoomItem gate, final RoomItem scoreBoard) {
        this.id = id;
        this.players = new ConcurrentHashMap<>();
        this.gate = gate;
        this.scoreBoard = scoreBoard;
    }

    public void onPointReceived(final int points) {
        if (scoreBoard == null) {
            return;
        }
        scoreBoard.setFlags((this.points += points) + "");
        scoreBoard.update(false, true);
    }

    public void resetScore() {
        this.points = 0;
        if (this.scoreBoard != null) {
            this.scoreBoard.setFlags(points + "");
            this.scoreBoard.update(false, true);
        }
    }

    public void addPlayer(final RoomPlayer player) {
        this.players.put(player.getVirtualId(), player);
    }

    public void removePlayer(final RoomPlayer player) {
        this.players.remove(player.getVirtualId());
    }

    public void setGate(final RoomItem item) {
        this.gate = item;
    }

    public void setScoreBoard(final RoomItem item) {
        this.scoreBoard = item;
    }

    private final int id;
    private final ConcurrentHashMap<Integer, RoomPlayer> players;
    private RoomItem gate;
    private RoomItem scoreBoard;
    private int points;
}
