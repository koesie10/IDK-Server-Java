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

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomCategory {

    // getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isStaffCategory() {
        return staffCategory;
    }

    public boolean isTradingEnabled() {
        return tradingEnabled;
    }

    public RoomCategory() {
        this.id = 0;
        this.title = "";
        this.staffCategory = false;
    }

    public void set(final ResultSet row) throws SQLException {
        this.id = row.getInt("id");
        this.title = row.getString("title");
        this.staffCategory = row.getInt("staff_category") == 1;
        this.tradingEnabled = row.getInt("trading_enabled") == 1;
    }

    // fields
    private int id;
    private String title;
    private boolean staffCategory;
    private boolean tradingEnabled;
}
