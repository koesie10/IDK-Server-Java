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
package org.stevewinfield.suja.idk.communication.moderation.readers;

import org.apache.log4j.Logger;
import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.Translations;
import org.stevewinfield.suja.idk.communication.IMessageReader;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.communication.moderation.writers.ModerationPlayerInfoWriter;
import org.stevewinfield.suja.idk.game.miscellaneous.NotifyType;
import org.stevewinfield.suja.idk.game.players.PlayerInformation;
import org.stevewinfield.suja.idk.network.sessions.Session;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetModerationPlayerInfoReader implements IMessageReader {
    private static final Logger logger = Logger.getLogger(GetModerationPlayerInfoReader.class);

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated() || !session.getPlayerInstance().hasRight("moderation_tool")) {
            return;
        }

        final int playerId = reader.readInteger();

        PlayerInformation info = null;
        final Session target = Bootloader.getSessionManager().getAuthenticatedSession(playerId);

        if (target == null || !target.isAuthenticated()) {
            try {
                final ResultSet row = Bootloader.getStorage().queryParams("SELECT * FROM players WHERE id=" + playerId).executeQuery();
                if (row.next()) {
                    info = new PlayerInformation();
                    info.set(row);
                }
            } catch (final SQLException e) {
                logger.error("SQL Exception", e);
            }

        } else {
            info = target.getPlayerInstance().getInformation();
        }

        if (info == null) {
            session.sendNotification(NotifyType.MOD_ALERT, Translations.getTranslation("fail_load_user_information"));
            return;
        }

        session.writeMessage(new ModerationPlayerInfoWriter(info, target));
    }

}
