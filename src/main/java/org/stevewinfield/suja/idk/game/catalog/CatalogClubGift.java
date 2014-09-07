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
import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.communication.ISerialize;
import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.game.furnitures.Furniture;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CatalogClubGift implements ISerialize {
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMinMonths() {
        return minMonths;
    }

    public boolean onlyVIP() {
        return vip;
    }

    public Furniture getBase() {
        return baseItem;
    }

    public CatalogClubGift() {
        this.name = "";
    }

    public void set(final ResultSet row) throws SQLException {
        this.id = row.getInt("id");
        this.name = row.getString("name");
        this.baseItem = Bootloader.getGame().getFurnitureManager().getFurniture(row.getInt("furni_id"));
        this.vip = row.getInt("only_vip") == 1;
        this.minMonths = row.getInt("min_months");
    }

    // fields
    private int id;
    private int minMonths;
    private boolean vip;
    private String name;
    private Furniture baseItem;

    @Override
    public void serialize(final MessageWriter writer) {
        writer.push(id);
        writer.push(name);
        writer.push(0); // is a gift so its free
        writer.push(0);
        writer.push(0);
        writer.push(1); // item
        writer.push(baseItem == null ? "s" : baseItem.getType());
        writer.push(baseItem == null ? 0 : baseItem.getSpriteId());
        writer.push("");
        writer.push(1);
        writer.push(-1);
        writer.push(0);
    }
}
