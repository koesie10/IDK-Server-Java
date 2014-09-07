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
package org.stevewinfield.suja.idk.game.furnitures;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FurnitureExchange {
    private static final Logger logger = Logger.getLogger(FurnitureExchange.class);

    public int getFurniId() {
        return furniId;
    }

    public int getChangeCoins() {
        return changeCoins;
    }

    public int getChangePixels() {
        return changePixels;
    }

    public int getChangeExtra() {
        return changeExtra;
    }

    public void set(final ResultSet row) throws SQLException {
        this.furniId = row.getInt("id");
        this.changeCoins = row.getInt("change_coins");
        this.changePixels = row.getInt("change_pixels");
        this.changeExtra = row.getInt("change_extra");
    }

    // fields
    private int furniId;
    private int changeCoins;
    private int changePixels;
    private int changeExtra;
}
