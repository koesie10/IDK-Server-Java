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
package org.stevewinfield.suja.idk.game.bots;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BotPhrase {
    private static final Logger logger = Logger.getLogger(BotPhrase.class);

    public int getBotId() {
        return botId;
    }

    public String getPhrase() {
        return phrase;
    }

    public boolean isShouted() {
        return shouted;
    }

    public BotPhrase() {
        this.phrase = "";
    }

    public void set(final ResultSet row) throws SQLException {
        this.botId = row.getInt("bot_id");
        this.phrase = row.getString("phrase");
        this.shouted = row.getInt("is_shouted") == 1;
    }

    private int botId;
    private String phrase;
    private boolean shouted;
}
