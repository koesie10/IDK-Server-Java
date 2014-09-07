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
package org.stevewinfield.suja.idk.game.catalog;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CatalogClubOffer {
    private static final Logger logger = Logger.getLogger(CatalogClubOffer.class);

    // getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getLengthDays() {
        return length;
    }

    public int getLengthSeconds() {
        return length * 86400;
    }

    public int getType() {
        return type;
    }

    public int getLengthMonths() {
        int correctedLength = length;

        return (int) Math.ceil(correctedLength / 31);
    }

    public CatalogClubOffer() {
        this.id = 0;
        this.name = "";
        this.price = 0;
        this.length = 0;
    }

    public void set(final ResultSet row) throws SQLException {
        this.id = row.getInt("id");
        this.name = row.getString("name");
        this.price = row.getInt("cost_credits");
        this.length = row.getInt("length_days");
        this.type = row.getInt("type");
    }

    // fields
    private int id;
    private String name;
    private int price;
    private int length;
    private int type;
}
