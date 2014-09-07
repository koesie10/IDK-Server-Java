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
package org.stevewinfield.suja.idk.communication.moderation.writers;

import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.OperationCodes;
import org.stevewinfield.suja.idk.game.rooms.RoomInformation;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;
import org.stevewinfield.suja.idk.game.rooms.RoomPlayer;

public class ModerationRoomInfoWriter extends MessageWriter {

    public ModerationRoomInfoWriter(final RoomInformation roomInfo, final RoomInstance instance) {
        super(OperationCodes.getOutgoingOpCode("ModerationRoomInfo"));
        super.push(roomInfo.getId());
        super.push(roomInfo.getTotalPlayers());

        boolean foundOwner = false;

        if (instance != null) {
            for (final RoomPlayer player : instance.getRoomPlayers().values()) {
                if (!player.isBot() && player.getSession() != null && player.getPlayerInformation().getId() == roomInfo.getOwnerId()) {
                    foundOwner = true;
                    break;
                }
            }
        }

        super.push(foundOwner);
        super.push(roomInfo.getOwnerId());
        super.push(roomInfo.getOwnerName());
        super.push(roomInfo.getId());
        super.push(roomInfo.getName());
        super.push(roomInfo.getDescription());
        super.push(0); // tag count TODO
        super.push(false); // event? TODO
    }

}
