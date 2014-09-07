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
import org.stevewinfield.suja.idk.communication.moderation.writers.ModerationRoomInfoWriter;
import org.stevewinfield.suja.idk.game.miscellaneous.NotifyType;
import org.stevewinfield.suja.idk.game.rooms.RoomInformation;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class GetModerationRoomInfoReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated() || !session.getPlayerInstance().hasRight("moderation_tool")) {
            return;
        }

        final RoomInformation info = Bootloader.getGame().getRoomManager().getRoomInformation(reader.readInteger());

        if (info == null) {
            session.sendNotification(NotifyType.MOD_ALERT, Translations.getTranslation("fail_load_room_information"));
            return;
        }

        session.writeMessage(new ModerationRoomInfoWriter(info, Bootloader.getGame().getRoomManager().getLoadedRoomInstance(info.getId())));
    }

}
