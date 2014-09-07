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
package org.stevewinfield.suja.idk.game.players;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerAchievement {
    private static final Logger logger = Logger.getLogger(PlayerAchievement.class);

    public int getId() {
        return id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public int getProgress() {
        return progress;
    }

    public int getLevel() {
        return level;
    }

    public boolean toUpdate() {
        return this.update;
    }

    public boolean toInsert() {
        return this.insert;
    }

    public PlayerAchievement() {
    }

    public PlayerAchievement(final int playerId, final int achievementId, final int progress, final int level) {
        this.id = 0;
        this.playerId = playerId;
        this.achievementId = achievementId;
        this.progress = progress;
        this.level = level;
        this.insert = true;
    }

    public void set(final int progress, final int level) {
        this.progress = progress;
        this.level = level;
        this.update = true;
    }

    public void set(final ResultSet row) throws SQLException {
        this.id = row.getInt("id");
        this.playerId = row.getInt("player_id");
        this.achievementId = row.getInt("achievement_id");
        this.progress = row.getInt("progress");
        this.level = row.getInt("level");
    }

    private int id;
    private int playerId;
    private int achievementId;
    private int progress;
    private int level;
    private boolean insert;
    private boolean update;
}
