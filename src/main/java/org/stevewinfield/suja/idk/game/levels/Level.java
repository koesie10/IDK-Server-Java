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
package org.stevewinfield.suja.idk.game.levels;

import org.apache.log4j.Logger;
import org.magicwerk.brownies.collections.GapList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Level {
    private static final Logger logger = Logger.getLogger(Level.class);

    // getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBadgeCode() {
        return badgeCode;
    }

    public List<LevelRight> getRights() {
        return rights;
    }

    public Level(final int id) {
        this.id = 0;
        this.rights = new GapList<>();
        this.name = "User";
        this.badgeCode = "";
    }

    public Level() {
        this(0);
    }

    public void set(final ResultSet row) throws SQLException {
        this.id = row.getInt("id");
        this.name = row.getString("level_name");
        this.badgeCode = row.getString("level_badge");
    }

    public void addRight(final LevelRight right) {
        this.rights.add(right);
    }

    public void loadRights() {
        if (this.id < 1) {
            return;
        }

    }

    // fields
    private int id;
    private String name;
    private String badgeCode;
    private final List<LevelRight> rights;
}
