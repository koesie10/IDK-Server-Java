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
package org.stevewinfield.suja.idk.game.moderation;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

public class ModerationPresetAction {
    private static final Logger logger = Logger.getLogger(ModerationPresetAction.class);

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public String getMessage() {
        return message;
    }

    public boolean isCategory() {
        return category;
    }

    public String getCaption() {
        return caption;
    }

    public ConcurrentHashMap<Integer, ModerationPresetAction> getItems() {
        return subItems;
    }

    public ModerationPresetAction() {
        this.subItems = new ConcurrentHashMap<>();
        this.caption = "";
        this.message = "";
    }

    public void set(final ResultSet row) throws SQLException {
        this.id = row.getInt("id");
        this.parentId = row.getInt("parent_id");
        this.caption = row.getString("caption");
        this.message = row.getString("message");
    }

    public void addSubItem(final ModerationPresetAction item) {
        this.subItems.put(item.getId(), item);
    }

    // fields
    private int id;
    private int parentId;
    private boolean category;
    private String caption;
    private String message;
    private final ConcurrentHashMap<Integer, ModerationPresetAction> subItems;
}
