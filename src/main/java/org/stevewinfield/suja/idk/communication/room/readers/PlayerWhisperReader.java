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
package org.stevewinfield.suja.idk.communication.room.readers;

import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.InputFilter;
import org.stevewinfield.suja.idk.communication.IMessageReader;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class PlayerWhisperReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated() || !session.isInRoom()) {
            return;
        }

        final RoomInstance room = Bootloader.getGame().getRoomManager().getLoadedRoomInstance(session.getRoomId());

        if (room == null || session.getRoomPlayer().isGettingKicked()) {
            return;
        }

        String message = reader.readUTF();

        if (message.length() < 1) {
            return;
        }

        final String targetName = message.substring(0, message.indexOf(" "));

        if (targetName.length() < 1 || message.length() < (targetName.length() + 1)) {
            return;
        }

        message = InputFilter.filterString(message.substring(message.indexOf(" ") + 1));

        if (message.length() < 1) {
            return;
        }

        if (message.length() > 100) {
            message = message.substring(0, 100);
        }

        session.getRoomPlayer().whisper(session, session.getRoomPlayer().getVirtualId(), message);

        if (targetName.equals(session.getPlayerInstance().getInformation().getPlayerName())) {
            return;
        }

        RoomPlayer target = null;

        for (final RoomPlayer player : room.getRoomPlayers().values()) {
            if (player.getSession() != null && player.getPlayerInformation().getPlayerName().equals(targetName)) {
                target = player;
                break;
            }
        }

        if (target != null) {
            target.whisper(target.getSession(), session.getRoomPlayer().getVirtualId(), message);
        }
    }

}
