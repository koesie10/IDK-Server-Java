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
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;
import org.stevewinfield.suja.idk.game.rooms.coordination.Rotation;
import org.stevewinfield.suja.idk.game.rooms.coordination.Vector2;
import org.stevewinfield.suja.idk.network.sessions.Session;

public class PlayerChangeRotationReader implements IMessageReader {

    @Override
    public void parse(final Session session, final MessageReader reader) {
        if (!session.isAuthenticated() || !session.isInRoom()) {
            return;
        }

        final RoomInstance room = Bootloader.getGame().getRoomManager().getLoadedRoomInstance(session.getRoomId());

        if (room == null || session.getRoomPlayer().isWalking() || session.getRoomPlayer().isFrozen()) {
            return;
        }

        final RoomPlayer player = session.getRoomPlayer();
        final Vector2 targetPosition = new Vector2(reader.readInteger(), reader.readInteger());

        if ((targetPosition.getX() == player.getPosition().getX() &&
                targetPosition.getY() == player.getPosition().getY()) ||
                player.getStatusMap().containsKey("sit") ||
                player.getStatusMap().containsKey("lay")) {
            return;
        }

        final int rotation = Rotation.calculate(player.getPosition().getX(), player.getPosition().getY(), targetPosition.getX(), targetPosition.getY());

        boolean update = false;

        if (player.getRotation() != rotation) {
            player.setBodyRotation(rotation);
            update = true;
        }

        if (player.getHeadRotation() != rotation) {
            player.setHeadRotation(rotation);
            update = true;
        }

        if (update) {
            player.update();
        }
    }

}
