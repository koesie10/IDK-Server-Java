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

import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.Translations;
import org.stevewinfield.suja.idk.communication.IMessageReader;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.game.miscellaneous.NotifyType;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class ModerationPlayerMessageReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated() || !session.getPlayerInstance().hasRight("moderation_tool")) {
            return;
        }

        final int playerId = reader.readInteger();
        final String message = reader.readUTF();

        if (message.length() < 1) {
            return;
        }

        final Session target = Bootloader.getSessionManager().getAuthenticatedSession(playerId);

        if (target == null) {
            session.sendNotification(NotifyType.MOD_ALERT, Translations.getTranslation("fail_send_message_user_not_online"));
            return;
        }

        target.sendNotification(NotifyType.MOD_ALERT, message);
        Bootloader.getGame().getModerationManager()
                .logAction(
                        session.getPlayerInstance().getInformation().getId(),
                        "Sent a message to " +
                                target.getPlayerInstance().getInformation().getPlayerName() +
                                " (ID: " + target.getPlayerInstance().getInformation().getId() +
                                "): \"" +
                                message.replace("\"", "'") +
                                "\""
                );
    }

}
