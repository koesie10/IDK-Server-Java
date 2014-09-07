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
package org.stevewinfield.suja.idk.game.achievements;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

public class Achievement {

    // getters
    public int getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getCategory() {
        return category;
    }

    public ConcurrentHashMap<Integer, AchievementLevel> getLevels() {
        return levels;
    }

    public int getLevelCount() {
        return levelCount;
    }

    public Achievement() {
        this.id = 0;
        this.groupName = "";
        this.category = "";
        this.levels = new ConcurrentHashMap<>();
    }

    public void set(final ResultSet row) throws SQLException {
        this.id = row.getInt("id");
        this.groupName = row.getString("group_name");
        this.category = row.getString("category");
    }

    public void addLevel(final int levelReward, final int levelScore, final int progressNeeded) {
        final AchievementLevel level = new AchievementLevel(++this.levelCount, levelReward, levelScore, progressNeeded);
        this.levels.put(level.getLevel(), level);
    }

    // fields
    private int id;
    private String groupName;
    private String category;
    private int levelCount;
    private final ConcurrentHashMap<Integer, AchievementLevel> levels;
}
