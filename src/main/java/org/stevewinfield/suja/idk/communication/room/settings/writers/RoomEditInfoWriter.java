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
package org.stevewinfield.suja.idk.communication.room.settings.writers;

import org.stevewinfield.suja.idk.Bootloader;
import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.OperationCodes;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;

public class RoomEditInfoWriter extends MessageWriter {

    public RoomEditInfoWriter(final RoomInstance room) {
        super(OperationCodes.getOutgoingOpCode("RoomEditInfo"));
        super.push(room.getInformation().getId());
        super.push(room.getInformation().getName());
        super.push(room.getInformation().getDescription());
        super.push(room.getInformation().getAccessType());
        super.push(room.getInformation().getCategoryId());
        super.push(room.getInformation().getMaxPlayers());
        super.push(room.getInformation().getModel().getMaxPlayers());
        super.push(room.getInformation().getRoomTags().length);
        for (final String tag : room.getInformation().getRoomTags()) {
            super.push(tag);
        }
        final int size = room.getRights().size();
        super.push(size);
        for (int i = 0; i < size; i++) {
            final int playerId = room.getRights().get(i);
            super.push(playerId);
            super.push(Bootloader.getStorage().readString("SELECT nickname FROM players WHERE id=" + playerId));
        }
        super.push(size);
        super.push(room.getInformation().petsAreAllowed());
        super.push(room.getInformation().petsEatingAllowed());
        super.push(room.getInformation().blockingDisabled()); // room blocking
        // disabled
        super.push(room.getInformation().wallsHidden());
        super.push(room.getInformation().getWallThickness());
        super.push(room.getInformation().getFloorThickness());
    }

}
