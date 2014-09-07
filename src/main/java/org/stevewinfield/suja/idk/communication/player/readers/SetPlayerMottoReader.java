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
package org.stevewinfield.suja.idk.communication.player.readers;

import org.apache.log4j.Logger;
import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.InputFilter;
import org.stevewinfield.suja.idk.communication.IMessageReader;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.game.friendstream.FriendStreamEventType;
import org.stevewinfield.suja.idk.game.friendstream.FriendStreamLinkType;
import org.stevewinfield.suja.idk.network.sessions.Session;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SetPlayerMottoReader implements IMessageReader {
    private static final Logger logger = Logger.getLogger(SetPlayerMottoReader.class);

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated()) {
            return;
        }

        String motto = InputFilter.filterString(reader.readUTF());

        if (motto.length() < 1) {
            return;
        }

        if (motto.length() > 38) {
            motto = motto.substring(0, 38);
        }

        session.getPlayerInstance().getInformation().setMotto(motto);
        session.sendInformationUpdate();

        try {
            final PreparedStatement std = Bootloader.getStorage().queryParams("UPDATE players SET motto=? WHERE id=" + session.getPlayerInstance().getInformation().getId());
            std.setString(1, motto);
            std.execute();
            std.close();
        } catch (final SQLException e) {
            logger.error("SQL Exception", e);
        }

        if (session.getPlayerInstance().getInformation().isStreamEnabled()) {
            session.getFriendStream().broadcastEvent(session, FriendStreamEventType.MOTTO_CHANGED, FriendStreamLinkType.OPEN_MOTTO_CHANGER, new String[]{motto});
        }

        Bootloader.getGame().getAchievementManager().progressAchievement(session, "ACH_Motto", 1);

    }

}
