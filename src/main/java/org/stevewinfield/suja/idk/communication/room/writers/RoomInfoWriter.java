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
package org.stevewinfield.suja.idk.communication.room.writers;

import org.stevewinfield.suja.idk.communication.MessageWriter;
import org.stevewinfield.suja.idk.communication.OperationCodes;
import org.stevewinfield.suja.idk.game.rooms.RoomInstance;

public class RoomInfoWriter extends MessageWriter {

    public RoomInfoWriter(final RoomInstance instance, final boolean isLoading, final boolean checkEntry) {
        super(OperationCodes.getOutgoingOpCode("RoomInfo"));
        super.push(isLoading);
        super.push(instance.getInformation().getId());
        super.push(0);
        super.push(instance.getInformation().getName());
        super.push(instance.getInformation().getOwnerName());
        super.push(instance.getInformation().getAccessType());
        super.push(instance.getInformation().getTotalPlayers());
        super.push(instance.getInformation().getMaxPlayers());
        super.push(instance.getInformation().getDescription());
        super.push(0);
        super.push(instance.getInformation().isTradingEnabled());
        super.push(0);
        super.push(instance.getInformation().getCategoryId());
        super.push("");
        super.push(instance.getInformation().getRoomTags().length);
        for (final String tag : instance.getInformation().getRoomTags()) {
            super.push(tag);
        }
        super.push(0); // background
        super.push(0); // overlay
        super.push(0); // objects
        super.push(instance.getInformation().petsAreAllowed());
        super.push(true);
        super.push(checkEntry);
        super.push(false); // is staff picked room TODO
    }

    public RoomInfoWriter(final RoomInstance instance) {
        this(instance, true, false);
    }

}
