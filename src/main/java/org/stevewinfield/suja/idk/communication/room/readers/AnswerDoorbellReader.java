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
import org.stevewinfield.suja.idk.communication.IMessageReader;
import org.stevewinfield.suja.idk.communication.MessageReader;
import org.stevewinfield.suja.idk.communication.room.writers.RoomDoorbellAcceptedWriter;
import org.stevewinfield.suja.idk.communication.room.writers.RoomDoorbellNoResponseWriter;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class AnswerDoorbellReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated() || !session.isInRoom()) {
            return;
        }

        final RoomInstance room = Bootloader.getGame().getRoomManager().getLoadedRoomInstance(session.getRoomId());

        if (room == null || !room.hasRights(session)) {
            return;
        }

        final String playerName = reader.readUTF();
        final boolean accept = reader.readBytes(1)[0] == 65;
        Session requestSession = null;

        for (final Session entry : Bootloader.getSessionManager().getSessions()) {
            if (!entry.isAuthenticated() || !entry.getPlayerInstance().getInformation().getPlayerName().equals(playerName)) {
                continue;
            }
            requestSession = entry;
            break;
        }

        if (requestSession == null || !requestSession.isLoadingRoom() || requestSession.getRoomId() != room.getInformation().getId()) {
            return;
        }

        if (accept) {
            requestSession.setLoadingChecksPassed(true);
            requestSession.writeMessage(new RoomDoorbellAcceptedWriter());
            return;
        }

        if (requestSession.isInRoom()) {
            final RoomInstance oldRoom = Bootloader.getGame().getRoomManager().loadRoomInstance(requestSession.getRoomId());
            oldRoom.removePlayerFromRoom(requestSession, false, false);
        }

        requestSession.writeMessage(new RoomDoorbellNoResponseWriter());
    }

}
