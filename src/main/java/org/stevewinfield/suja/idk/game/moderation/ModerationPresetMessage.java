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

public class ModerationPresetMessage {
    private static final Logger logger = Logger.getLogger(ModerationPresetMessage.class);

    public int getId() {
        return id;
    }

    public ModerationPresetMessageType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public ModerationPresetMessage() {
        this.message = "";
    }

    public void set(final ResultSet row) throws SQLException {
        this.id = row.getInt("id");
        this.type = row.getString("type").equals("room") ? ModerationPresetMessageType.ROOM_MESSAGE : ModerationPresetMessageType.PLAYER_MESSAGE;
        this.message = row.getString("message");
    }

    // fields
    private int id;
    private ModerationPresetMessageType type;
    private String message;
}
